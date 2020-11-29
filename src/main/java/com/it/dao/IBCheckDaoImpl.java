package com.it.dao;

import com.it.bean.Approval_Process;
import com.it.bean.Business_Check;
import com.it.bean.Business_cost;
import com.it.bean.User;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class IBCheckDaoImpl implements IBCheckDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean add(Business_Check check) {
        //1.编写sql语句
        String sql = "insert into cost_check(business_id,cost_id,userp,check_condition,check_idea,check_date,remarks) values(?,?,?,?,?,?,?)";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {check.getBusiness_id(),check.getCost_id(),check.getUserp(),check.getCheck_condition(),check.getCheck_idea(),check.getCheck_date(),check.getRemarks()};
        try{
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.释放资源
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean addCheck(Business_Check check) {
        //1.编写sql语句
        String sql = "insert into cost_check(userp,business_id,cost_id ) values(?,?,?)";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {check.getUserp(),check.getBusiness_id(),check.getCost_id()};
        try{
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.释放资源
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean del(Business_Check check) {
        //1.编写sql语句
        String sql = "delete from cost_check where id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {check.getId()};
        try {
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean upd(Business_Check check,String login_name) {
        //1.编写sql语句
        String sql = "UPDATE cost_check SET check_condition=?,check_idea=?,check_date=?,remarks=? WHERE id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {check.getCheck_condition(),check.getCheck_idea(),check.getCheck_date(),check.getRemarks(),check.getId()};
        try{
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.释放资源
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Business_Check> queryAllLike(String sql) {
        try {
            System.out.println(sql);
            List<Business_Check> checks = queryRunner.query(sql,new BeanListHandler<>(Business_Check.class));
            if(checks!=null){
                System.out.println(checks);
                return checks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int count(String sql) {
        Number num;
        try {
            num = (Number)queryRunner.query(sql,new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Business_Check> queryType() {
        return null;
    }

    @Override
    public List<Approval_Process> queryAllProcess() {
        return null;
    }

    @Override
    public List<User> queryParentUser(String loginname) {
        String sql = "select id from user where login_name = '" + loginname + "'";
        String sql1 = "select * from approval_process";
        StringBuffer sql2 = new StringBuffer("SELECT u.id as id,u.username as username from `user` u,user_dept ud,department d,role r,user_role ur \n" +
                "WHERE u.id = ud.id AND ud.did = d.id AND u.id = ur.uid AND ur.rid = r.id   ");
        try {
            User user = queryRunner.query(sql, new BeanHandler<>(User.class));
            System.out.println(user.getId());
            List<Approval_Process> approval_processList = queryRunner.query(sql1, new BeanListHandler<>(Approval_Process.class));
            Integer parent_id = null;
            System.out.println(approval_processList);
            for (int i = 0; i < approval_processList.size(); i++) {
                System.out.println(approval_processList.get(i).getId());
                if (approval_processList.get(i).getApproval_user()!=null && user.getId() == approval_processList.get(i).getApproval_user()) {
                    parent_id = approval_processList.get(i).getParent_id()-1;
                    System.out.println("parent_id-->" + parent_id);
                    break;
                }
            }
            for (int j = 0; j < approval_processList.size(); j++) {
                System.out.println("app--->"+approval_processList.get(j).getId());
                if( parent_id == approval_processList.get(j).getParent_id() ){
                    if(!"".equals(approval_processList.get(j).getApproval_user()) && approval_processList.get(j).getApproval_user()!=null){
                        sql2.append(" and u.id = " + approval_processList.get(j).getApproval_user());
                    }else{
                        if(approval_processList.get(j).getApproval_department() != null){
                            sql2.append(" AND d.id = " + approval_processList.get(j).getApproval_department());
                        }
                        if(approval_processList.get(j).getApproval_role() != null){
                            sql2.append(" AND r.id = " + approval_processList.get(j).getApproval_role());
                        }
                    }
                    System.out.println("app2-->" + sql2);
                    List<User> userList = queryRunner.query(sql2.toString(),new BeanListHandler<>(User.class));
                    System.out.println(userList);
                    return userList;
                }else if("0".equals(parent_id)){
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
