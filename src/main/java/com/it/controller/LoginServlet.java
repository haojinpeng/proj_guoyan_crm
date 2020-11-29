package com.it.controller;

import com.it.bean.Resource;
import com.it.bean.Resources;
import com.it.bean.User;
import com.it.services.*;
import com.it.services.impl.ResourceServicesImpl;
import com.it.utils.MD5Util;
import com.it.utils.ParentAndChildrenSort;
import com.it.utils.ResourceParentSort;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpSession;


@WebServlet(value = "/login.html")
public class LoginServlet extends HttpServlet {

    private IUserServices userService = new UserServicesImpl();

    private ResourcesService resourcesService = new ResourcesServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login_name = req.getParameter("login_name");
        String password = req.getParameter("password");
        System.out.println(login_name);
        User user = userService.findUserByLogin_Name(login_name);

        if(user !=null && user.getPassword().equals(MD5Util.getMD5(password,"assfs","UTF-8"))){

            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);

//            List<Resources> resourcesList = new ParentAndChildrenSort<Resources>().sortList(resourcesService.findResourcesByUserId(user));
//           session.setAttribute("resorces",resourcesList);
            //需要改的代码
            System.out.println("user==========" +user);
            IResourceSercices resourceSercices=new ResourceServicesImpl();
            List<Resource> resourcesList = new ResourceParentSort<Resource>().sortList(resourceSercices.findResourcesByUserId(user));
            for (Resource re:resourcesList
            ){
                System.out.println("resssssss=="+re);
            }
            session.setAttribute("resources",resourcesList);
            resp.getWriter().print("success");

        }else{
            resp.getWriter().print("error");
        }
        resp.getWriter().flush();
        resp.getWriter().close();

    }
}
