package com.it.dao;

import com.it.bean.Privilege;
import com.it.bean.Resources;
import com.it.bean.User;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PrivilegeDaoImpl implements PrivilegeDao{
     QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Privilege> selectPrivilegeByResourcesAndUser(Resources resources, User user) {
        String sql = "select jurisdiction.* from user_role , role_resources_jurisdiction,jurisdiction where user_role.uid=? and user_role.rid = role_resources_jurisdiction.rid and jurisdiction.id = role_resources_jurisdiction.jurisdiction and role_resources_jurisdiction.resourceid=? ";
        Object[] param = {user.getId(),resources.getId()};
        List<Privilege> result = null;
        try {
            queryRunner.query(sql,new BeanListHandler<Privilege>(Privilege.class),param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
