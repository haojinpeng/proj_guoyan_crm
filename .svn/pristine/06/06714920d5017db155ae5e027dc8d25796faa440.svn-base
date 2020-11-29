package com.it.dao;

import com.it.bean.Country;
import com.it.bean.Nation;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class NationdaoImpl implements INationdao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Nation> queryAllNation() {
        String sql = "select * from nation";
        List<Nation> nationList = null;
        try {
            nationList = queryRunner.query(sql,new BeanListHandler<>(Nation.class));
            System.out.println(nationList);
            if(nationList != null && nationList.size()>0){
                System.out.println("查找成功！！！");
                return nationList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
