package com.it.controller;

import com.it.bean.*;
import com.it.services.IBusinessOppoServices;
import com.it.services.IBusinessOppoServiceImpl;
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

@WebServlet(name = "BusinessOppoServlet",urlPatterns = "/bus.do")
public class BusinessOppoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //获取页面信息
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String customers_id = request.getParameter("customers_id");
        String business_type_id = request.getParameter("business_type_id");
        String contact = request.getParameter("contact");
        String intention = request.getParameter("intention");
        String userid = request.getParameter("userid");
        String remark = request.getParameter("remarks");
        //创建输出流
        PrintWriter out  = response.getWriter();
        //创建对象
        BusinessOpporitunity businessOppo = new BusinessOpporitunity();
        //链接services层
        IBusinessOppoServices businessOppoServices = new IBusinessOppoServiceImpl();
        System.out.println("000");
        //判断 "" null
        if(!"".equals(name)&&name!=null){
            businessOppo.setName(name);
        }
        if(!"".equals(status)&&status!=null){
            businessOppo.setStatus(Integer.parseInt(status));
        }
        if(!"".equals(customers_id)&&customers_id!=null){
            businessOppo.setCustomers_id(Integer.parseInt(customers_id));
        }
        if(!"".equals(business_type_id)&&business_type_id!=null){
            businessOppo.setBusiness_type_id(Integer.parseInt(business_type_id));
        }
        if(!"".equals(contact)&&contact!=null){
            businessOppo.setContact(contact);
        }

        if(!"".equals(intention)&&intention!=null){
            businessOppo.setIntention(Integer.parseInt(intention));
        }
        if(!"".equals(userid)&&userid!=null){
            businessOppo.setUserid(Integer.parseInt(userid));
        }
        if(!"".equals(remark)&&remark!=null){
            businessOppo.setRemarks(remark);
        }
        System.out.println("11111");
        //"1"成功  "0"失败
        String rs = "1";
        if("add".equals(opt)){

            if(!"".equals(remark)&&remark!=null){
                businessOppo.setRemarks(remark);
            }
            boolean flag = businessOppoServices.add(businessOppo);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("update".equals(opt)){
            if(!"".equals(id)&&id!=null){
                businessOppo.setId(Integer.parseInt(id));
            }
            //判断
            boolean flag = businessOppoServices.upd(businessOppo);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("delete".equals(opt)){
            if(!"".equals(id)&&id!=null){
                businessOppo.setId(Integer.parseInt(id));
            }
            boolean flag = businessOppoServices.del(businessOppo);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("deletes".equals(opt)){
            //获取前端页面的id集合
            String delIds = request.getParameter("delArray");

            System.out.println("delIds--->"+delIds);
            //{key:value,key:value....}
            JSONObject result = new JSONObject();
            int delNums = 0;
            //获取前端页面的Sno
            if(!"".equals(id)&& id!=null){
                businessOppo.setId(Integer.parseInt(id));
                businessOppoServices.del(businessOppo);
                delNums = 1;
            }else{
                delNums = businessOppoServices.dels(delIds);
            }
            if (delNums > 0) {
                result.put("success", true);
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除失败");
            }

            out.print(result);

        }
        if("findById".equals(opt)){
            //page 当前页码
            System.out.println("3333");
            StringBuffer sql = new StringBuffer("SELECT b.id,c.customer_name as customer_name,b.`name`,b.business_type_id,b.contact,b.`status`,b.customers_id,t.`name` AS type_name,b.intention,GROUP_CONCAT(ct.`name`,\" : \",ct.phone)  AS contactman,u.username,b.remarks\n" +
                    "FROM businessopporitunity b,customer c,business_type t,contact ct,`user` u \n" +
                    "WHERE b.contact = ct.id\n" +
                    "AND b.business_type_id = t.id \n" +
                    "AND b.userid = u.id \n" +
                    "AND b.customers_id = c.id\n" +
                    " AND 1=1");
            if(!"".equals(id)&& id!=null){
               sql.append(" and b.id = "+id);
            }
            List<BusinessOpporitunity> businessOppo1 = businessOppoServices.queryAllLike(sql.toString());
            JSONArray jsonArray = JSONArray.fromObject(businessOppo1);
            System.out.println("jsonArray-->" +  jsonArray);
            out.print(jsonArray);
        }
        if("query".equals(opt)){
            List<BusinessOpporitunity> business_typeList = businessOppoServices.query();
            JSONArray jsonArray = JSONArray.fromObject(business_typeList);
            out.print(jsonArray);
        }
        System.out.println(opt);
        if("search".equals(opt)){
            //page 当前页码
            System.out.println("3333");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            StringBuffer sql = new StringBuffer("SELECT b.id,b.`name`,b.business_type_id,b.contact,b.`status`,b.customers_id,t.`name` AS type_name,b.intention,GROUP_CONCAT(ct.`name`,\" : \",ct.phone)  AS contactman,u.username,b.remarks\n" +
                    "FROM businessopporitunity b,business_type t,contact ct,`user` u \n" +
                    "WHERE b.contact = ct.id\n" +
                    "AND b.business_type_id = t.id \n" +
                    "AND b.userid = u.id \n" +
                    " AND 1=1");
            StringBuffer sql1 = new StringBuffer("select count(*) from businessopporitunity where 1=1");
            if(businessOppo!=null){
                if(!"".equals(name)&&name!=null){
                    sql.append(" and b.name like '%"+name+"%'");
                    sql1.append(" and name like '%"+name+"%'");
                }
                if(!"".equals(intention)&&intention!=null){
                    sql.append(" and intention = '"+intention+"'");
                    sql1.append(" and intention = '"+intention+"'");
                }
                if(!"".equals(status)&&status!=null){
                    sql.append(" and status  = "+status);
                    sql1.append(" and status  = "+status);
                }
            }
            sql.append(" GROUP BY b.id");
            PageBean bean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
            if(!"".equals(page) && page!=null){

                if(!"".equals(rows) && rows !=null){
                    sql.append(" limit " + bean.getStart() + ","
                            + rows);
                }
            }

            List<BusinessOpporitunity> businessOppo1 = businessOppoServices.queryAllLike(sql.toString());
            int total = 0;
            total = businessOppoServices.count(sql1.toString());
            System.out.println("total" + total);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(businessOppo1);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
//            System.out.println(result.toString());
            out.print(result);

        }

        if("queryType".equals(opt)){
            List<Business_type> business_typeList = businessOppoServices.queryType();
            JSONArray jsonArray = JSONArray.fromObject(business_typeList);
            out.print(jsonArray.toString());
        }
        if("queryContact".equals(opt)){
            List<Business_contact> contacts = businessOppoServices.queryContact();
            JSONArray jsonArray = JSONArray.fromObject(contacts);
            out.print(jsonArray.toString());
        }
        if("queryUser".equals(opt)){
            List<Business_User> business_typeList = businessOppoServices.queryUser();
            JSONArray jsonArray = JSONArray.fromObject(business_typeList);
            out.print(jsonArray.toString());
        }
        if("queryFid".equals(opt)){
            List<Business_type> business_typeList = businessOppoServices.queryFid();
            JSONArray jsonArray = JSONArray.fromObject(business_typeList);
            out.print(jsonArray.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
