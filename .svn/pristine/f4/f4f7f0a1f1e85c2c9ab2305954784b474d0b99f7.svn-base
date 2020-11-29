package com.it.controller;

import com.it.bean.Business_Check;
import com.it.bean.Business_cost;
import com.it.bean.Business_tracking;
import com.it.bean.PageBean;
import com.it.dao.IBTrackingDao;
import com.it.services.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "BCostServlet",urlPatterns = "/buscost.do")
public class BCostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码格式
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //获取页面信息
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String business_id = request.getParameter("business_id");
        String business_tracking = request.getParameter("business_tracking");
        String conusme_type = request.getParameter("consume_type");
        String reality_consume = request.getParameter("reality_consume");
        String perdict_consume = request.getParameter("perdict");
        String audit_status = request.getParameter("audit_status ");
        String cost_declarant = request.getParameter("cost_declarant");
        String cost_description = request.getParameter("cost_description");
        System.out.println("perdict_consume" + perdict_consume);
        //创建输出流
        PrintWriter out  = response.getWriter();
        //创建对象
        Business_cost cost = new Business_cost();
        Business_Check check = new Business_Check();
        //链接services层
        IBCostServices costServices = new IBCostServicesImpl();
        IBCheckServices checkServices = new IBCheckServicesImpl();
        System.out.println("000");
        //判断 "" null
        if(!"".equals(business_id)&&business_id!=null){
            cost.setBusiness_id(Integer.parseInt(business_id));
            check.setBusiness_id(Integer.parseInt(business_id));
        }
        if(!"".equals(business_tracking)&&business_tracking!=null){
            cost.setBusiness_tracking(Integer.parseInt(business_tracking));
        }
        if(!"".equals(conusme_type)&&conusme_type!=null){
            cost.setConsume_type(Integer.parseInt(conusme_type));
        }
        if(!"".equals(reality_consume)&&reality_consume!=null){
            cost.setReality_consume(Double.parseDouble(reality_consume));
        }
        if(!"".equals(perdict_consume)&&perdict_consume!=null){
            cost.setPerdict_consume(Double.parseDouble(perdict_consume));
        }
        if(!"".equals(audit_status)&&audit_status!=null){
            cost.setAudit_status(Integer.parseInt(audit_status));
        }
        if(!"".equals(cost_declarant)&&cost_declarant!=null){
            cost.setCost_declarant(Integer.parseInt(cost_declarant));
            check.setUserp(Integer.parseInt(cost_declarant));
        }
        if(!"".equals(cost_description)&&cost_description!=null){
            cost.setCost_description(cost_description);
        }
        System.out.println("tracking");
        //"1"成功  "0"失败
        String rs = "1";
        if("add".equals(opt)){
           /* UUID uuid=UUID.randomUUID();
            String str = uuid.toString();
            String uuidStr=str.replace("-", "");*/
            String orderNo = "" ;
            String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
            String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
            orderNo = trandNo.substring(0, 7);
            System.out.println(orderNo);
            cost.setId(Integer.parseInt(orderNo));
            check.setCost_id(Integer.parseInt(orderNo));
            System.out.println(cost);
            boolean flag = costServices.add(cost);
            boolean flag1 = checkServices.addCheck(check);
            if(!flag && !flag1){
                rs="0";
            }
            out.print(rs);
        }
        if("update".equals(opt)){
            if(!"".equals(id)&&id!=null){
                cost.setId(Integer.parseInt(id));
            }
            //判断
            boolean flag = costServices.upd(cost);
            if(!flag){
                rs="0";
            }
            out.print(rs);
        }
        if("delete".equals(opt)){
            if(!"".equals(id)&&id!=null){
                cost.setId(Integer.parseInt(id));
            }
            boolean flag = costServices.del(cost);
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
                cost.setId(Integer.parseInt(id));
                costServices.del(cost);
                delNums = 1;
            }else{
                delNums = costServices.dels(delIds);
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
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            StringBuffer sql = new StringBuffer("SELECT bc.*,t.`name` as type_name,b.`name` as business_name,u.username as username " +
                    "from businessopporitunity_cost bc,businessopporitunity b,`user` u,business_tracking bt,business_type t " +
                    "WHERE bc.business_id = b.id and bc.consume_type = t.id and bc.business_tracking = bt.id and bc.cost_declarant = u.id and 1 = 1");
            StringBuffer sql1 = new StringBuffer("SELECT count(*) " +
                    "from businessopporitunity_cost bc,businessopporitunity b,`user` u,business_tracking bt,business_type t " +
                    "WHERE bc.business_id = b.id and bc.consume_type = t.id and bc.business_tracking = bt.id and bc.cost_declarant = u.id and 1 = 1");
            if(cost!=null){
                if(!"".equals(business_id)&&business_id!=null){
                    sql.append(" and business_name like '%"+business_id+"%'");
                    sql1.append(" and business_name like '%"+business_id+"%'");
                }
//                if(!"".equals(status)&&status!=null){
//                    sql.append(" and status  = "+status);
//                    sql1.append(" and status  = "+status);
//                }
            }
            PageBean bean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
            if(!"".equals(page) && page!=null){

                if(!"".equals(rows) && rows !=null){
                    sql.append(" limit " + bean.getStart() + ","
                            + rows);
                }
            }
            List<Business_cost> costs = costServices.queryAllLike(sql.toString());
            int total = 0;
            total = costServices.count(sql1.toString());
            System.out.println("total" + total);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(costs);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result.toString());

        }
        if("queryAll".equals(opt)){
            StringBuffer sql = new StringBuffer("SELECT bc.*,t.`name` as type_name,b.`name` as business_name,u.username as username " +
                    "from businessopporitunity_cost bc,businessopporitunity b,`user` u,business_tracking bt,business_type t " +
                    "WHERE bc.business_id = b.id and bc.consume_type = t.id and bc.business_tracking = bt.id and bc.cost_declarant = u.id and 1 = 1");
            List<Business_cost> costs = costServices.queryAllLike(sql.toString());
            JSONArray jsonArray = JSONArray.fromObject(costs);
            out.print(jsonArray.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
