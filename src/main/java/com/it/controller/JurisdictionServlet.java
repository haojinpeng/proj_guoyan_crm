package com.it.controller;


import com.it.bean.Jurisdiction;
import com.it.bean.PageBean;

import com.it.services.IJurisdictionServices;
import com.it.services.impl.JurisdictionServicesImpl;
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

@WebServlet(name = "JurisdictionServlet", value = "/jurisdiction.do")
public class JurisdictionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        //获取页面上的值
        String opt = request.getParameter("opt");
        System.out.println("opt==========" + opt);
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String path = request.getParameter("path");
        //page 当前页码
        String page = request.getParameter("page");
        //limit pageSize
        String rows = request.getParameter("limit");

        PrintWriter out = response.getWriter();

        //创建对象
        Jurisdiction jurisdiction = new Jurisdiction();
        JSONObject result = new JSONObject();

        // 存放集合内容
        List<Jurisdiction> jurisdictionList = null;

        // 判 "" null
        if (id != null && !"".equals(id)) {
            jurisdiction.setId(Long.parseLong(id));
        }
        if (name != null && !"".equals(name)) {
            jurisdiction.setName(name);
        }
        if (path != null && !"".equals(path)) {
            jurisdiction.setPath(path);
        }
        System.out.println("jurisdiction==========" + jurisdiction);

        //service
//        System.out.println("ssssssssssssssss");
        IJurisdictionServices jurisdictionServices = new JurisdictionServicesImpl();
        System.out.println("services");

        // 判别flag
        int flag = 0;
        System.out.println("flag");

        //增
        if ("add".equals(opt)) {
            System.out.println("===========add==========");
            flag = jurisdictionServices.add(jurisdiction);
            out.print(flag);
            return;
        }

        //删
        if ("delete".equals(opt)) {
            //获取前端页面的id集合
            String delIds = request.getParameter("delArray");
            System.out.println("delIds--->" + delIds);
            //{key:value,key:value....}
            int delNums = 0;
            //获取前端页面的Sno
            if (!"".equals(id) && id != null) {
                //先删除从表resources_jurisdiction

                //再删除主表jurisdiction
                jurisdictionServices.del(id);
                delNums = 1;
            } else {
                //先删除从表resources_jurisdiction

                //再删除主表jurisdiction
                delNums = jurisdictionServices.del(delIds);
            }
            out.print(delNums);
            return;
        }

        //改
        if ("update".equals(opt)) {
            try {
                flag = jurisdictionServices.upd(jurisdiction);
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(flag);
            return;
        }

        //模糊查询所有
        if ("queryAll".equals(opt)) {
            //查询所有信息默认分页
            System.out.println("111111111");
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            System.out.println("222222222");
            jurisdictionList = jurisdictionServices.queryAll(pageBean, jurisdiction);
            System.out.println("33333333333");
            //查询总记录数
            int total = jurisdictionServices.count(jurisdiction);
            System.out.println("total==========" + total);
            // jsonArray
            JSONArray jsonArray = JSONArray.fromObject(jurisdictionList);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
            return;
        }

        // 查询单个
        if ("select".equals(opt)) {
            try {
                Jurisdiction jurisdiction1 = jurisdictionServices.select(jurisdiction);
                if (jurisdiction1 != null) {
                    flag = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(flag);
            return;
        } else {
            System.out.println("无opt");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
