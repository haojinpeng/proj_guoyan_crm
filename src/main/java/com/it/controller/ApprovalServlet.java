package com.it.controller;

import com.it.bean.Expenses_apply;
import com.it.bean.Expenses_approval;
import com.it.bean.PageBean;
import com.it.services.IExpenses_apply_Services;
import com.it.services.IExpenses_approval_Services;
import com.it.services.Expenses_apply_ServicesImpl;
import com.it.services.Expenses_approval_ServicesImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ApprovalServlet",urlPatterns = {"/approval.do"})
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Expenses_approval expenses_approval = new Expenses_approval();
        //判断登陆人，进行展示对应的记录
        /*String loginname = (String)request.getSession().getAttribute("loginname");*/
        String loginname = "admin2";
        expenses_approval.setLogin_name(loginname);

        //1.处理乱码
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        //2.获取页面的值
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String createtime = request.getParameter("time");
        String status = request.getParameter("sta");
        String description = request.getParameter("des");
        String parent_Reviewer = request.getParameter("parentReviewer");
        String expenses_id = request.getParameter("expenses_id");
        //3.创建对象
        //4.services
        IExpenses_approval_Services iExpenses_approval_services = new Expenses_approval_ServicesImpl();
        String flags = "1";

        //添加业务
        if ("update".equals(opt)){
            //判断
            if (!"".equals(id) && id != null){
                expenses_approval.setId(Integer.parseInt(id));
            }
            if (!"".equals(createtime) && createtime != null){
                expenses_approval.setCreatetime(createtime);
            }
            if (!"".equals(status) && status != null){
                expenses_approval.setStatus(Integer.parseInt(status));
            }
            if (!"".equals(description) && description != null){
                expenses_approval.setDescription(description);
            }
            if (!"".equals(parent_Reviewer) && parent_Reviewer != null){
                expenses_approval.setParent_Reviewer(Integer.parseInt(parent_Reviewer));
            }
            if (!"".equals(expenses_id) && expenses_id != null){
                expenses_approval.setExpenses_id(Integer.parseInt(expenses_id));
            }

            boolean flag = iExpenses_approval_services.updExpenses_approval(expenses_approval);
            if (flag){
                if (expenses_approval.getParent_Reviewer()!=0){
                    //0为驳回
                    //判断是否为最终审核人
                    int pid = iExpenses_approval_services.queryParent_id(expenses_approval);
                    if (pid == 0){
                        //为最终审核人，那么他的审核结果为最终审核结果
                        //update到此用户审核表中
                        boolean flag3 = iExpenses_approval_services.updExpenses_apply_Status(expenses_approval);
                        if (!flag3){
                            flags = "0";
                        }
                    }else {
                        //添加给上级审核人记录
                        boolean flag1 = iExpenses_approval_services.addExpenses_approval(expenses_approval);
                        if (flag1){
                            flags = "1";
                        }else {
                            flags = "0";
                        }
                    }
                }else if (expenses_approval.getParent_Reviewer()==0){
                    //此为最高级审核人状态
                    //最高或非最高级选择状态为驳回，需要将此申请设置为驳回
                    boolean flag2 = iExpenses_approval_services.updExpenses_apply_Status(expenses_approval);
                    if (flag2){
                        flags = "1";
                    }else {
                        flags = "0";
                    }
                }
            }else {
                flags = "0";
            }
            System.out.println(flags);
            out.print(flags);
        }

        //查询所有业务
        if ("queryAll".equals(opt)){

            //模糊查询，重载表格
            String Apply_id = request.getParameter("apply_id");    //申请表id
            String Approval_Status = request.getParameter("approval_status");   //审核状态
            String TimeHead = request.getParameter("timeHead");   //审核时间

            List<Expenses_approval> expenses_approvalList = null;
            //page 当前页码
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            if(!"".equals(createtime)&&createtime!=null){
                expenses_approval.setCreatetime(createtime);
            }
            if(!"".equals(Apply_id)&&Apply_id!=null){
                expenses_approval.setExpenses_id(Integer.parseInt(Apply_id));
            }
            if(!"".equals(Approval_Status)&&Approval_Status!=null){
                expenses_approval.setStatus(Integer.parseInt(Approval_Status));
            }
            if(!"".equals(TimeHead)&&TimeHead!=null){
                expenses_approval.setCreatetime(TimeHead);
            }
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            //查询所有信息默认分页
            expenses_approvalList = iExpenses_approval_services.queryAllExpenses_approvalAndLimit(pageBean,expenses_approval);
            //查询总记录数
            int total = iExpenses_approval_services.queryExpenses_approvalCount(expenses_approval);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(expenses_approvalList);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
