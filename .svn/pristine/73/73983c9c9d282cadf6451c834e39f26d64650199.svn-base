package com.it.controller;

import com.it.bean.Business_Check;
import com.it.bean.Business_cost;
import com.it.bean.PageBean;
import com.it.bean.User;
import com.it.services.IBCheckServices;
import com.it.services.IBCheckServicesImpl;
import com.it.services.IBCostServices;
import com.it.services.IBCostServicesImpl;
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
import java.util.UUID;

@WebServlet(name = "BCheckServlet",urlPatterns = "/buscheck.do")
public class BCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码格式
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //获取页面信息
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String cost_id = request.getParameter("cost_id");
        String userp = request.getParameter("userp");
        String check_condition = request.getParameter("check_condition");
        String check_idea = request.getParameter("check_idea");
        String check_date = request.getParameter("check_date");
        String remarks = request.getParameter("remarks");
        String business_name = request.getParameter("business_name");
        String status = request.getParameter("status");
        String business_id = request.getParameter("business");

        //创建输出流
        PrintWriter out  = response.getWriter();
        //创建对象
        Business_Check check = new Business_Check();
        Business_cost cost = new Business_cost();
        IBCostServices costServices = new IBCostServicesImpl();
        //链接services层
        IBCheckServices checkServices = new IBCheckServicesImpl();
        System.out.println("000");
        //判断 "" null
        if(!"".equals(cost_id)&&cost_id!=null){
            check.setCost_id(Integer.parseInt(cost_id));
            cost.setId(Integer.parseInt(cost_id));
        }
        if(!"".equals(userp)&&userp!=null){
            check.setUserp(Integer.parseInt(userp));
        }
        if(!"".equals(check_condition)&&check_condition!=null){
            check.setCheck_condition(Integer.parseInt(check_condition));
        }
        if(!"".equals(check_idea)&&check_idea!=null){
            check.setCheck_idea(check_idea);
        }
        if(!"".equals(check_date)&&check_date!=null){
            check.setCheck_date(check_date);
        }
        if(!"".equals(remarks)&&remarks!=null){
            check.setRemarks(remarks);
        }if(!"".equals(business_id)&&business_id!=null){
            check.setBusiness_id(Integer.parseInt(business_id));
        }
        System.out.println("buss-->"+business_id);
        System.out.println("tracking");
        //"1"成功  "0"失败
        String rs = "1";
        if("add".equals(opt)){
            String no = UUID.randomUUID().toString();
            System.out.println(no);
            check.setId(Integer.parseInt(no));
            boolean flag = checkServices.add(check);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("update".equals(opt)){
            if("2".equals(check_condition)){
                if(!"".equals(id)&&id!=null){
                    check.setId(Integer.parseInt(id));
                }
                System.out.println(id);
//            String login_name = request.getSession().getAttribute("login_name").toString();
                String login_name = "admin2";
                //判断
                System.out.println("1111");
                boolean flag = checkServices.upd(check,login_name);
                if(!flag){
                    rs="0";
                }
                out.print(rs);
            }else if("1".equals(check_condition)){
                if(!"".equals(id)&&id!=null){
                    check.setId(Integer.parseInt(id));
                }
                System.out.println(id);
//            String login_name = request.getSession().getAttribute("login_name").toString();
                String login_name = "admin2";
                List<User> userList = checkServices.queryAllProcess(login_name);
                if(userList!=null){
                    //判断
                    System.out.println("1111");
                    boolean flag = checkServices.upd(check,login_name);
                    boolean flag1 = checkServices.add(check);
                    if(!flag && !flag1){
                        rs="0";
                    }
                    out.print(rs);
                }else{
                    System.out.println("返回值为null");
                    cost.setAudit_status(1);
                    boolean flag1 = costServices.update(cost);
                    //判断
                    System.out.println("1111");
                    boolean flag = checkServices.upd(check,login_name);
                    if(!flag && !flag1){
                        rs="0";
                    }
                    out.print(rs);
                }

            }

        }
        if("delete".equals(opt)){
            if(!"".equals(id)&&id!=null){
                check.setId(Integer.parseInt(id));
            }
            boolean flag = checkServices.del(check);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        System.out.println(opt);
        if("search".equals(opt)){
            /*String login_name = request.getSession().getAttribute("login_name");*/
            String login_name = "admin2";
            //page 当前页码
            System.out.println("3333");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            StringBuffer sql = new StringBuffer("SELECT c.*,b.name as business_name,u.username as username from cost_check c,`user` u,businessopporitunity b WHERE c.userp = u.id and c.business_id = b.id and 1 = 1");
            StringBuffer sql1 = new StringBuffer("SELECT count(*),b.name as business_name from cost_check c,`user` u,businessopporitunity b WHERE c.userp = u.id and c.business_id = b.id and 1=1");
            if(!"".equals(business_name)&&business_name!=null){
                sql.append(" and b.name like '%"+business_name+"%'");
                sql1.append(" and b.name like '%"+business_name+"%'");
            }
            if(!"".equals(status)&&status!=null){
                sql.append(" and check_condition  = "+status);
                sql1.append(" and check_condition  = "+status);
            }
            sql.append( " and u.login_name = '"+login_name +"'");
            sql1.append(" and u.login_name = '"+login_name +"'");
            PageBean bean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
            if(!"".equals(page) && page!=null){

                if(!"".equals(rows) && rows !=null){
                    sql.append(" limit " + bean.getStart() + ","
                            + rows);
                }
            }
            List<Business_Check> costs = checkServices.queryAllLike(sql.toString());
            int total = 0;
            total = checkServices.count(sql1.toString());
            System.out.println("total" + total);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(costs);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
//            System.out.println(result.toString());
            out.print(result);

        }
        if("checkUser".equals(opt)){
            /*String loginname = (String)request.getSession().getAttribute("loginname");*/
               String loginname = "admin2";
            List<User> userList = checkServices.queryAllProcess(loginname);
            if(userList!=null){
                JSONArray jsonArray = JSONArray.fromObject(userList);
                System.out.println(jsonArray.toString());
                out.print(jsonArray);
            }else{
                System.out.println("返回值为null");
                rs = "0";
                out.print(rs);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
