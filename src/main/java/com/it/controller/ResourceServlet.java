package com.it.controller;

import com.it.bean.*;
import com.it.services.IResourceSercices;
import com.it.services.impl.ResourceServicesImpl;
import com.it.utils.ResourceParentSort;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ResourceServlet", value = "/resource.do")
public class ResourceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        response.setContentType("application/json; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        //获取页面上的值
        String opt = request.getParameter("opt");
        System.out.println("opt==========" + opt);
        String resource_id = request.getParameter("resource_id");
//        System.out.println("resource_id=============" + resource_id);
        String name = request.getParameter("name");
        String path = request.getParameter("path");
        String type = request.getParameter("type");
        String jurisdiction_id = request.getParameter("jurisdiction_id");
        String jurisdiction_name = request.getParameter("jurisdiction_name");
        String checkID = request.getParameter("checkID");
        System.out.println("checkID========" + checkID);
        //page 当前页码
        String page = request.getParameter("page");
        //limit pageSize
        String rows = request.getParameter("limit");

        PrintWriter out = response.getWriter();

        //创建对象
        Resource resource = new Resource();
        Resources_jurisdiction resources_jurisdiction = new Resources_jurisdiction();
        ResourceAndJurisdiction resourceAndJurisdiction = new ResourceAndJurisdiction();
//        Jurisdiction jurisdiction = new Jurisdiction();
        JSONObject result = new JSONObject();

        // 存放集合内容
//        List<Jurisdiction> jurisdictionList = null;
        List<Jurisdiction> jurisdictionList = null;
        List<Resource> resourceList = null;
        List<Resources_jurisdiction> resources_jurisdictionList = null;
        List<ResourceAndJurisdiction> resourceAndJurisdictionList = null;

        // 判 "" null
        if (resource_id != null && !"".equals(resource_id)) {
            resource.setId(Long.parseLong(resource_id));
            resources_jurisdiction.setResourceid(Long.parseLong(resource_id));
            resourceAndJurisdiction.setResource_id(Long.parseLong(resource_id));
        }
        if (name != null && !"".equals(name)) {
            resource.setName(name);
            resourceAndJurisdiction.setName(name);
        }
        if (path != null && !"".equals(path)) {
            resource.setPath(path);
            resourceAndJurisdiction.setPath(path);
        }
        if (type != null && !"".equals(type)) {
            resource.setType(type);
            resourceAndJurisdiction.setType(type);
        }
        if (jurisdiction_id != null && !"".equals(jurisdiction_id)) {
            resources_jurisdiction.setJurisdiction(Long.parseLong(jurisdiction_id));
        }
        if (jurisdiction_name != null && !"".equals(jurisdiction_name)) {
            resourceAndJurisdiction.setJurisdiction_name(jurisdiction_name);
        }
        System.out.println("resource==========" + resource);

        //service
        IResourceSercices resourceSercices = new ResourceServicesImpl();

        // 判别flag
        int flag = 0;


        if ("quanxian".equals(opt)){
            //权限管理
            //获得URL
            String url = request.getRequestURI();
            url = url.substring(1, url.length() - 2) + "html";
            System.out.println("newURL=====" + url);
            HttpSession session = request.getSession();
//            User user = new User();
//            user.setId(Long.parseLong("3"));
//            session.setAttribute("user", user);
            User user1 = (User) request.getSession().getAttribute("user");
            System.out.println("user======" + user1);
            //根据user,url查找 所拥有的权限
            List<Jurisdiction> jurisdictionList1 = resourceSercices.queryJurisdictionByUserAndUrl(user1, url);
            System.out.println("jurssssss========"+jurisdictionList1);
            JSONArray jsonArray2=JSONArray.fromObject(jurisdictionList1);

//            result.put("privalege",jsonArray2);
            out.print(jsonArray2);
            return;
        }

        //增
        if ("add".equals(opt)) {
//            System.out.println("===========add==========");
//            System.out.println("resource============" + resource);
//            System.out.println("resources_jurisdiction==========" + resources_jurisdiction);
            String[] checkbox = checkID.split(",");
            resourceSercices.addResource(resource);
            resource = resourceSercices.select(resource);
            for (String s : checkbox
            ) {
                resources_jurisdiction = new Resources_jurisdiction(resource.getId(), Long.parseLong(s));
                resourceSercices.addResources_jurisdiction(resources_jurisdiction);
                flag++;
            }
            out.print(flag);
            return;
        }

        //删
        if ("delete".equals(opt)) {
            //获取前端页面的id集合
            String delIds = request.getParameter("delArray");
            System.out.println("delIds--->" + delIds);
            //{key:value,key:value....}
            int delNums = 0;
//            获取前端页面的Sno
            if (!"".equals(resource_id) && resource_id != null) {
                resourceSercices.del(resource_id);
                delNums = 1;
            } else {
                delNums = resourceSercices.del(delIds);
            }
            out.print(delNums);
            return;
        }


        //改
        if ("update".equals(opt)) {
            String[] checkbox = checkID.split(",");
//            System.out.println("upd1   resource===========" + resource);
//            resourceSercices.del(resource_id);
            //先修改资源
            try {
                resourceSercices.updResource(resource);
                flag++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            //根据资源id查询Resources_jurisdiction
            resources_jurisdictionList = resourceSercices.queryResources_jurisdiction(resource.getId());

            //比较checkbox中的jur_id与Resources_jurisdiction中的jur_id的异同
            for (String s : checkbox
            ) {
                boolean flag1 = true;
                for (Resources_jurisdiction resources_jurisdiction1 : resources_jurisdictionList
                ) {
                    if (s.equals(resources_jurisdiction1.getJurisdiction().toString())) {
                        flag1 = false;
                        break;
                    }
                }
                if (flag1) {
                    //添加Resources_jurisdiction
                    resources_jurisdiction.setResourceid(resource.getId());
                    resources_jurisdiction.setJurisdiction(Long.parseLong(s));
                    resourceSercices.addResources_jurisdiction(resources_jurisdiction);
                    flag++;
                }
            }
            for (Resources_jurisdiction resources_jurisdiction1 : resources_jurisdictionList
            ) {
                boolean flag1 = true;
                for (String s : checkbox
                ) {
                    if (s.equals(resources_jurisdiction1.getJurisdiction().toString())) {
                        flag1 = false;
                        break;
                    }
                }
                if (flag1) {
                    //删除Resources_jurisdiction
                    resources_jurisdiction.setResourceid(resource.getId());
                    resources_jurisdiction.setJurisdiction(resources_jurisdiction1.getJurisdiction());
                    System.out.println("-----------" + resources_jurisdiction);
                    resourceSercices.delResources_jurisdictionByResources_jurisdiction(resources_jurisdiction);
                    flag++;
                }
            }
            out.print(flag);
            return;
        }

        //模糊查询所有
        if ("queryAll".equals(opt)) {

            //查询所有信息默认分页
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
//            jurisdictionList = jurisdictionServices.queryAll(pageBean, jurisdiction);
            resourceAndJurisdictionList = resourceSercices.queryAll(pageBean, resourceAndJurisdiction);
            //查询总记录数
            int total = resourceSercices.count(resourceAndJurisdiction);

            String url = request.getRequestURI();
            url = url.substring(1, url.length() - 2) + "html";
            System.out.println("newURL=====" + url);
            HttpSession session = request.getSession();
//            User user = new User();
//            user.setId(Long.parseLong("3"));
//            session.setAttribute("user", user);
            User user1 = (User) request.getSession().getAttribute("user");
            System.out.println("user======" + user1);
            //根据user,url查找 所拥有的权限
            List<Jurisdiction> jurisdictionList1 = resourceSercices.queryJurisdictionByUserAndUrl(user1, url);
            System.out.println("jurssssss========"+jurisdictionList1);
            JSONArray jsonArray1=JSONArray.fromObject(jurisdictionList1);


            System.out.println("total==========" + total);

            // jsonArray
            JSONArray jsonArray = JSONArray.fromObject(resourceAndJurisdictionList);
            result.put("code", 0);
            result.put("msg", "正在加载....");
            result.put("count", total);
            result.put("data", jsonArray);
//            result.put("privalege",jsonArray1);
//            result.put("jurs", jurisdictionList);
            System.out.println(result.toString());
            out.print(result);
            return;
        }




//        // 查询单个
//        if ("select".equals(opt)) {
//            try {
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            out.print(flag);
//            return;
//        }

        //查询所有的权限
        if ("queryAllJurisdiction".equals(opt)) {
            try {
                jurisdictionList = resourceSercices.queryAllJurisdiction(new Jurisdiction());
                // jsonArray
                JSONArray jsonArray = JSONArray.fromObject(jurisdictionList);
                out.print(jsonArray);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        if ("login".equals(opt)) {

            if (request.getSession().getAttribute("user") != null) {
                JSONObject object = new JSONObject();
                JSONObject object1 = new JSONObject();
                JSONObject object2 = new JSONObject();
                object2.put("title", "CRM管理系统");
                if (request.getSession().getAttribute("resources") != null) {
                    object2.put("child", request.getSession().getAttribute("resources"));

                } else {
                    User user2 = (User) request.getSession().getAttribute("user");
                    List<Resource> resourcesList = new ResourceParentSort<Resource>().sortList(resourceSercices.findResourcesByUserId(user2));
                    object2.put("child", resourcesList);
                }
                object1.put("currency", object2);
                object.put("menuInfo", object1);

                JSONObject object3 = new JSONObject();
                object3.put("clearUrl","api/clear.json");
                object.put("clearInfo",object3);
                JSONObject object4 = new JSONObject();
                object4.put("title","工作台");
                object4.put("icon","fa fa-home");
                object4.put("href","./page/welcome-1.html");
                object.put("homeInfo",object4);

                JSONObject object5 = new JSONObject();
                object5.put("title","CRM管理系统");
                object5.put("image","images/logo.png");
                object5.put("href","");
                object.put("logoInfo",object5);

                response.getWriter().print(object);
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                response.sendRedirect("/page/login-1.html");
            }
        } else {
            System.out.println("无opt");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
