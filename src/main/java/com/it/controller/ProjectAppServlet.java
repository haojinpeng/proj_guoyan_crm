package com.it.controller;

import com.it.bean.PageBean;
import com.it.bean.ProjectApp;
import com.it.services.ProjectAppServices;
import com.it.services.ProjectAppServicesImpl;
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

@WebServlet(name = "ProjectAppServlet",urlPatterns = {"/projectApp.do"})
public class ProjectAppServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();

        //获取页面信息
        String opt = request.getParameter("opt");
        String project_id = request.getParameter("project_id");
        String project_name = request.getParameter("project_name");
        String name = request.getParameter("name");
        String createtime = request.getParameter("createtime");
        String manager_id = request.getParameter("manager_id");
        String status = request.getParameter("status");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        //生成对象
        ProjectApp projectApp = new ProjectApp();

        ProjectAppServices projectAppServices = new ProjectAppServicesImpl();

        //成功1  , 失败0
        String rs = "1";

        /**
         * 查询所有
         * */
        if ("queryAll".equals(opt)) {
            //page 当前页码
            String page = request.getParameter("page");

            // pagesize
            String rows = request.getParameter("limit");
            // "" null
            if (!"".equals(createtime) && createtime != null) {
                projectApp.setCreatetime(createtime);
            }
            if (!"".equals(project_name) && project_name != null) {
                projectApp.setProject_name(project_name);
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));


            // 执行 查询所有信息默认分页
            List<ProjectApp> ProjectAppList = projectAppServices.queryAllProjectApp(pageBean,projectApp,startTime,endTime);

            System.out.println("servlet-->" + ProjectAppList);


            //查询总记录数
            int total = projectAppServices.count();

            JSONObject result = new JSONObject();

            //转成jsonarray
            JSONArray jsonArray = JSONArray.fromObject(ProjectAppList);

            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());

            //发送到前端
            out.print(result);


        }
        /**
         * 查询所有
         * */
        if ("select".equals(opt)) {

            // 执行 查询所有信息默认分页
            List<ProjectApp> ProjectAppList = projectAppServices.selectProjectApp(projectApp);

            System.out.println("servlet-->" + ProjectAppList);

            JSONObject result = new JSONObject();

            //转成jsonarray
            JSONArray jsonArray = JSONArray.fromObject(ProjectAppList);

            result.put("code", 0);
            result.put("msg", "正在加载....");

            result.put("data", jsonArray);
            System.out.println(result.toString());

            //发送到前端
            out.print(result);


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
