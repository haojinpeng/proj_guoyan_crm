package com.it.controller;

import com.it.bean.WorkSpaces;
import com.it.services.IWorkspacesServices;
import com.it.services.IWorkspacesServicesImpl;
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

@WebServlet(name = "WorkServlet",urlPatterns = "/work.do")
public class WorkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //获取页面信息
        String opt = request.getParameter("opt");
        PrintWriter out = response.getWriter();
        IWorkspacesServices workspacesServices = new IWorkspacesServicesImpl();
        if("query".equals(opt)){
            List<WorkSpaces> workSpaces = workspacesServices.query();
            System.out.println(workSpaces);
            JSONObject result1 = new JSONObject();
            JSONArray result = JSONArray.fromObject(workSpaces);
            result1.put("code",0);
            result1.put("msg","正在加载....");
            result1.put("data",result);
            out.print(result1);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
