package com.it.controller;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.Project_task;
import com.it.services.ProjectTaskServices;
import com.it.services.impl.ProjectTaskServicesImpl;
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

@WebServlet(name = "ServletProject",urlPatterns = {"/project_task1.do"})
public class ServletProject extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;utf-8");
        PrintWriter out = response.getWriter();

        String opt = request.getParameter("opt");
        String project_name = request.getParameter("project_name");
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Project_task project_task = new Project_task();
        Project project = new Project();
        if (!"".equals(project_name) && project_name!=null){
            project_task.setProject_name(project_name);
        }
        if (!"".equals(username) && username!=null){
            project_task.setUsername(username);
        }
        if (!"".equals(name) && name!=null){
            project_task.setName(name);
        }
        if (!"".equals(description) && description!=null){

            project_task.setDescrption(description);
        }
        ProjectTaskServices services = new ProjectTaskServicesImpl();

        if ("query".equals(opt)){
            String page = request.getParameter("page");
            String rows = request.getParameter("limit");
            System.out.println("page-->" + page);
            System.out.println("rows-->" + rows);
            PageBean pageBean = null;
            if(page != null && !"".equals(page)){
                if(rows != null && !"".equals(rows)){
                    pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
                }
            }
            int total =services.queryAll(project_task).size();
            System.out.println("total-->" + total);
            List<Project_task> list = services.queryTask(project_task,pageBean);
            System.out.println("project_task_list"+list);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(list);
            System.out.println(jsonArray.toString());
            result.put("code",0);
            result.put("data",jsonArray);
            out.print(result);
        }
        //按项目名称查找
        if ("selectProjName".equals(opt)){
            List<Project> projectList = services.selectProjName(project);
            System.out.println("projectList---"+projectList);
            JSONArray jsonArray = JSONArray.fromObject(projectList);
            System.out.println(jsonArray.toString());
            out.print(jsonArray);
        }



    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
