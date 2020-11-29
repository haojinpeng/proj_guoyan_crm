package com.it.controller;

import com.it.bean.PageBean;
import com.it.bean.Wvo;
import com.it.services.WorkorderServices;
import com.it.services.WorkorderServicesImpl;
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

@WebServlet(name = "WorkorderServlets",urlPatterns = {"/workorder.do"})
public class WorkorderServlets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");

        //获取页面的值
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String customer_name = request.getParameter("customer_name");
        String number = request.getParameter("number");
        String project_name = request.getParameter("project_name");
        String receive_time = request.getParameter("receive_time");
        String require_time = request.getParameter("require_time");
        String username = request.getParameter("username");
        String status = request.getParameter("status");
        String problem_description = request.getParameter("problem_description");


        String problem_solve = request.getParameter("problem_solve");
        String recive_uer = request.getParameter("recive_uer");
        String remarks = request.getParameter("remarks");


        //测试
        System.out.println(opt);
        System.out.println("id："+id);
        System.out.println("customer_name："+customer_name);
        System.out.println("number："+number);
        System.out.println("project_name："+project_name);
        System.out.println("receive_time："+receive_time);
        System.out.println("require_time："+require_time);
        System.out.println("username："+username);
        System.out.println("status："+status);
        System.out.println("problem_description："+problem_description);

        System.out.println("problem_solve："+problem_solve);
        System.out.println("recive_uer："+recive_uer);
        System.out.println("remarks："+remarks);


        //创建对象
        Wvo wvo = new Wvo();
        WorkorderServices workorderServices = new WorkorderServicesImpl();
        PrintWriter out = response.getWriter();

        if("queryall".equals(opt)){
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");

            List<Wvo> list = null;
            //page 当前页码
            System.out.println("没进去");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            if(!"".equals(status) && status!=null){
                wvo.setStatus(Integer.valueOf(status));
            }

            if (!"".equals(project_name) && project_name!=null){
                wvo.setProject_name(project_name);
            }
            //查询所有信息默认分页
            list = workorderServices.queryalllike(pageBean,wvo,startTime,endTime);
            System.out.println(list.size());
            //查询总记录数
            int total = workorderServices.count(wvo);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(list);
            result.put("code", 0);
            result.put("msg", "正在加载……");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);


        }

        if("queryalldoit".equals(opt)){

            List<Wvo> list = null;
            //page 当前页码
            System.out.println("没进去");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            if(!"".equals(status) && status!=null){
                wvo.setStatus(Integer.valueOf(status));
            }

            if(!"".equals(number) && number!=null){
                wvo.setNumber(number);
            }
            //查询所有信息默认分页
            list = workorderServices.queryall(pageBean,wvo);
            System.out.println(list.size());
            //查询总记录数
            int total = workorderServices.count2(wvo);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(list);
            result.put("code", 0);
            result.put("msg", "正在加载……");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);


        }

        if ("add".equals(opt)){

            //"1"成功  "0"失败
            String rs = "1";
            if(customer_name != null && !"".equals(customer_name)){
                wvo.setCustomer_name(customer_name);
            }
            if(project_name!=null && !"".equals(project_name)){
                wvo.setProject_name(project_name);
            }
            if(receive_time!=null && !"".equals(receive_time)){
                wvo.setReceive_time(receive_time);
            }
            if (require_time!= null && !"".equals(require_time)){
                wvo.setRequire_time(require_time);
            }
            if(username!=null &&!"".equals(username)){
                wvo.setUsername(username);
            }
            if(!"".equals(status) && status!=null){
                wvo.setStatus(Integer.valueOf(status));
            }
            if(!"problem_description".equals("") && "problem_description"!=null){
                wvo.setProblem_description(problem_description);
            }

            boolean flag = workorderServices.add(wvo);

            if(!flag){
                 rs="0";
            }

            System.out.println("rs:"+rs);
            out.print(rs);
        }

        if("doshow".equals(opt)){
           String str = request.getParameter("number2");
            List<Wvo> list = null;
            list = workorderServices.queryall(str);
            System.out.println(list.size());
            JSONArray jsonArray = JSONArray.fromObject(list);
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
        }

        if("findproj".equals(opt)){
            List<Wvo> list = null;
            list = workorderServices.findprojname();
            JSONArray jsonArray = JSONArray.fromObject(list);
            System.out.println(list);
            out.print(jsonArray);


        }


        if("finduser".equals(opt)){
            List<Wvo> list = null;
            list = workorderServices.findusertname();
            JSONArray jsonArray = JSONArray.fromObject(list);
            out.print(jsonArray);



        }
        if("findcustomer".equals(opt)){
            List<Wvo> list = null;
            list = workorderServices.findcustomername();
            JSONArray jsonArray = JSONArray.fromObject(list);
            out.print(jsonArray);



        }

        if("update".equals(opt)){

            String rs = "1";
            if(username!=null &&!"".equals(username)){
                wvo.setUsername(username);
            }
            if(!"".equals(status) && status!=null){
                wvo.setStatus(Integer.valueOf(status));
            }
            if(number!=null && !"".equals(number)){
                wvo.setNumber(number);
            }
            boolean flag = workorderServices.update(wvo);

            if(!flag){
                rs="0";
            }

            System.out.println("id"+id+"username"+username+"status"+status);
            System.out.println("rs:"+rs);
            out.print(rs);
        }
        if("queryallpj".equals(opt)){
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");

            List<Wvo> list = null;
            //page 当前页码
            System.out.println("没进去");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));



            if (!"".equals(customer_name) && customer_name!=null){
                wvo.setCustomer_name(customer_name);
            }
            //查询所有信息默认分页
            list = workorderServices.queryallpj(pageBean,wvo,startTime,endTime);
            System.out.println(list.size());
            //查询总记录数
            int total = workorderServices.count3(wvo);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(list);
            result.put("code", 0);
            result.put("msg", "正在加载……");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);


        }
        if("updatepj".equals(opt)){

            String rs = "1";
            String evaluate = request.getParameter("evaluate");
            String suggest = request.getParameter("suggest");

            System.out.println(evaluate+"    "+suggest+"           "+number);

            if (evaluate != null && !"".equals(evaluate)){
                wvo.setEvaluate(Integer.valueOf(evaluate));
            }
            if(suggest!=null && !"".equals(suggest)){
                wvo.setSuggest(suggest);
            }
            if(number!=null && !"".equals(number)){
                wvo.setNumber(number);
            }
            boolean flag = workorderServices.updatepj(wvo);

            if(!flag){
                rs="0";
            }

            out.print(rs);


        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
