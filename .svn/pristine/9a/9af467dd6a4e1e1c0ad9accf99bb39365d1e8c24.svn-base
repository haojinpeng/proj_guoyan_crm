package com.it.controller;

import com.it.bean.User;
import com.it.services.IUserServices;
import com.it.services.UserServicesImpl;
import com.it.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdateServlet",urlPatterns = {"/update.do"})
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        String id = request.getParameter("id");
        String old_password = request.getParameter("old_password");
        String password = request.getParameter("password");
        String again_password = request.getParameter("again_password");
        String opt = request.getParameter("opt");
        System.out.println("进去servlet--->");
        System.out.println(old_password);
        System.out.println("new_password--->"+password);
        User user = new User();
        //生成services
        IUserServices services = new UserServicesImpl();
        //生成session进行会话跟踪
        HttpSession session = request.getSession();
        User user1=(User)session.getAttribute("user");

        String idd = String.valueOf(user1.getId());
        System.out.println("idd=======>"+idd);
        if("upd".equals(opt)){
            //""null
            if(!"".equals(idd)&&idd!=null){
                user.setId(Integer.parseInt(idd));
            }

            if(!"".equals(password)&&password!=null){
                user.setPassword(MD5Util.getMD5(password,"assfs","UTF-8"));
            }
            System.out.println("快进去service");
            try{
                boolean flag = services.upd(user);
                if(flag){
                    session.setAttribute("user",user);
                    response.sendRedirect("index.html");
                }else {
                    response.sendRedirect("Login.html");
                }
            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("Login.html");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
    }
}
