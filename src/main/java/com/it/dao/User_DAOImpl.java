package com.it.dao;

import com.it.bean.User;
import com.it.dao.IUser_DAO;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class User_DAOImpl implements IUser_DAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<User> queryParentUser(String loginname) {
        //1.编写sql
        /*##查出来的parent_id - 1 与表中的比较，返回approval_user
        ##增加部门限定条件
        ##查出相应的审核人和部门后，查询审核人姓名*/
        String sql = "SELECT us.id,us.username\n" +
                "FROM USER us\n" +
                "WHERE us.id IN\n" +
                "(\n" +
                "SELECT ap.approval_user\n" +
                "FROM `approval_process` ap\n" +
                "WHERE parent_id = \n" +
                "(\n" +
                "SELECT ap.parent_id\n" +
                "FROM `approval_process` ap\n" +
                "WHERE ap.approval_user IN\n" +
                "(SELECT user.id\n" +
                "FROM USER\n" +
                "WHERE user.login_name = ?)\n" +
                ")-1\n" +
                "AND ap.approval_department = (\n" +
                "SELECT user_dept.`did`\n" +
                "FROM user_dept,department\n" +
                "WHERE user_dept.`id` = \n" +
                "(\n" +
                "SELECT user.id\n" +
                "FROM USER\n" +
                "WHERE user.login_name = ?\n" +
                ")\n" +
                "AND user_dept.did = department.id\n" +
                ")\n" +
                ")\n";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {loginname,loginname};
        //4.执行并返回结果
        List<User> userList = null;
        try {
            userList = queryRunner.query(sql, new BeanListHandler<>(User.class),params);
            return userList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    @Override
    public List<User> queryLoginUsername(String loginname) {
        //1.编写sql
        String sql = "SELECT us.`username`\n" +
                "FROM USER us \n" +
                "WHERE us.`login_name` = ?";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object[] params = {loginname};
        //4.执行并返回结果
        List<User> userList = null;
        try {
            userList = queryRunner.query(sql,new BeanListHandler<>(User.class),params);
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User queryApply_idByLoginname(String loginname) {
        //1.编写sql
        String sql = "SELECT us.id\n" +
                "FROM USER us\n" +
                "WHERE us.login_name = ?";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object[] params = {loginname};
        //4.执行并返回结果
        User user = null;
        try {
            user = queryRunner.query(sql,new BeanHandler<>(User.class),params);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
