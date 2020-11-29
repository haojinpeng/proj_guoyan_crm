package com.it.dao;

import com.it.bean.City;
import com.it.bean.Country;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CitydaoImpl implements ICitydao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<City> queryAllCity() {
        String sql = "select * from city";
        List<City> cities = null;
        try {
            cities = queryRunner.query(sql,new BeanListHandler<>(City.class));
            System.out.println(cities);
            if(cities != null && cities.size()>0){
                System.out.println("查找成功！！！");
                return cities;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
