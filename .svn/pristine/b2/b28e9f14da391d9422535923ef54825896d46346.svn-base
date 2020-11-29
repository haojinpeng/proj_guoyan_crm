package com.it.controller;

import com.it.bean.*;
import com.it.services.*;
import com.it.utils.MD5Util;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "regiterServlet",urlPatterns = {"/reg.do"})
public class regiterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String opt = request.getParameter("opt");
        String employee_number=request.getParameter("employee_number");
        String username = request.getParameter("username");
        String payroll=request.getParameter("payroll");
        String tel = request.getParameter("tel");
        String address=request.getParameter("address");
        String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");
        String n_id=request.getParameter("n_id");
        String c_id=request.getParameter("c_id");
        String p_id=request.getParameter("p_id");
        String city_id=request.getParameter("city_id");
        String e_id = request.getParameter("e_id");
        System.out.println(n_id);
        System.out.println(c_id);
        System.out.println(p_id);
        System.out.println(city_id);
        System.out.println(e_id);



        //生成对象
        User user = new User();
        IUserServices services = new UserServicesImpl();
        String flags = "1";
        //添加功能
        System.out.println("注册！！！");
        //type下拉框




        if ("queryedu".equals(opt)){

            IEducationServices service = new EducationServicesImpl();
            List<Education> educationList = service.queryAllEducation();
            JSONArray jsonArray = JSONArray.fromObject(educationList);
            System.out.println("Type:");
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
        }

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
        //民族下拉框
        if ("querynation".equals(opt)){

            INationServices service = new NationServicesImpl();
            List<Nation> nationList = service.queryAllNation();
            JSONArray jsonArray = JSONArray.fromObject(nationList);
            System.out.println("Type:");
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
        }

        if("reg".equals(opt)){
            // "" null
            //判断
//            User login = services.findUserByLogin_Name(login_name);

            if (!"".equals(employee_number) && employee_number != null) {
                user.setEmployee_number(employee_number);
            }
            if (!"".equals(login_name) && login_name != null) {
                    user.setLogin_name(login_name);
            }
            if (!"".equals(password) && password != null) {
                user.setPassword(MD5Util.getMD5(password,"assfs","UTF-8"));
            }
            if(!"".equals(c_id)&&c_id!=null){
                user.setCountry(Integer.parseInt(c_id));
            }
            if(!"".equals(p_id)&&p_id!=null){
                user.setProvince(Integer.parseInt(p_id));
            }

            if(!"".equals(city_id)&&city_id!=null){
                user.setCity(Integer.parseInt(city_id));
            }
            if(!"".equals(n_id)&&n_id!=null){
                user.setNation(Integer.parseInt(n_id));
            }
            if(!"".equals(e_id)&&e_id!=null){
                user.setEducation_level(Integer.parseInt(e_id));
            }

            if (!"".equals(username) && username != null) {
                user.setUsername(username);
            }

            if (!"".equals(payroll) && payroll != null) {
                user.setPayroll(payroll);
            }

            if (!"".equals(tel) && tel != null) {
                user.setTel(tel);
            }

            if (!"".equals(address) && address != null) {
                user.setAddress(address);
            }
         try{
             boolean flag = services.add(user);
             if(!flag){
                 flags="0";
             }
             out.print(flags);
         }catch (Exception e){
             e.printStackTrace();
             out.print("0");
         }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
    }
}
