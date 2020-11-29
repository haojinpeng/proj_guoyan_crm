package com.it.controller;

import com.it.bean.Expenses_apply;
import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.User;
import com.it.dao.IProject_DAO;
import com.it.dao.IUser_DAO;
import com.it.services.IExpenses_apply_Services;
import com.it.services.IProject_Services;
import com.it.services.IUser_Services;
import com.it.services.Expenses_apply_ServicesImpl;
import com.it.services.Project_ServicesImpl;
import com.it.services.User_ServicesImpl;
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

@WebServlet(name = "ApplyServlet",urlPatterns = {"/apply.do"})
public class ApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Expenses_apply expenses_apply = new Expenses_apply();
        //登陆人session
        /*String loginname = (String)request.getSession().getAttribute("loginname");*/
        String loginname = "admin2";
        expenses_apply.setLogin_name(loginname);

        //1.处理乱码
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();
        //2.获取页面的值
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String apply_user = request.getParameter("apply_user");
        String project_id = request.getParameter("project_id");
        String apply_type = request.getParameter("apply_type");
        String apply_money = request.getParameter("apply_money");
        String commodity = request.getParameter("commodity");
        String createtime = request.getParameter("createtime");
        String status = request.getParameter("status");
        String apply_description = request.getParameter("apply_description");
        String reviewer_id = request.getParameter("audit_people");   //审核人
        System.out.println(opt);
        //3.创建对象
        Project project = new Project();
        //4.services
        IExpenses_apply_Services iExpenses_apply_services = new Expenses_apply_ServicesImpl();
        IProject_Services iProject_services = new Project_ServicesImpl();
        String flags = "1";



        //添加业务
        if ("add".equals(opt)){
            //判断
            if (!"".equals(apply_user) && apply_user != null){
                expenses_apply.setApply_user(Integer.parseInt(apply_user));
            }
            if (!"".equals(project_id) && project_id != null){
                expenses_apply.setProject_id(Integer.parseInt(project_id));
            }
            if (!"".equals(apply_type) && apply_type != null){
                expenses_apply.setApply_type(Integer.parseInt(apply_type));
            }
            if (!"".equals(apply_money) && apply_money != null){
                expenses_apply.setApply_money(Double.parseDouble(apply_money));
            }
            if (!"".equals(commodity) && commodity != null){
                expenses_apply.setCommodity(commodity);
            }
            if (!"".equals(createtime) && createtime != null){
                expenses_apply.setCreatetime(createtime);
            }
            if (!"".equals(apply_description) && apply_description != null){
                expenses_apply.setApply_description(apply_description);
            }
            if (!"".equals(reviewer_id) && reviewer_id != null){
                expenses_apply.setReviewer_id(Integer.parseInt(reviewer_id));
            }
            //通过登录名查找申请人id,然后set给apply
            //如此实现前端为姓名，但是传给后端的是id，应为通过session可以确定id,是死的
            User user = new User();
            IUser_Services iUser_services = new User_ServicesImpl();
            user = iUser_services.queryApply_idByLoginname(loginname);
            int num = user.getId();
            expenses_apply.setApply_user(num);
            System.out.println(expenses_apply);
            boolean flag = iExpenses_apply_services.addExpenses_apply(expenses_apply);
            if (!flag){
                flags = "0";
            }
            System.out.println(flags);
            out.print(flags);
        }

        //删除单条业务
        if("delete".equals(opt)){
            //获取前端页面的sno
            //判断
            if(!"".equals(id)&&id!=null){
                expenses_apply.setId(Integer.parseInt(id));
            }
            boolean flag = iExpenses_apply_services.delExpenses_apply(expenses_apply);
            if(!flag){
                flags="0";
            }
            out.print(flags);
        }

        //修改单条业务
        if("update".equals(opt)){
            //判断
            if (!"".equals(project_id) && project_id != null){
                expenses_apply.setProject_id(Integer.parseInt(project_id));
            }
            if (!"".equals(apply_type) && apply_type != null){
                expenses_apply.setApply_type(Integer.parseInt(apply_type));
            }
            if (!"".equals(apply_money) && apply_money != null){
                expenses_apply.setApply_money(Double.parseDouble(apply_money));
            }
            if (!"".equals(commodity) && commodity != null){
                expenses_apply.setCommodity(commodity);
            }
            if (!"".equals(createtime) && createtime != null){
                expenses_apply.setCreatetime(createtime);
            }
            if (!"".equals(apply_description) && apply_description != null){
                expenses_apply.setApply_description(apply_description);
            }
            if (!"".equals(id) && id != null){
                expenses_apply.setId(Integer.parseInt(id));
            }

            //通过登录名查找申请人id,然后set给apply
            //如此实现前端为姓名，但是传给后端的是id，应为通过session可以确定id,是死的
            User user = new User();
            IUser_Services iUser_services = new User_ServicesImpl();
            user = iUser_services.queryApply_idByLoginname(loginname);
            int num = user.getId();
            expenses_apply.setApply_user(num);

            boolean flag = iExpenses_apply_services.updExpenses_apply(expenses_apply);
            if(!flag){
                flags="0";
            }
            System.out.println(flags);
            out.print(flags);
        }

        //查询所有业务 包含分页，模糊查询
        if ("queryAll".equals(opt)){
            List<Expenses_apply> expenses_applyList = null;
            //page 当前页码
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            if(!"".equals(project_id)&&project_id!=null){
                expenses_apply.setProject_id(Integer.parseInt(project_id));
            }
            if(!"".equals(createtime)&&createtime!=null){
                expenses_apply.setCreatetime(createtime);
            }
            if(!"".equals(status)&&status!=null){
                expenses_apply.setApply_status(Integer.parseInt(status));
            }
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            //查询所有信息默认分页
            expenses_applyList = iExpenses_apply_services.queryAllExpenses_applyAndLimit(pageBean,expenses_apply);
            //查询总记录数
            int total = iExpenses_apply_services.queryExpenses_applyCount(expenses_apply);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(expenses_applyList);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
        }

        //查询当前登录人姓名业务
        //"1"成功  "0"失败
        String rs = "1";
        if ("queryLoginUsername".equals(opt)){
            User user = new User();
            IUser_Services iUser_services = new User_ServicesImpl();
            List<User> userList = null;
            userList = iUser_services.queryLoginUsername(loginname);
            if(userList!=null){
                JSONArray jsonArray = JSONArray.fromObject(userList);
                System.out.println(jsonArray.toString());
                out.print(jsonArray);
            }else{
                System.out.println("返回值为null");
                rs = "0";
                out.print(rs);
            }
        }

        //更新给审核表业务
        if ("submit".equals(opt)){
            if (!"".equals(id) && id != null){
                expenses_apply.setId(Integer.parseInt(id));
            }
            //设置申请页面中申请状态为3 审核中 没用到这个，我在dao中直接赋值
            if ("0".equals(status) && status != null){
                expenses_apply.setApply_status(3);
            }
            //给审核表插入数据
            boolean flag = iExpenses_apply_services.submitExpenses_apply(expenses_apply);
            //更新为审核中状态
            boolean flag2 = iExpenses_apply_services.updExpenses_apply_Apply_status(expenses_apply);
            System.out.println("flag2"+flag2);
            if (!flag){
                flags = "0";
            }
            out.print(flags);
        }
        //查询项目名称下拉框
        if ("queryAllProject_id".equals(opt)){
            List<Project> projectList = null;
            projectList = iProject_services.queryProject_ProjectId(project);
            JSONArray jsonArray = JSONArray.fromObject(projectList);
            System.out.println("111--"+jsonArray);
            out.print(jsonArray);
        }
        //查询审核人下列框
        //"1"成功  "0"失败
        if ("queryApprovalUser".equals(opt)){
            User user = new User();
            IUser_Services iUser_services = new User_ServicesImpl();
            List<User> userList = iUser_services.queryParentUser(loginname);
            if(userList!=null){
                JSONArray jsonArray = JSONArray.fromObject(userList);
                System.out.println(jsonArray.toString());
                out.print(jsonArray);
            }else{
                System.out.println("返回值为null");
                rs = "0";
                out.print(rs);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
