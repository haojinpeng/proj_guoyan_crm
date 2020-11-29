package com.it.controller;

import com.it.bean.*;
import com.it.services.IBTrackingServices;
import com.it.services.IBTrackingServicesImpl;
import com.it.services.IBusinessOppoServiceImpl;
import com.it.services.IBusinessOppoServices;
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

@WebServlet(name = "BTrackingServlet",urlPatterns = "/track.do")
public class BTrackingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //获取页面信息
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String business = request.getParameter("business");
        String type = request.getParameter("type");
        String createdate = request.getParameter("createdate");
        String recorder = request.getParameter("recorder");
        String message = request.getParameter("message");
        String feedback = request.getParameter("feedback");
        String remarks = request.getParameter("remarks");
        String busrecorder = request.getParameter("busrecorder");
        String date1 = request.getParameter("date");
        System.out.println("feedback_result-->" + feedback);
        //创建输出流
        PrintWriter out  = response.getWriter();
        //创建对象
        Business_tracking tracking = new Business_tracking();
        //链接services层
        IBTrackingServices trackingServices  = new IBTrackingServicesImpl();
        System.out.println("000");
        //判断 "" null
        if(!"".equals(business)&&business!=null){
            tracking.setBusiness(Integer.parseInt(business));
        }
        if(!"".equals(type)&&type!=null){
            tracking.setType(Integer.parseInt(type));
        }
        if(!"".equals(createdate)&&createdate!=null){
            tracking.setCreatedate(createdate);
        }
        if(!"".equals(recorder)&&recorder!=null){
            tracking.setRecorder(Integer.parseInt(recorder));
        }
        if(!"".equals(message)&&message!=null){
            tracking.setMessage(message);
        }
        if(!"".equals(feedback)&&feedback!=null){
            tracking.setFeedback_result(feedback);
        }
        if(!"".equals(remarks)&&remarks!=null){
            tracking.setRemarks(remarks);
        }
        System.out.println("tracking");
        //"1"成功  "0"失败
        String rs = "1";
        if("add".equals(opt)){
            boolean flag = trackingServices.add(tracking);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("update".equals(opt)){
            if(!"".equals(id)&&id!=null){
                tracking.setId(Integer.parseInt(id));
            }
            //判断
            boolean flag = trackingServices.upd(tracking);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("delete".equals(opt)){
            if(!"".equals(id)&&id!=null){
                tracking.setId(Integer.parseInt(id));
            }
            boolean flag = trackingServices.del(tracking);
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
                tracking.setId(Integer.parseInt(id));
                trackingServices.del(tracking);
                delNums = 1;
            }else{
                delNums = trackingServices.dels(delIds);
            }
            if (delNums > 0) {
                result.put("success", true);
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除失败");
            }

            out.print(result);

        }
        System.out.println(opt);
        if("search".equals(opt)){
            //page 当前页码
            System.out.println("3333");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            StringBuffer sql = new StringBuffer("SELECT t.*,b.name,bt.name AS type_name,u.username FROM business_tracking t,businessopporitunity b,business_type bt,`user` u WHERE t.business = b.id AND t.type = bt.id AND t.recorder = u.id and 1 = 1");
            StringBuffer sql1 = new StringBuffer("select count(*),b.name,bt.name AS type_name,u.username FROM business_tracking t,businessopporitunity b,business_type bt,`user` u WHERE t.business = b.id AND t.type = bt.id AND t.recorder = u.id and 1 = 1");
            if(tracking!=null){
                if(!"".equals(type)&&type!=null){
                    sql.append(" and type = "+type);
                    sql1.append(" and type= "+type);
                }
            }
            if(!"".equals(busrecorder)&&busrecorder!=null){
                sql.append(" and username like '%"+busrecorder+"%'");
                sql1.append(" and username like '%"+busrecorder+"%'");
            }
            System.out.println(sql);
            System.out.println(date1);
            if(!"".equals(date1)&&date1!=null){
                String[] date = date1.split(" ~ ");
                System.out.println(date[0]);
                System.out.println(date[1]);
                sql.append(" and createdate  >= '"+date[0] + "' and createdate <= '" + date[1] +"'");
                sql1.append(" and createdate >= '"+date[0] + "' and createdate <= '" + date[1] +"'");
            }
            System.out.println(sql);
            PageBean bean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
            if(!"".equals(page) && page != null){

                if(!"".equals(rows) && rows !=null){
                    sql.append(" limit " + bean.getStart() + ","
                            + rows);
                }
            }
            List<Business_tracking> trackings = trackingServices.queryAllLike(sql.toString());
            int total = 0;
            total = trackingServices.count(sql1.toString());
            System.out.println("total" + total);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(trackings);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
//            System.out.println(result.toString());
            out.print(result);

        }
        if("findById".equals(opt)){
            //page 当前页码
            System.out.println("3333");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            StringBuffer sql = new StringBuffer("SELECT t.*,b.name,bt.name AS type_name,u.username FROM business_tracking t,businessopporitunity b,business_type bt,`user` u WHERE t.business = b.id AND t.type = bt.id AND t.recorder = u.id and 1 = 1");
            if(!"".equals(id)&&id!=null){
                sql.append(" and b.id = "+id);
            }
            System.out.println(sql);
            System.out.println(date1);
            System.out.println(sql);
            PageBean bean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
            if(!"".equals(page) && page != null){

                if(!"".equals(rows) && rows !=null){
                    sql.append(" limit " + bean.getStart() + ","
                            + rows);
                }
            }
            List<Business_tracking> trackings = trackingServices.queryAllLike(sql.toString());
            JSONArray jsonArray = JSONArray.fromObject(trackings);
//            System.out.println(result.toString());
            out.print(jsonArray);

        }
        if("queryType".equals(opt)){
            List<Business_type> business_typeList = trackingServices.queryType();
            JSONArray jsonArray = JSONArray.fromObject(business_typeList);
            out.print(jsonArray.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
