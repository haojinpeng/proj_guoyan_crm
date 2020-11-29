package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.User;

import com.it.bean.User_Role;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class IUserImpl implements IUdao {
QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public User selectUserByLogin_Name(String login_name) {
        Object[] param = {login_name};
        User user = null;
        try {
            user = queryRunner.query("select * from user where login_name = ?", param,new BeanHandler<>(User.class));
            System.out.println("user--->"+user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        //编写sql语句
        String sql=" INSERT INTO USER(`employee_number`,`username`,`login_name`,`password`,`payroll`,`nation`,education_level,`tel`,country,province,city,`address`)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        Object[] parame={user.getEmployee_number(),user.getUsername(),user.getLogin_name(),
                user.getPassword(),user.getPayroll(),user.getNation(),user.getEducation_level(),user.getTel(),user.getCountry(),user.getProvince(),user.getCity(),user.getAddress()};

        try {
            int rows=queryRunner.update(sql, parame);
            return rows>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
    }

    @Override
    public boolean logion(User user) {
        //1 编写sql
        String sql = "select * from user where login_name=? and password=? ";
        //2 打印sql
        System.out.println("sql-->"+sql);
        //3 处理c参数
        Object params[] = {user.getLogin_name(),user.getPassword()};
        //4 执行并返回结果

        //4 执行并返回结果
System.out.println("qwertyui");
            User users1 = null;

            try {
                users1 = queryRunner.query(sql,new BeanHandler<>(User.class),params);
                System.out.println("users1--->"+users1);
                if(users1!=null) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw  new RuntimeException("登录失败！");
            }


        return false;
    }

    @Override
    public List<User_Role> queryAllUsrole(PageBean pageBean, User_Role user_role) {
        //编写sql

        System.out.println("user_role.getU_id()-->"+user_role.getU_id());
        StringBuffer sql = new StringBuffer("SELECT u.id as u_id,u.`username`,r.`id` as r_id,r.`name` FROM `user` as u,`role` as r ");

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }



        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<User_Role> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(User_Role.class));

            for (User_Role st:listU
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }


        return listU;
    }


    @Override
    public boolean del(User user) {
        //编写sql语句
        String sql="delete from user where id=?";
        System.out.println(sql);
        Object[] parame={user.getId()};
        try {
            int i=queryRunner.update(sql, parame);
            return i>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("删除失败!!");
        }
    }
    @Override
    public int Deletess(String delIds) {
        System.out.println(delIds);
        String sql = "delete from  user where id in (" + delIds + ")";
        System.out.println("sql--->"+sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public boolean updateb(User user) {
        //编写sql语句
        String sql="update user set employee_number=?,username=?,payroll=?,age=?,sex=?,tel=?,address=? where id=? ";
        System.out.println(sql);
        Object[] parame={user.getEmployee_number(),user.getUsername(),user.getPayroll(),user.getAge(),user.getSex(),user.getTel(),user.getAddress(),user.getId()};
        try {
            int in=queryRunner.update(sql, parame);
            return in>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
    }
//修改密码
    @Override
    public boolean upd(User user) {
        //编写sql语句
        String sql="update user set password=? where id=? ";
        System.out.println(sql);
        Object[] parame={user.getPassword(),user.getId()};
        try {
            int in=queryRunner.update(sql, parame);
            return in>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
    }

    @Override
    public List<User> queryAllUser(PageBean pageBean, User user) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT u.`id`,u.`employee_number`,u.`username`,u.`login_name`,u.`password`,\n" +
                "u.`payroll`,u.`age`,u.`sex`, n.`n_name`,e.`e_name`,u.`tel`,c.`c_name`,p.`p_name`,ci.`city_name`,u.`address`\n" +
                " FROM USER u,nation n,`education` e,`country` c,`province` p,`city`ci\n" +
                "  WHERE u.`nation`=n.`id` AND u.`education_level`=e.`id` AND u.`country`=c.`id` AND \n" +
                "  u.`province`=p.`id` AND u.`city`=ci.`id` and 1=1 ");

        if(user!=null){

            if(!" ".equals(user.getEmployee_number())&&user.getEmployee_number()!=null){
                //2.拼接sql
                sql.append(" and u.employee_number like '%"+user.getEmployee_number()+"%' ");

            }
            if(!" ".equals(user.getUsername())&&user.getUsername()!=null){
                //2.拼接sql
                sql.append(" and u.username like '%"+user.getUsername()+"%' ");
            }




        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }



        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<User> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(User.class));

            for (User st:listU
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }


        return listU;
    }

    @Override
    public int userCount(User user) {
        StringBuffer sb = new StringBuffer("select count(*) from user where 1=1 ");
        //有条件判断条件，重新拼接sql
//        if(billsVo!=null){
//            //判定账户名的文本框的合法性
//            // "" null判定
//            if(!"".equals(student.getSno()) && student.getSno()!=null) {
//
//                //拼接sb
//                sb .append("and sno like '%"+student.getSno()+"%' ");
//
//            }
//
//            if(!"".equals(student.getSname()) && student.getSname()!=null) {
//                sb .append(" and sname like '%"+student.getSname()+"%'");
//
//            }
//        }
        Number num;
        try {
            num = (Number)queryRunner.query(sb.toString(),new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }

    }
}
