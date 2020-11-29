package com.it.controller;


import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.services.ProjectService;
import com.it.services.impl.ProjectServicesImpl;
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

@WebServlet(name = "ProServlet",urlPatterns = {"/project1.do"})
public class ProServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String project_name = request.getParameter("project_name");
        String identifier = request.getParameter("identifier");
        String business_id = request.getParameter("business_id");
        String createp_id = request.getParameter("createp_id");
        String status = request.getParameter("status");
        String uptime = request.getParameter("uptime");
        String prolong = request.getParameter("prolong");
        String createtime = request.getParameter("createtime");
        String description = request.getParameter("description");
        Project project = new Project();
        if (!"".equals(id) && id!=null){
            project.setId(Integer.parseInt(id));
        }
        if (!"".equals(project_name) && project_name!=null){
            project.setProject_name(project_name);
        }
        if (!"".equals(identifier) && identifier!=null){
            project.setIdentifier(identifier);
        }
        if (!"".equals(business_id) && business_id!=null){
            project.setBusiness_id(Integer.parseInt(business_id));
        }
        if (!"".equals(createp_id) && createp_id!=null){
            project.setCreatep_id(Long.parseLong(createp_id));
        }
        if (!"".equals(status) && status!=null){
            project.setStatus(Integer.parseInt(status));
        }
        if (!"".equals(uptime) && uptime!=null){
            project.setUptime(uptime);
        }
        if (!"".equals(prolong) && prolong!=null){
            project.setProlong(prolong);
        }
        if (!"".equals(createtime) && createtime!=null){
            project.setCreatetime(createtime);
        }
        if (!"".equals(description) && description!=null){
            project.setDescription(description);
        }
        ProjectService services = new ProjectServicesImpl();
        String flags = "1";
        //添加
        if ("add".equals(opt)) {
            boolean flag = services.add(project);
            if (!flag){
                flags = "0";
            }
            System.out.println("add----"+flags);
            out.print(flags);
        }
        //删除单个
        if ("delete".equals(opt)){
            boolean flag = services.delete(project);
            if (!flag){
                flags = "0";
            }
            System.out.println("delete-----"+flags);
            out.print(flags);
        }
        //批量删除
        if ("delBatch".equals(opt)){
            String ids = request.getParameter("delArray");
            String [] idt = ids.split(",");
            for (int i = 0; i < idt.length; i++) {
                if(id != null & !"".equals(id)){
                    try{
                        project.setId(Integer.parseInt(idt[i]));
                        services.delete(project);
                    }catch (Exception e){
                        e.printStackTrace();
                        flags = "0";
                    }
                }
            }
            System.out.println("删除"+flags);
            out.print(flags);
        }
        //修改
        if ("update".equals(opt)) {
            boolean flag = services.update(project);
            if (!flag){
                flags = "0";
            }
            System.out.println("update----"+flags);
            out.print(flags);
        }
        //查询所有
        if ("queryAll".equals(opt)) {
            String page = request.getParameter("page");
            String rows = request.getParameter("limit");
            System.out.println("page-->" + page);
            System.out.println("rows-->" + rows);
            System.out.println("=======1");
            PageBean pageBean = null;
            if(page != null && !"".equals(page)){
                if(rows != null && !"".equals(rows)){
                    pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
                }
            }
            List<Project> list =null;
            int total =services.queryAll(project).size();
            System.out.println("total-->" + total);
            list = services.queryAllPage(project,pageBean);
            JSONArray jsonArray = JSONArray.fromObject(list);
            JSONObject result = new JSONObject();
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
        }
        //按项目名称查找
        if ("selectProjName".equals(opt)){
            System.out.println("============");
            List<Project> projectList = services.selectProjName(project);
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
