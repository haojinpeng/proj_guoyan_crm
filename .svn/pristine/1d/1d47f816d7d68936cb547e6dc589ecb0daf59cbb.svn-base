package com.it.controller;

import com.it.bean.PageBean;
import com.it.bean.Procurement;
import com.it.services.ProcurementServices;
import com.it.services.ProcurementServicesImpl;
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

@WebServlet(name = "ProcurementServlet",urlPatterns = {"/procurement.do"})
public class ProcurementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理乱码
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();

        //获取页面信息
        String opt = request.getParameter("opt");
        String procurement_id = request.getParameter("procurement_id");
        String procurement_type = request.getParameter("procurement_type");
        String choose = request.getParameter("choose");
        //生成对象
        Procurement procurement = new Procurement();

        //servces
        ProcurementServices procurementServices = new ProcurementServicesImpl();

        //成功1  , 失败0
        String rs = "1";

        /**
         * 查询所有
         * */
        if ("queryAll".equals(opt)) {
            //page 当前页码
            String page = request.getParameter("page");

            // pagesize
            String rows = request.getParameter("limit");
            // "" null
            if (!"".equals(procurement_id) && procurement_id != null) {
                procurement.setProcurement_id(procurement_id);
            }
            if (!"".equals(procurement_type) && procurement_type != null) {
                procurement.setProcurement_type(procurement_type);
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));


            // 执行 查询所有信息默认分页
            List<Procurement> ProcurementList = procurementServices.queryAllProcurement(pageBean,procurement);

            System.out.println("servlet-->" + ProcurementList);


            //查询总记录数
            int total = procurementServices.count();

            JSONObject result = new JSONObject();

            //转成jsonarray
            JSONArray jsonArray = JSONArray.fromObject(ProcurementList);

            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());

            //发送到前端
            out.print(result);

        }

        /**
         * 添加方法
         * */
        if ("add".equals(opt)) {

            if (!"".equals(procurement_id) && procurement_id != null) {
                procurement.setProcurement_id(procurement_id);
            }
            if (!"".equals(procurement_type) && procurement_type != null) {
                procurement.setProcurement_type(procurement_type);
            }
           /* if (!"".equals(choose) && choose != null) {
                procurement.setChoose(choose);
            }*/

            boolean flag = procurementServices.addProcurement(procurement);
            if (!flag) {
                rs = "0";
            }

            System.out.println("结果-->" + rs);
            //发送到前端
            out.print(rs);

        }

        /**
         * 删除单个
         * */
        if ("del".equals(opt)) {
            //获取前端页面的sno
            //判断
            if (!"".equals(procurement_id) && procurement_id != null) {
                procurement.setProcurement_id(procurement_id);
            }

            boolean flag = procurementServices.deleteProcurement(procurement);

            if (!flag) {
                rs = "0";
            }
            out.print(rs);
        }
        // 修改操作
        if ("upd".equals(opt)) {
            // 处理数据类型 “” null

            if (!"".equals(procurement_id) && procurement_id != null) {
                procurement.setProcurement_id(procurement_id);
            }
            if (!"".equals(procurement_type) && procurement_type != null) {
                procurement.setProcurement_type(procurement_type);
            }
            /*if (!"".equals(choose) && choose != null) {
                procurement.setChoose(choose);
            }*/
            if (!"".equals(procurement_id) && procurement_id != null) {
                procurement.setProcurement_id(procurement_id);
            }
            JSONObject result = new JSONObject();

            //执行
            boolean flag = procurementServices.updateProcurement(procurement);
            if (!flag) {
                rs = "0";
            }
            System.out.println("结果-->" + rs);
            out.print(rs);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
