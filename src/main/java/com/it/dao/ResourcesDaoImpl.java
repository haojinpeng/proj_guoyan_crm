package com.it.dao;

import com.it.bean.Resources;
import com.it.bean.User;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ResourcesDaoImpl implements ResourcesDao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());


    @Override
    public List<Resources> selectResourcesByUserId(User user) {
        String sql = "select resource.* from  resource , user_role, role_resources_jurisdiction where user_role.uid = ? and user_role.rid = role_resources_jurisdiction.rid and role_resources_jurisdiction.resourceid = resource.id GROUP BY role_resources_jurisdiction.resourceid";
        Object[] param = {user.getId()};
        List<Resources> result = null;
        try {
            System.out.println(sql+" " +param[0]);
            result = queryRunner.query(sql,new BeanListHandler<>(Resources.class),param);
            System.out.println(result.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Resources selectResourcesByPath(String requestURI) {
        String sql = "select * from resource where resource.href=?";
        Object[] param = {requestURI};
        Resources resources = null;
        try {
            resources = queryRunner.query(sql,new BeanHandler<>(Resources.class),param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }
}
