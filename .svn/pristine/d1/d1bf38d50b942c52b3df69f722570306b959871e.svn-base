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

@WebServlet(name = "ContactServlet",urlPatterns = {"/contact.do"})
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        //获取页面上的值
        String opt=request.getParameter("opt");
        String name = request.getParameter("name");
        String type_id = request.getParameter("type_id");
        String username = request.getParameter("type_id");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sex  = request.getParameter("sex");
        String n_id=request.getParameter("n_id");
        String c_id=request.getParameter("c_id");
        String p_id=request.getParameter("p_id");
        String city_id=request.getParameter("city_id");
        String usname = request.getParameter("usname");
        String id= request.getParameter("id");

        //生成对象
        Contact contact = new Contact();
        main.java.com.it.services.IContactServices services = new ContactServicesImpl();
        //查询所有

        //添加功能
        String flags = "1";
        if("reg".equals(opt)){
            System.out.println("通讯录添加！！！");
            // "" null
            //判断

            if(!"".equals(name)&&name!=null){
                contact.setName(name);
            }
            System.out.println("通讯录添加！！");
            if(!"".equals(username)&&username!=null){
                contact.setType(Integer.parseInt(username));
            }
            System.out.println("通讯录添加！");
            if(!"".equals(age)&&age!=null){
                contact.setAge(Integer.parseInt(age));
            }

            System.out.println("通讯录添加");
            if(!"".equals(phone)&&phone!=null){

                contact.setPhone(phone);

            }
            System.out.println("通讯录添");
            if(!"".equals(c_id)&&c_id!=null){
                contact.setCountry(Integer.parseInt(c_id));
            }
            if(!"".equals(p_id)&&p_id!=null){
                contact.setProvince(Integer.parseInt(p_id));
            }
            System.out.println("通讯录");
            if(!"".equals(city_id)&&city_id!=null){
                contact.setCity(Integer.parseInt(city_id));
            }
            if(!"".equals(n_id)&&n_id!=null){
                contact.setNation(Integer.parseInt(n_id));
            }
            System.out.println("通讯");
            if(!"".equals(address)&&address!=null){
                contact.setAddress(address);
            }

            if(!"".equals(sex)&&sex!=null){
                contact.setSex(Integer.parseInt(sex));
            }

            System.out.println("通讯录添加services！！！");
            boolean flag = services.add(contact);

            if(!flag){
                flags="0";

            }
            System.out.println(flags);
            out.print(flags);
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
        //民族下拉框
        if ("querynation".equals(opt)){

            INationServices service = new NationServicesImpl();
            List<Nation> nationList = service.queryAllNation();
            JSONArray jsonArray = JSONArray.fromObject(nationList);
            System.out.println("Type:");
            System.out.println(jsonArray);
            out.print(jsonArray.toString());
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
                contact.setId(Long.parseLong(id));
                //执行删除单个
                services.del(contact);
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


        //修改
        if("upd".equals(opt)) {
            //判断
            if(!"".equals(id)&&id!=null){
                contact.setId(Long.parseLong(id));
            }
            if(!"".equals(name)&&name!=null){
                contact.setName(name);
            }
            if(!"".equals(type_id)&&type_id!=null){
                contact.setType(Integer.parseInt(type_id));
            }
            if(!"".equals(phone)&&phone!=null){
                contact.setPhone(phone);
            }
            if(!"".equals(c_id)&&c_id!=null){
                contact.setCountry(Integer.parseInt(c_id));
            }

            if(!"".equals(city_id)&&city_id!=null){

                contact.setCity(Integer.parseInt(city_id));

            }
            if(!"".equals(address)&&address!=null){
                contact.setAddress(address);
            }
            if(!"".equals(p_id)&&p_id!=null){
                contact.setProvince(Integer.parseInt(p_id));
            }
            if(!"".equals(n_id)&&n_id!=null){
                contact.setNation(Integer.parseInt(n_id));
            }
            if(!"".equals(age)&&age!=null){
                contact.setAge(Integer.parseInt(age));
            }
            if(!"".equals(sex)&&sex!=null){
                contact.setSex(Integer.parseInt(sex));
            }
            boolean flag = services.update(contact);
            if(!flag){
                flags="0";

            }
            System.out.println(flags);
            out.print(flags);
        }

//删除单个功能
        if("del".equals(opt)){
            //获取前端页面的sno
            //判断
            if(!"".equals(id)&&id!=null){
                contact.setId(Long.parseLong(id));
            }

            boolean flag = services.del(contact);

            if(!flag){
                flags="0";
            }
            out.print(flags);

        }
        //查询所有
        if("queryAll".equals(opt)){
            // 存放集合内容
            List<Contact> lists= null;
            //page 当前页码
            System.out.println("进去contract");
            String page = request.getParameter("page");
            //limit pageSize
            String rows = request.getParameter("limit");
            if(!"".equals(usname)&&usname!=null){
                contact.setName(usname);
            }

            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

            //查询所有信息默认分页

            lists = services.queryAllCotact(pageBean,contact);
            //查询总记录数
//            System.out.println("22222");
            int total = services.contactCount(contact);
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
