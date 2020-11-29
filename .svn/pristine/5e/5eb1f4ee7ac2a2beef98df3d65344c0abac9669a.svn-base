package com.it.controller;

import com.it.bean.*;
import com.it.dao.IProject_DAO;
import com.it.services.IWorkorder_Services;
import com.it.services.Workorder_ServicesImpl;
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

@WebServlet(name = "StatisticsServlet",urlPatterns = {"/statistics.do"})
public class StatisticsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.处理乱码
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        //2.获取页面的值
        String opt = request.getParameter("opt");
        String sel_customer_name = request.getParameter("customer_name");
        String sel_project_name = request.getParameter("project_name");
        String sel_createtime = request.getParameter("createtime");
        System.out.println(sel_project_name);
        System.out.println(opt);
        //3.创建对象
        Workorder workorder = new Workorder();
        //4.services
        IWorkorder_Services iWorkorder_services = new Workorder_ServicesImpl();
        String flags = "1";

        //查询所有业务
        if ("queryAll".equals(opt)){
            List<Workorder> workorderList = null;
            //page 当前页码
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            //模糊查询
            if(!"".equals(sel_customer_name)&&sel_customer_name!=null){
                workorder.setCustomer_name(sel_customer_name);
            }
            if(!"".equals(sel_project_name)&&sel_project_name!=null){
                workorder.setProject_name(sel_project_name);
            }
            if(!"".equals(sel_createtime)&&sel_createtime!=null){
                workorder.setCreatetime(sel_createtime);
            }
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            //查询所有信息默认分页
            workorderList = iWorkorder_services.queryAllWorkorderAndLimit(pageBean,workorder);
            for (Workorder workorder1 : workorderList) {
                //调用一个查询工单申请的dao ,返回一个count,然后判断是否为空，然后set进去
                if (!"".equals(workorder1) && workorder1 != null) {
                    int num1 = iWorkorder_services.queryWorkorderCount(workorder1);
                    int num2 = iWorkorder_services.queryWorkorder_ProcessCount(workorder1);
                    int num3 = iWorkorder_services.queryWorkorder_VisitCount(workorder1);
                    workorder1.setWorkorder_count(num1);
                    workorder1.setWorkorder_process_count(num2);
                    workorder1.setWorkorder_visit_count(num3);
                    System.out.println("1--" + num1);
                    System.out.println("2--" + num2);
                    System.out.println("3--" + num3);
                    System.out.println(workorder);
                }
            }
            //查询总记录数
            int total = iWorkorder_services.queryAllWorkorderCount(workorder);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(workorderList);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println("json--"+jsonArray);
            System.out.println(result.toString());
            out.print(result);
        }


        //查询统计图表数据业务
        if ("queryData".equals(opt)){
            String project_name = request.getParameter("project_name");
            String customer_name = request.getParameter("customer_name");
            if (!"".equals(project_name) && project_name!=null){
                workorder.setProject_name(project_name);
            }
            if (!"".equals(customer_name) && customer_name!=null){
                workorder.setCustomer_name(customer_name);
            }
            int num1 = iWorkorder_services.queryWorkorderCount(workorder);
            int num2 = iWorkorder_services.queryWorkorder_ProcessCount(workorder);
            int num3 = iWorkorder_services.queryWorkorder_VisitCount(workorder);
            workorder.setWorkorder_count(num1);
            workorder.setWorkorder_process_count(num2);
            workorder.setWorkorder_visit_count(num3);

            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(workorder);
            System.out.println("json--"+jsonArray);
            out.print(jsonArray);
        }

        //查询头部右侧图表数据业务
        if ("queryHeadRightData".equals(opt)){
            List<Workorder> workorderList = null;
            workorderList = iWorkorder_services.queryDistinctProject_name(workorder);
            for (Workorder workorder1 : workorderList) {
                if (!"".equals(workorder1.getProject_name()) && workorder1.getProject_name() !=null){
                    workorder1.setProject_name(workorder1.getProject_name());
                }
                int num1 = iWorkorder_services.queryWorkorderCountByPro(workorder1);
                int num2 = iWorkorder_services.queryWorkorder_ProcessCountByPro(workorder1);
                int num3 = iWorkorder_services.queryWorkorder_VisitCountByPro(workorder1);
                workorder1.setWorkorder_count(num1);
                workorder1.setWorkorder_process_count(num2);
                workorder1.setWorkorder_visit_count(num3);
                System.out.println("323"+num1);
                System.out.println("323"+num2);
                System.out.println("323"+num3);
            }

            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(workorderList);
            System.out.println("json--"+jsonArray);
            out.print(jsonArray);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
