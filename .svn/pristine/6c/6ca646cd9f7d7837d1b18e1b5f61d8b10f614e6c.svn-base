package com.it.controller;

import com.it.bean.*;
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
import java.util.List;

@WebServlet(name = "CustomerServlet",urlPatterns = {"/customer.do"})
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        //获取页面上的值
        String opt=request.getParameter("opt");
        String id = request.getParameter("id");
        String customer_name = request.getParameter("customer_name");
        String c_name = request.getParameter("c_name");
        String address = request.getParameter("address");
        String clientType = request.getParameter("clientType");
        String contact = request.getParameter("contact");
        String idd = request.getParameter("idd");
        String couser = request.getParameter("couser");
        String c_id=request.getParameter("c_id");
        String p_id = request.getParameter("p_id");
        String city_id = request.getParameter("city_id");


        //生成对象
        Customer customer = new Customer();
        Country country = new Country();
        Province province = new Province();
        City city = new City();
        ICustomerServices services = new CustomerServicesImpl();

        //添加功能
        String flags = "1";
        if("reg".equals(opt)){
            // "" null
            //判断
            if(!"".equals(id)&&id!=null){
                customer.setId(Long.parseLong(id));
            }
            if(!"".equals(customer_name)&&customer_name!=null){
                customer.setCustomer_name(customer_name);
            }
            if(!"".equals(c_id)&&c_id!=null){
                customer.setCountry(Integer.parseInt(c_id));
            }
            if(!"".equals(p_id)&&p_id!=null){
                customer.setProvince(Integer.parseInt(p_id));
            }

            if(!"".equals(city_id)&&city_id!=null){

                customer.setCity(Integer.parseInt(city_id));

            }
            if(!"".equals(address)&&address!=null){
                customer.setAddress(address);
            }
            if(!"".equals(clientType)&&clientType!=null){
                customer.setClientType(Integer.parseInt(clientType));
            }
            if(!"".equals(contact)&&contact!=null){
                customer.setContact(contact);
            }

            boolean flag = services.add(customer);

            if(!flag){
                flags="0";

            }
            System.out.println(flags);
            out.print(flags);
            return;
        }
        //查询客户
        if("kehu".equals(opt)){
            // 存放集合内容
            List<Customer> lists= null;
            //page 当前页码
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");



            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            //查询所有信息默认分页

            lists = services.queryAllkehu(pageBean,customer);
            System.out.println("c_name---->"+c_name);

            //查询总记录数
//            System.out.println("22222");
            int total = services.Count(customer);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(lists);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
            return;
        }
        //type下拉框
        if ("queryType".equals(opt)){

            ICountryServices service = new CountryservicesImpl();
            List<Country> countryList = service.queryAllCountry();
            JSONArray jsonArray = JSONArray.fromObject(countryList);
            System.out.println("Type:");
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
        }

        //type下拉框
        if ("querypro".equals(opt)){

            IProvinceService service = new ProvinceServiceImpl();
            List<Province> provinceList = service.queryAllProvince();
            JSONArray jsonArray = JSONArray.fromObject(provinceList);
            System.out.println("Type:");
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
        }
        //type下拉框
        if ("querycity".equals(opt)){

            ICityServices service = new CityServicesImpl();
            List<City> cityList = service.queryAllCity();
            JSONArray jsonArray = JSONArray.fromObject(cityList);
            System.out.println("Type:");
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
        }

        //删除单个功能
        if("del".equals(opt)){
            //获取前端页面的sno
            //判断
            if(!"".equals(id)&&id!=null){
                customer.setId(Long.parseLong(id));
            }

            boolean flag = services.delete(customer);

            if(!flag){
                flags="0";
            }
            out.print(flags);

        }
        //修改
        if("upd".equals(opt)) {
            //判断
            if(!"".equals(id)&&id!=null){
                customer.setId(Long.parseLong(id));
            }
            if(!"".equals(customer_name)&&customer_name!=null){
                customer.setCustomer_name(customer_name);
            }
            if(!"".equals(c_id)&&c_id!=null){
                customer.setCountry(Integer.parseInt(c_id));
            }
            if(!"".equals(p_id)&&p_id!=null){
                customer.setProvince(Integer.parseInt(p_id));
            }

            if(!"".equals(city_id)&&city_id!=null){

                customer.setCity(Integer.parseInt(city_id));

            }
            if(!"".equals(address)&&address!=null){
                customer.setAddress(address);
            }
            if(!"".equals(clientType)&&clientType!=null){
                customer.setClientType(Integer.parseInt(clientType));
            }
            if(!"".equals(contact)&&contact!=null){
                customer.setContact(contact);
            }
            boolean flag = services.update(customer);
            if(!flag){
                flags="0";

            }
            System.out.println(flags);
            out.print(flags);
        }

/**
 * 批量删除操作
 * */
        if ("dels".equals(opt)) {
            //获取前端页面的id集合
            String delIds = request.getParameter("delArray");
            System.out.println("delIds--->" + delIds);
            JSONObject result = new JSONObject();
            int delNums = 0;
            // "" null
            if (!"".equals(id) && id != null) {
                customer.setId(Long.parseLong(id));
                //执行删除单个
                services.delete(customer);
                delNums = 1;
            } else {
                delNums = services.Deletess(delIds);
            }

            if (delNums > 0) {
                result.put("success", true);
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除失败");
            }
            out.print(result);
        }


        //查询所有

        if("queryAll".equals(opt)){
            // 存放集合内容
//            List<String> list = null;
            List<Customer> lists= null;
//            String time = "'"+start + "' and '" + end +"'";
            //page 当前页码
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");

            if(!"".equals(idd)&&idd!=null){
                customer.setId(Long.parseLong(idd));
            }
            if(!"".equals(couser)&&couser!=null){
                customer.setCustomer_name(couser);
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            //查询所有信息默认分页

            lists = services.queryAll(pageBean,customer);
            System.out.println("c_name---->"+c_name);

            //查询总记录数
//            System.out.println("22222");
            int total = services.Count(customer);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(lists);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
            System.out.println(result.toString());
            out.print(result);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
