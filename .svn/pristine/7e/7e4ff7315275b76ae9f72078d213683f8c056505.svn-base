package com.it.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.it.bean.Resources;
import com.it.bean.User;
import com.it.services.ResourcesService;
import com.it.services.ResourcesServicesImpl;
import com.it.utils.ParentAndChildrenSort;

import net.sf.json.JSONObject;

@WebServlet(value = "/resources.html")
public class ResourcesServlet extends HttpServlet {

    private ResourcesService resourcesService = new ResourcesServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理乱码
        resp.setContentType("application/json; charset=utf-8");
        User user1 = (User)req.getSession().getAttribute("user");
        if( req.getSession().getAttribute("user")!=null){
            JSONObject object = new JSONObject();
            JSONObject object1 = new JSONObject();
            JSONObject object2 = new JSONObject();
            object2.put("title","CRM管理系统");
            if(req.getSession().getAttribute("resources")!=null) {
                object2.put("child",req.getSession().getAttribute("resources"));

            }else{
                object2.put("child",new ParentAndChildrenSort<Resources>().sortList(resourcesService.findResourcesByUserId((User) req.getSession().getAttribute("user"))));
            }
            object1.put("currency",object2);
            object.put("menuInfo",object1);
            resp.getWriter().print(object);
            resp.getWriter().flush();
            resp.getWriter().close();
        }else{
            resp.sendRedirect("/page/login-1.html");
        }

    }
}
