package com.it.controller;

import com.it.bean.Jurisdiction;
import com.it.bean.PageBean;
import com.it.bean.Project_cost_statistics;
import com.it.services.IJurisdictionServices;
import com.it.services.IProject_cost_statisticsServices;
import com.it.services.impl.JurisdictionServicesImpl;
import com.it.services.impl.Project_cost_statisticsServicesImpl;
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

@WebServlet(name = "Project_cost_statisticsServlet",value = "/project_cost_statistics.do")
public class Project_cost_statisticsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        //获取页面上的值
        String opt = request.getParameter("opt");
        System.out.println("opt==========" + opt);
        String projectName = request.getParameter("projectName");
        String customerName = request.getParameter("customerName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        //page 当前页码
        String page = request.getParameter("page");
        //limit pageSize
        String rows = request.getParameter("limit");

        PrintWriter out = response.getWriter();

        //创建对象
        Project_cost_statistics project_cost_statistics=new Project_cost_statistics();
        JSONObject result = new JSONObject();

        // 存放集合内容
        List<Project_cost_statistics> project_cost_statisticsList = null;

        // 判 "" null
        if (projectName != null && !"".equals(projectName)) {
            project_cost_statistics.setProjectName(projectName);
        }
        if (customerName != null && !"".equals(customerName)) {
            project_cost_statistics.setCustomerName(customerName);
        }
        if (startTime != null && !"".equals(startTime)) {
            project_cost_statistics.setStartTime(startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            project_cost_statistics.setEndTime(endTime);
        }

        System.out.println("project_cost_statistics==========" + project_cost_statistics);

        //service
        IProject_cost_statisticsServices project_cost_statisticsServices=new Project_cost_statisticsServicesImpl();

        // 判别flag  0为失败
        int flag = 0;

        if ("queryAll".equals(opt)){
            //查询所有信息默认分页
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            project_cost_statisticsList=project_cost_statisticsServices.queryAll(pageBean,project_cost_statistics);

            //查询总记录数
            int total = project_cost_statisticsServices.count(project_cost_statistics);
            System.out.println("total=========="+total);
            // jsonArray
            JSONArray jsonArray = JSONArray.fromObject(project_cost_statisticsList);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
            return;
        }else{
            System.out.println("无opt");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
