package com.it.dao;

import com.it.bean.Country;
import com.it.services.ICountryServices;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;

public class CountrydaoImpl implements ICountrydao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Country> queryAllCountry() {
        String sql = "select * from country";
        List<Country> countryList = null;
        try {
            countryList = queryRunner.query(sql,new BeanListHandler<>(Country.class));
            System.out.println(countryList);
            if(countryList != null && countryList.size()>0){
                System.out.println("查找成功！！！");
                return countryList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
