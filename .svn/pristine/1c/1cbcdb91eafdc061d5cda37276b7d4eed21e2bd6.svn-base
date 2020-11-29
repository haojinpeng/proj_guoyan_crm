package com.it.dao;

import com.it.bean.*;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public int addRole(Role role) {
        //编写sql语句
        String sql = "INSERT INTO `crm`.`role` (`name`) VALUES (?)";
        System.out.println(sql);
        Object[] parame = {role.getName()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
//        return 0;
    }

    @Override
    public int addRoleAndResourcesAndJurisdiction(RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction) {
        //编写sql语句
        String sql = "INSERT INTO `crm`.`role_resources_jurisdiction` (`rid`, `resourceid`, `jurisdiction`) VALUES (?, ?, ?)";
        System.out.println(sql);
        Object[] parame = {roleAndResourcesAndJurisdiction.getId(),roleAndResourcesAndJurisdiction.getRes_id(),roleAndResourcesAndJurisdiction.getJur_id()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
    }

    @Override
    public int del(String delIds) {
        String sql = "delete from `crm`.`role` where `id` in (" + delIds + ")";
        System.out.println("sql--->" + sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delRole_res_jurByRole_id(Long id) {
        String sql = "delete from `crm`.`role_resources_jurisdiction` where `rid` in (" + id + ")";
        System.out.println("sql--->" + sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updRole(Role role) {
        //编写sql语句
        String sql = "update `crm`.`role` set `name`=? where `id`=? ";
        System.out.println(sql);
        Object[] parame = {role.getName(),role.getId()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
//        return 0;
    }

    @Override
    public List<RoleAndResourcesAndJurisdiction> queryAll(PageBean pageBean, RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction) {
        StringBuffer sql = new StringBuffer("SELECT role.`id`,role.`name`,\n" +
                "       GROUP_CONCAT(rrjs.res_id) AS res_id,GROUP_CONCAT(rrjs.res_name) AS res_name,\n" +
                "       GROUP_CONCAT(rrjs.jur_id SEPARATOR ';') AS jur_id,GROUP_CONCAT(rrjs.jur_name SEPARATOR ';') AS jur_name\n" +
                "FROM role,\n" +
                "     (SELECT rjj.`rid`,rjj.`resourceid` AS res_id,res.`name` AS res_name,\n" +
                "             GROUP_CONCAT(jur.`id`) AS jur_id,GROUP_CONCAT(jur.`name`) AS jur_name\n" +
                "      FROM role_resources_jurisdiction AS rjj,resources_jurisdiction AS rj,resource AS res,jurisdiction AS jur\n" +
                "      WHERE 1=1\n" +
                "            AND rjj.`resourceid`=rj.`resourceid` AND rjj.`jurisdiction`=rj.`jurisdiction`\n" +
                "            AND rjj.`resourceid`=res.`id` AND rjj.`jurisdiction`=jur.`id`\n" +
                "            GROUP BY rjj.`rid`,rjj.`resourceid`) AS rrjs\n" +
                "WHERE 1=1 AND rrjs.rid=role.`id` \n" +
                "GROUP BY role.`id`\n" +
                "HAVING 1=1");
        if (roleAndResourcesAndJurisdiction != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(roleAndResourcesAndJurisdiction.getName()) && roleAndResourcesAndJurisdiction.getName() != null) {
                //拼接sql
                sql.append(" and `name` like '%" + roleAndResourcesAndJurisdiction.getName() + "%' ");
            }
            if (!"".equals(roleAndResourcesAndJurisdiction.getRes_name()) &&
                    roleAndResourcesAndJurisdiction.getRes_name() != null) {
                sql.append(" and `res_name` like '%" + roleAndResourcesAndJurisdiction.getRes_name() + "%'");
            }
            if (!"".equals(roleAndResourcesAndJurisdiction.getJur_name()) && roleAndResourcesAndJurisdiction.getJur_name() != null) {
                sql.append(" and `jur_name` like '%" + roleAndResourcesAndJurisdiction.getJur_name() + "%'");
            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
//        List<Jurisdiction> jurisdictionList = null;
        List<RoleAndResourcesAndJurisdiction> roleAndResourcesAndJurisdictionList = null;
        try {
            roleAndResourcesAndJurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(RoleAndResourcesAndJurisdiction.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return roleAndResourcesAndJurisdictionList;
    }

    @Override
    public int count(RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction) {
        StringBuffer sql = new StringBuffer("SELECT role.`id`,role.`name`,\n" +
                "       GROUP_CONCAT(rrjs.res_id) AS res_id,GROUP_CONCAT(rrjs.res_name) AS res_name,\n" +
                "       GROUP_CONCAT(rrjs.jur_id SEPARATOR ';') AS jur_id,GROUP_CONCAT(rrjs.jur_name SEPARATOR ';') AS jur_name\n" +
                "FROM role,\n" +
                "     (SELECT rjj.`rid`,rjj.`resourceid` AS res_id,res.`name` AS res_name,\n" +
                "             GROUP_CONCAT(jur.`id`) AS jur_id,GROUP_CONCAT(jur.`name`) AS jur_name\n" +
                "      FROM role_resources_jurisdiction AS rjj,resources_jurisdiction AS rj,resource AS res,jurisdiction AS jur\n" +
                "      WHERE 1=1\n" +
                "            AND rjj.`resourceid`=rj.`resourceid` AND rjj.`jurisdiction`=rj.`jurisdiction`\n" +
                "            AND rjj.`resourceid`=res.`id` AND rjj.`jurisdiction`=jur.`id`\n" +
                "            GROUP BY rjj.`rid`,rjj.`resourceid`) AS rrjs\n" +
                "WHERE 1=1 AND rrjs.rid=role.`id` \n" +
                "GROUP BY role.`id`\n" +
                "HAVING 1=1");
        if (roleAndResourcesAndJurisdiction != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(roleAndResourcesAndJurisdiction.getName()) && roleAndResourcesAndJurisdiction.getName() != null) {
                //拼接sql
                sql.append(" and `name` like '%" + roleAndResourcesAndJurisdiction.getName() + "%' ");
            }
            if (!"".equals(roleAndResourcesAndJurisdiction.getRes_name()) &&
                    roleAndResourcesAndJurisdiction.getRes_name() != null) {
                sql.append(" and `res_name` like '%" + roleAndResourcesAndJurisdiction.getRes_name() + "%'");
            }
            if (!"".equals(roleAndResourcesAndJurisdiction.getJur_name()) && roleAndResourcesAndJurisdiction.getJur_name() != null) {
                sql.append(" and `jur_name` like '%" + roleAndResourcesAndJurisdiction.getJur_name() + "%'");
            }
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
//        List<Jurisdiction> jurisdictionList = null;
        List<RoleAndResourcesAndJurisdiction> roleAndResourcesAndJurisdictionList = null;
        try {
            roleAndResourcesAndJurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(RoleAndResourcesAndJurisdiction.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return roleAndResourcesAndJurisdictionList.size();
    }

    @Override
    public Role selectRole(Role role) {
        //编写sql语句
        String sql="SELECT * FROM `crm`.`role` WHERE 1=1 and `name`=?";
        System.out.println(sql);
        Object[]parame={role.getName()};
        try {
            return queryRunner.query(sql, new BeanHandler<Role>(Role.class),parame);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ResourceAndJurisdiction> queryAllResAndJur(ResourceAndJurisdiction resourceAndJurisdiction) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT re.`id` AS resource_id,re.`name`,re.`path`,re.`type`,GROUP_CONCAT(ju.`id`) AS `jurisdiction_id`,GROUP_CONCAT(ju.`name`) AS `jurisdiction_name`\n" +
                "                FROM `resource` AS re,`resources_jurisdiction` AS rj,`jurisdiction` AS ju\n" +
                "                WHERE re.`id`=rj.`resourceid` AND rj.`jurisdiction`=ju.`id`\n" +
                "                GROUP BY re.`id`\n" +
                "                HAVING 1=1");
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<ResourceAndJurisdiction> resourceAndJurisdictionList = null;
        try {
            resourceAndJurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(ResourceAndJurisdiction.class));
            return resourceAndJurisdictionList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
//        return null;
    }
}
