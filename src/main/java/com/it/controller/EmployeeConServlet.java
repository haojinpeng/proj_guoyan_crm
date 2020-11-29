package com.it.controller;

import com.it.bean.*;
import com.it.services.EmployeeContrService;
import com.it.services.impl.EmployeeContrServiceImpl;
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

@WebServlet(name = "EmployeeConServlet",urlPatterns = {"/employeeCon.do"})
public class EmployeeConServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String project_name=request.getParameter("project_name");
        String station_name=request.getParameter("station_name");
        String employee_number=request.getParameter("employee_number");
        String createtime=request.getParameter("createtime");
        String number=request.getParameter("number");
        String workorder_id=request.getParameter("workorder_id");
        String cycle=request.getParameter("cycle");
        String actual_working_hours=request.getParameter("actual_working_hours");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        String numberCount = request.getParameter("numberCount");
        String workCount = request.getParameter("workCount");
        EmployeeContribution ec = new EmployeeContribution();
        if (id!=null && !"".equals(id)){
            ec.setId(Integer.valueOf(id));
        }
        if (!"".equals(workorder_id)  && workorder_id!=null){
            ec.setWorkorder_id(Integer.valueOf(workorder_id));
        }
        if (!"".equals(number)  && number!=null){
            ec.setNumber(number);
        }
        if (!"".equals(cycle)  && cycle!=null){
            ec.setCycle(Integer.valueOf(cycle));
        }
        if (!"".equals(actual_working_hours)  && actual_working_hours!=null){
            ec.setActual_working_hours(Integer.valueOf(actual_working_hours));
        }
        if (!"".equals(createtime)  && createtime!=null){
            ec.setCreatetime(createtime);
        }
        if (!"".equals(numberCount) && numberCount!=null){
            ec.setNumberCount(Integer.parseInt(numberCount));
        }
        if (!"".equals(workCount) && workCount!=null){
            ec.setWorkCount(Integer.parseInt(workCount));
        }
        EmployeeContrService service = new EmployeeContrServiceImpl();
        Project project = new Project();
        Station station = new Station();
        User user = new User();
        //查询所有
        if ("queryAll".equals(opt)) {
            if (!"".equals(project_name)  && project_name!=null){
                ec.setProject_name(project_name);
            }
            if (!"".equals(station_name)  && station_name!=null){
                ec.setStation_name(station_name);
            }
            if (!"".equals(employee_number) && employee_number!=null){
                ec.setEmployee_number(employee_number);
            }
            if (!"".equals(createtime)  && createtime!=null){
                ec.setCreatetime(createtime);
            }
            String page = request.getParameter("page");
            String rows = request.getParameter("limit");
            System.out.println("这里");
            System.out.println("page-->" + page);
            System.out.println("rows-->" + rows);
            PageBean pageBean = null;
            if(page != null && !"".equals(page)){
                if(rows != null && !"".equals(rows)){
                    pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
                }
            }
            List<EmployeeContribution> list =null;
            System.out.println(beginTime);
            System.out.println(endTime);
            int total =service.queryAll(ec,beginTime,endTime).size();
            System.out.println("total-->" + total);
            list = service.queryAlls(ec,beginTime,endTime,pageBean);
            for (EmployeeContribution e:list
                 ) {
                if (!"".equals(e) && e!=null){
                    int num1 = service.queryCount(e);
                    int num2 = service.queryCounta(e);
                    e.setNumberCount(num1);
                    e.setWorkCount(num2);
                    System.out.println("接受工单数量" + num1);
                    System.out.println("解决工单数量" + num2);
                    System.out.println(ec);
                }
            }
            JSONArray jsonArray = JSONArray.fromObject(list);
            JSONObject result = new JSONObject();
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
        }
        //查询统计图表数据业务
        if ("queryData".equals(opt)){
            System.out.println("========0000");
            int num1 = service.queryCount(ec);
            int num2 = service.queryCounta(ec);
            ec.setNumberCount(num1);
            ec.setWorkCount(num2);
            ec.setCycle(Integer.valueOf(cycle));
            ec.setActual_working_hours(Integer.valueOf(actual_working_hours));
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(ec);
            System.out.println("=======1111");
            System.out.println("json--"+jsonArray);
            out.print(result);
        }
        //按项目名称查找
        if ("selectProName".equals(opt)){
            List<Project> projectList = service.selectProName(project);
            System.out.println("----------");
            System.out.println("projectList---"+projectList);
            JSONArray jsonArray = JSONArray.fromObject(projectList);
            System.out.println(jsonArray.toString());
            out.print(jsonArray);
        }
        //按岗位名称查找
        if ("selectStatName".equals(opt)) {
            System.out.println("============");
            List<Station> stationList = service.selectStatName(station);
            System.out.println("stationList---"+stationList);
            JSONArray jsonArray = JSONArray.fromObject(stationList);
            System.out.println(jsonArray.toString());
            out.print(jsonArray);
        }
        //按员工号查询
        if ("selectEmploNum".equals(opt)){
            System.out.println("=========");
            List<User> userList = service.selectEmploNum(user);
            System.out.println("userList---"+userList);
            JSONArray jsonArray = JSONArray.fromObject(userList);
            System.out.println(jsonArray.toString());
            out.print(jsonArray);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
