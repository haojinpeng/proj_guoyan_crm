package com.it.dao;

import com.it.bean.Jurisdiction;
import com.it.bean.PageBean;
//import com.it.jdbcutils.JdbcUtils;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class JurisdictionDAOImpl implements JurisdictionDAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public int add(Jurisdiction jurisdiction) {
        //编写sql语句
        String sql = "insert into `crm`.`jurisdiction` (`name`, `path`) values(?,?)";
        System.out.println(sql);
        Object[] parame = {jurisdiction.getName(),jurisdiction.getPath()};
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
        String sql = "delete from `crm`.`jurisdiction` where `id` in (" + delIds + ")";
        System.out.println("sql--->" + sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delRes_jur(String delIds) {
        String sql = "DELETE FROM `crm`.`resources_jurisdiction` WHERE `resources_jurisdiction`.`jurisdiction` IN (" + delIds + ")";
        System.out.println("sql--->" + sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int upd(Jurisdiction jurisdiction) {
        //编写sql语句
        String sql = "update `crm`.`jurisdiction` set `name`=? , `path`=? where `id`=? ";
        System.out.println(sql);
        Object[] parame = {jurisdiction.getName(),jurisdiction.getPath(),jurisdiction.getId()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
//        return 0;
    }

    @Override
    public List<Jurisdiction> queryAll(PageBean pageBean, Jurisdiction jurisdiction) {
        //编写sql
        StringBuffer sql = new StringBuffer("select * from `crm`.`jurisdiction`where 1=1 ");
        if (jurisdiction != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(jurisdiction.getName()) && jurisdiction.getName() != null) {
                //拼接sql
                sql.append("and `name` like '%" + jurisdiction.getName() + "%' ");
            }
            if (!"".equals(jurisdiction.getPath()) && jurisdiction.getPath() != null) {
                sql.append(" and `path` like '%" + jurisdiction.getPath() + "%'");
            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<Jurisdiction> jurisdictionList = null;

        try {
            jurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(Jurisdiction.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return jurisdictionList;
    }

    @Override
    public Jurisdiction select(Jurisdiction jurisdiction) {
        //编写sql语句
        String sql="SELECT * FROM `crm`.`approval_type` WHERE `name`=? or `path`=?";
        System.out.println(sql);
        Object[]parame={jurisdiction.getName(),jurisdiction.getPath()};
        try {
            return queryRunner.query(sql, new BeanHandler<Jurisdiction>(Jurisdiction.class),parame);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(Jurisdiction jurisdiction) {
        //编写sql
        StringBuffer sql = new StringBuffer("select * from `crm`.`jurisdiction`where 1=1 ");
        if (jurisdiction != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(jurisdiction.getName()) && jurisdiction.getName() != null) {
                //拼接sql
                sql.append("and `name` like '%" + jurisdiction.getName() + "%' ");
            }
            if (!"".equals(jurisdiction.getPath()) && jurisdiction.getPath() != null) {
                sql.append(" and `path` like '%" + jurisdiction.getPath() + "%'");
            }
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<Jurisdiction> jurisdictionList = null;
        try {
            jurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(Jurisdiction.class));
            return jurisdictionList.size();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
//        return 0;
    }
}
