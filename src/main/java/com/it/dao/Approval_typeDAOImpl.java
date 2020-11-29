package com.it.dao;

import com.it.bean.Approval_type;
import com.it.bean.PageBean;
//import com.it.jdbcutils.JdbcUtils;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Approval_typeDAOImpl implements Approval_typeDAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public int add(Approval_type approval_type) {
        //编写sql语句
        String sql = "insert into `crm`.`approval_type` values(?,?)";
        System.out.println(sql);
        Object[] parame = {approval_type.getId(), approval_type.getName()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
//        return 0;
    }

    @Override
    public int del(String delIds) {
        String sql = "delete from `crm`.`approval_type` where `id` in (" + delIds + ")";
        System.out.println("sql--->" + sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int upd(Approval_type approval_type) {
        //编写sql语句
        String sql = "update `crm`.`approval_type` set `name`=? where `id`=? ";
        System.out.println(sql);
        Object[] parame = {approval_type.getName(), approval_type.getId()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
//        return 0;
    }

    @Override
    public List<Approval_type> queryAll(PageBean pageBean, Approval_type approval_type) {
        //编写sql
        StringBuffer sql = new StringBuffer("select * from `crm`.`approval_type`where 1=1 ");
        if (approval_type != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(approval_type.getId()) && approval_type.getId() != null) {
                //拼接sql
                sql.append("and `id` like '%" + approval_type.getId() + "%' ");
            }
            if (!"".equals(approval_type.getName()) && approval_type.getName() != null) {
                sql.append(" and `name` like '%" + approval_type.getName() + "%'");
            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<Approval_type> approval_typeList = null;

        try {
            approval_typeList = queryRunner.query(sql.toString(), new BeanListHandler<>(Approval_type.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return approval_typeList;
    }

    @Override
    public Approval_type select(Approval_type approval_type) {
        //编写sql语句
        String sql="SELECT * FROM `crm`.`approval_type` WHERE `id`=? or `name`=?";
        System.out.println(sql);
        Object[]parame={approval_type.getId(),approval_type.getName()};
        try {
            return queryRunner.query(sql, new BeanHandler<Approval_type>(Approval_type.class),parame);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(Approval_type approval_type) {
        //编写sql
        StringBuffer sql = new StringBuffer("select * from `crm`.`approval_type`where 1=1 ");
        if (approval_type != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(approval_type.getId()) && approval_type.getId() != null) {
                //拼接sql
                sql.append("and `id` like '%" + approval_type.getId() + "%' ");
            }
            if (!"".equals(approval_type.getName()) && approval_type.getName() != null) {
                sql.append(" and `name` like '%" + approval_type.getName() + "%'");
            }
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<Approval_type> approval_typeList = null;

        try {
            approval_typeList = queryRunner.query(sql.toString(), new BeanListHandler<>(Approval_type.class));
            return approval_typeList.size();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
//        return 0;
    }
}
