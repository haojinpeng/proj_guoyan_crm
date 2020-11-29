package com.it.dao;

import com.it.bean.Country;
import com.it.bean.Province;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Provincedao implements IProvincedao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Province> queryAllProvince() {
        String sql = "select * from province";
        List<Province> provinces = null;
        try {
            provinces = queryRunner.query(sql,new BeanListHandler<>(Province.class));
            System.out.println(provinces);
            if(provinces != null && provinces.size()>0){
                System.out.println("查找成功！！！");
                return provinces;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
