package com.it.controller;

import com.it.bean.PageBean;
import com.it.bean.ProjectVo;
import com.it.bean.Project_task;
import com.it.services.Project_taskServices;
import com.it.services.Project_taskServicesImpl;
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

@WebServlet(name = "Project_taskServlet",urlPatterns = {"/project_task.do"})
public class Project_taskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理乱码
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();
        //获取页面信息
        String opt = request.getParameter("opt");
        String project_name = request.getParameter("project_name");
        String createtime = request.getParameter("createtime");
        String name = request.getParameter("name");
        String project_id = request.getParameter("project_id");

        String manager_id = request.getParameter("manager_id");
        String plan_time = request.getParameter("plan_time");
        String actul_time = request.getParameter("actul_time");
        String status = request.getParameter("status");

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        Project_task project_task = new Project_task();
        ProjectVo projectVo = new ProjectVo();
        Project_taskServices project_taskServices = new Project_taskServicesImpl();
        //成功1  , 失败0
        String rs = "1";


        //查询所有

        if ("queryAll".equals(opt)) {
            //page 当前页码
            String page = request.getParameter("page");

            // pagesize
            String rows = request.getParameter("limit");
            // "" null
            if (!"".equals(createtime) && createtime != null) {
                projectVo.setCreatetime(createtime);
            }
            if (!"".equals(project_name) && project_name != null) {
                projectVo.setProject_name(project_name);
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));


            // 执行 查询所有信息默认分页
            List<ProjectVo> ProjectVoList = project_taskServices.queryAllProjectVo(pageBean,projectVo,startTime,endTime);

            System.out.println("servlet-->" + ProjectVoList);


            //查询总记录数
            int total = project_taskServices.count();

            JSONObject result = new JSONObject();

            //转成jsonarray
            JSONArray jsonArray = JSONArray.fromObject(ProjectVoList);

            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());

            //发送到前端
            out.print(result);


        }
        // 修改操作
        if ("upd".equals(opt)) {
            // 处理数据类型 “” null
            if (!"".equals(project_id) && project_id != null) {
                project_task.setProject_id(Integer.parseInt(project_id));
            }
            if (!"".equals(status) && status != null) {
                project_task.setStatus(Integer.parseInt(status));
            }
            if (!"".equals(actul_time) && actul_time != null) {
                project_task.setActul_time(Integer.parseInt(actul_time));
            }
            if (!"".equals(project_id) && project_id != null) {
                project_task.setProject_id(Integer.parseInt(project_id));
            }

            JSONObject result = new JSONObject();

            //执行
            boolean flag = project_taskServices.updateProject_task(project_task);
            if (!flag) {
                rs = "0";
            }
            System.out.println("结果-->" + rs);
            out.print(rs);
        }
        /**
         * 添加方法
         * */
        if ("add".equals(opt)) {

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (!"".equals(project_id) && project_id != null) {
                projectVo.setProject_id(Integer.parseInt(project_id));
            }
            projectVo.setProject_name("项目二");
            if (!"".equals(name) && name != null) {
                projectVo.setName(name);
            }

            projectVo.setPlan_time(50);
            projectVo.setActul_time(50);
            projectVo.setCreatetime(sdf.format(date));

            projectVo.setManager_id(2);

            if (!"".equals(status) && status != null) {
                projectVo.setStatus(Integer.parseInt(status));
            }

            boolean flag = project_taskServices.addProjectVo(projectVo);
            if (!flag) {
                rs = "0";
            }

            System.out.println("结果-->" + rs);
            //发送到前端
            out.print(rs);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
