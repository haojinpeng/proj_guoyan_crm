package com.it.controller;

import com.it.bean.PageBean;
import com.it.services.ProjectServices;
import com.it.services.ProjectServicesImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

    @WebServlet(name = "ProjectServlet",urlPatterns = {"/project.do"})
public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理乱码
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();
        //获取页面信息
        String opt = request.getParameter("opt");
        String identifier = request.getParameter("identifier");
        String project_name = request.getParameter("project_name");
        String uptime = request.getParameter("uptime");
        String prolong = request.getParameter("prolong");
        String createp_id = request.getParameter("createp_id");//添加人
        String createtime = request.getParameter("createtime");
        String status = request.getParameter("status");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        //生成对象
        com.it.bean.Project project = new com.it.bean.Project();
        //services
        ProjectServices projectServices = new ProjectServicesImpl();
        //成功1  , 失败0
        String rs = "1";

        /**
         * 查询所有
         * */
        if ("queryall".equals(opt)) {
            //page 当前页码
            String page = request.getParameter("page");

            // pagesize
            String rows = request.getParameter("limit");
            // "" null
            if (!"".equals(createtime) && createtime != null) {
                project.setCreatetime(createtime);
            }
            if (!"".equals(createp_id) && createp_id != null) {
                project.setCreatep_id(Long.parseLong(createp_id));
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));


            // 执行 查询所有信息默认分页
            List<com.it.bean.Project> ProjectList = projectServices.queryAllProject(pageBean,project,startTime,endTime);

            System.out.println("servlet-->" + ProjectList);


            //查询总记录数
            int total = projectServices.count();

            JSONObject result = new JSONObject();

            //转成jsonarray
            JSONArray jsonArray = JSONArray.fromObject(ProjectList);

            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());

            //发送到前端
            out.print(result);


        }

        /**
        * 添加方法
        * */
        if ("add".equals(opt)) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (!"".equals(identifier) && identifier != null) {
                project.setIdentifier(identifier);
            }
            if (!"".equals(project_name) && project_name != null) {
                project.setProject_name(project_name);
            }

                project.setUptime("2019-12-12");
                project.setProlong("2019-12-12");
                project.setCreatetime(sdf.format(date));

            if (!"".equals(createp_id) && createp_id != null) {
                project.setCreatep_id(Long.parseLong(createp_id));
            }
            if (!"".equals(status) && status != null) {
                project.setStatus(Integer.parseInt(status));
            }

            boolean flag = projectServices.addProject(project);
            if (!flag) {
                rs = "0";
            }

            System.out.println("结果-->" + rs);
            //发送到前端
            out.print(rs);

        }

        /**
         * 删除单个
         * */
        if ("del".equals(opt)) {
            //获取前端页面的sno
            //判断
            if (!"".equals(identifier) && identifier != null) {
               project.setIdentifier(identifier);
            }

            boolean flag = projectServices.deleteProject(project);

            if (!flag) {
                rs = "0";
            }
            out.print(rs);
        }
        // 修改操作
        if ("upd".equals(opt)) {
            // 处理数据类型 “” null

            if (!"".equals(project_name) && project_name != null) {
                project.setProject_name(project_name);
            }

            if (!"".equals(createp_id) && createp_id != null) {
                project.setCreatep_id(Long.parseLong(createp_id));
            }
            if (!"".equals(createtime) && createtime != null) {
                project.setCreatetime(createtime);
            }
            if (!"".equals(status) && status != null) {
                project.setStatus(Integer.parseInt(status));
            }
            if (!"".equals(identifier) && identifier != null) {
                project.setIdentifier(identifier);
            }
            JSONObject result = new JSONObject();

            //执行
            boolean flag = projectServices.updateProject(project);
            if (!flag) {
                rs = "0";
            }
            System.out.println("结果-->" + rs);
            out.print(rs);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
