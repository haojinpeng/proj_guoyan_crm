package com.it.dao;

import com.it.bean.*;
//import com.it.jdbcutils.JdbcUtils;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ResourceDAOImpl implements ResourceDAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public int addResource(Resource resource) {
        //编写sql语句
        String sql = "INSERT INTO `crm`.`resource` (`id`,`name`, `path`,`type`) VALUE(?,?,?,?)";
        System.out.println(sql);
        Object[] parame = {resource.getId(), resource.getName(), resource.getPath(), resource.getType()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
//        return 0;
    }

    @Override
    public int addResources_jurisdiction(Resources_jurisdiction resources_jurisdiction) {
        //编写sql语句
        String sql = "INSERT INTO `crm`.`resources_jurisdiction` (`resourceid`, `jurisdiction`) VALUES (?, ?)";
        System.out.println(sql);
        Object[] parame = {resources_jurisdiction.getResourceid(), resources_jurisdiction.getJurisdiction()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
    }

    @Override
    public int del(String delIds) {
        String sql = "delete from `crm`.`resource` where `id` in (" + delIds + ")";
        System.out.println("sql--->" + sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updResource(Resource resource) {
        //编写sql语句
        String sql = "update `crm`.`resource` set `name`=? , `path`=?,`type`=? where `id`=? ";
        System.out.println(sql);
        Object[] parame = {resource.getName(), resource.getPath(), resource.getType(), resource.getId()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
//        return 0;
    }

    @Override
    public List<ResourceAndJurisdiction> queryAll(PageBean pageBean, ResourceAndJurisdiction resourceAndJurisdiction) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT re.`id` AS resource_id,re.`name`,re.`path`,re.`type`,GROUP_CONCAT(ju.`id`) AS `jurisdiction_id`,GROUP_CONCAT(ju.`name`) AS `jurisdiction_name`\n" +
                "FROM `resource` AS re,`resources_jurisdiction` AS rj,`jurisdiction` AS ju\n" +
                "WHERE re.`id`=rj.`resourceid` AND rj.`jurisdiction`=ju.`id` \n" +
                "GROUP BY re.`id`\n" +
                "HAVING 1=1");
        if (resourceAndJurisdiction != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(resourceAndJurisdiction.getName()) && resourceAndJurisdiction.getName() != null) {
                //拼接sql
                sql.append(" and `name` like '%" + resourceAndJurisdiction.getName() + "%' ");
            }
            if (!"".equals(resourceAndJurisdiction.getType()) && resourceAndJurisdiction.getType() != null) {
                sql.append(" and `type` like '%" + resourceAndJurisdiction.getType() + "%'");
            }
            if (!"".equals(resourceAndJurisdiction.getPath()) && resourceAndJurisdiction.getPath() != null) {
                sql.append(" and `path` like '%" + resourceAndJurisdiction.getPath() + "%'");
            }
            if (!"".equals(resourceAndJurisdiction.getJurisdiction_name()) && resourceAndJurisdiction.getJurisdiction_name() != null) {
                sql.append(" and `jurisdiction_name` like '%" + resourceAndJurisdiction.getJurisdiction_name() + "%'");
            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
//        List<Jurisdiction> jurisdictionList = null;
        List<ResourceAndJurisdiction> resourceAndJurisdictionList = null;
        try {
            resourceAndJurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(ResourceAndJurisdiction.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return resourceAndJurisdictionList;
    }

    @Override
    public Resource select(Resource resource) {
        //编写sql语句
        String sql = "SELECT * FROM `crm`.`resource` WHERE `id`=? or `name`=?";
        System.out.println(sql);
        Object[] parame = {resource.getId(), resource.getName()};
        try {
            return queryRunner.query(sql, new BeanHandler<Resource>(Resource.class), parame);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(ResourceAndJurisdiction resourceAndJurisdiction) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT re.`id` AS resource_id,re.`name`,re.`path`,re.`type`,GROUP_CONCAT(ju.`id`) AS `jurisdiction_id`,GROUP_CONCAT(ju.`name`) AS `jurisdiction_name`\n" +
                "FROM `resource` AS re,`resources_jurisdiction` AS rj,`jurisdiction` AS ju\n" +
                "WHERE re.`id`=rj.`resourceid` AND rj.`jurisdiction`=ju.`id` \n" +
                "GROUP BY re.`id`\n" +
                "HAVING 1=1");
        if (resourceAndJurisdiction != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(resourceAndJurisdiction.getName()) && resourceAndJurisdiction.getName() != null) {
                //拼接sql
                sql.append(" and `name` like '%" + resourceAndJurisdiction.getName() + "%' ");
            }
            if (!"".equals(resourceAndJurisdiction.getType()) && resourceAndJurisdiction.getType() != null) {
                sql.append(" and `type` like '%" + resourceAndJurisdiction.getType() + "%'");
            }
            if (!"".equals(resourceAndJurisdiction.getPath()) && resourceAndJurisdiction.getPath() != null) {
                sql.append(" and `path` like '%" + resourceAndJurisdiction.getPath() + "%'");
            }
            if (!"".equals(resourceAndJurisdiction.getJurisdiction_name()) && resourceAndJurisdiction.getJurisdiction_name() != null) {
                sql.append(" and `jurisdiction_name` like '%" + resourceAndJurisdiction.getJurisdiction_name() + "%'");
            }
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<ResourceAndJurisdiction> resourceAndJurisdictionList = null;
        try {
            resourceAndJurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(ResourceAndJurisdiction.class));
            return resourceAndJurisdictionList.size();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
//        return 0;
    }

    @Override
    public List<Jurisdiction> queryAllJurisdiction(Jurisdiction jurisdiction) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT * FROM `jurisdiction`");
        System.out.println("sql-->" + sql);

        try {
            return queryRunner.query(sql.toString(), new BeanListHandler<>(Jurisdiction.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
//        return null;
    }

    @Override
    public List<Resources_jurisdiction> queryResources_jurisdiction(Long res_id) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT *\n" +
                "FROM resources_jurisdiction \n" +
                "WHERE 1=1 and resourceid=?");
        System.out.println("sql-->" + sql);
        Object[] parame = {res_id};
        //存放所有Users信息
        List<Resources_jurisdiction> resources_jurisdictionList = null;
        try {
            resources_jurisdictionList = queryRunner.query(sql.toString(), new BeanListHandler<>(Resources_jurisdiction.class), parame);
            return resources_jurisdictionList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    @Override
    public int delResources_jurisdictionByResources_jurisdiction(Resources_jurisdiction resources_jurisdiction) {
        String sql = "DELETE FROM resources_jurisdiction WHERE 1=1 AND resourceid=? AND jurisdiction=?";
        System.out.println("sql--->" + sql);
        Object[] parame = {resources_jurisdiction.getResourceid(), resources_jurisdiction.getJurisdiction()};
        try {
            return queryRunner.update(sql, parame);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Resource> findResourcesByUserId(User user) {
        String sql = "select resource.* " +
                "from  resource , user_role, role_resources_jurisdiction " +
                "where user_role.uid = ? and user_role.rid = role_resources_jurisdiction.rid " +
                "and role_resources_jurisdiction.resourceid = resource.id GROUP BY role_resources_jurisdiction.resourceid";
        Object[] param = {user.getId()};
        List<Resource> resourceList = null;
        try {
            System.out.println(sql + " " + param[0]);
            resourceList = queryRunner.query(sql, new BeanListHandler<>(Resource.class), param);
            System.out.println(resourceList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @Override
    public List<Jurisdiction> queryJurisdictionByUserAndUrl(User user, String url) {
        String sql = "SELECT jur.`name`,jur.`path`\n" +
                "FROM user_role AS ur,role_resources_jurisdiction AS rrj,resource res,jurisdiction AS jur\n" +
                "WHERE ur.`uid`=?\n" +
                "      AND ur.`rid`=rrj.`rid`\n" +
                "      AND rrj.`resourceid`=res.`id` AND res.`path`=?\n" +
                "      AND rrj.`jurisdiction`=jur.`id`";
        Object[] param = {user.getId(),url};
        List<Jurisdiction> jurisdictionList = null;
        try {
            System.out.println(sql + " " + param[0]);
            jurisdictionList = queryRunner.query(sql, new BeanListHandler<>(Jurisdiction.class), param);
            System.out.println(jurisdictionList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jurisdictionList;
    }
}
