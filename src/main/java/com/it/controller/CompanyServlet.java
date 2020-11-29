package com.it.controller;

import com.it.bean.Company;
import com.it.bean.PageBean;
import com.it.services.CompanyService;
import com.it.services.CompanyServicesImpl;
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

@WebServlet(name = "CompanyServlet",urlPatterns = {"/company.do"})
public class CompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理乱码
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();

        //获取页面信息
        String opt = request.getParameter("opt");
        String company_id = request.getParameter("company_id");
        String company_type = request.getParameter("company_type");

        //生成对象
        Company company = new Company();

        //servces
        CompanyService companyService = new CompanyServicesImpl();

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
            if (!"".equals(company_id) && company_id != null) {
                company.setCompany_id(Long.parseLong(company_id));
            }
            if (!"".equals(company_type) && company_type != null) {
                company.setCompany_type(company_type);
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));


            // 执行 查询所有信息默认分页
            List<Company> CompanyList = companyService.queryAllCompany(pageBean,company);

            System.out.println("servlet-->" + CompanyList);


            //查询总记录数
            int total = companyService.count();

            JSONObject result = new JSONObject();

            //转成jsonarray
            JSONArray jsonArray = JSONArray.fromObject(CompanyList);

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

            if (!"".equals(company_id) && company_id != null) {
                company.setCompany_id(Long.parseLong(company_id));
            }
            if (!"".equals(company_type) && company_type != null) {
                company.setCompany_type(company_type);
            }


            boolean flag = companyService.addCompany(company);
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
            if (!"".equals(company_id) && company_id != null) {
                company.setCompany_id(Long.parseLong(company_id));
            }

            boolean flag = companyService.deleteCompany(company);

            if (!flag) {
                rs = "0";
            }
            out.print(rs);
        }
        // 修改操作
        if ("upd".equals(opt)) {
            // 处理数据类型 “” null

            if (!"".equals(company_id) && company_id != null) {
                company.setCompany_id(Long.parseLong(company_id));
            }
            if (!"".equals(company_type) && company_type != null) {
                company.setCompany_type(company_type);
            }
            if (!"".equals(company_id) && company_id != null) {
                company.setCompany_id(Long.parseLong(company_id));
            }
            JSONObject result = new JSONObject();

            //执行
            boolean flag = companyService.updateCompany(company);
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
