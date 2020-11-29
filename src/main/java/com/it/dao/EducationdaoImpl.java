package com.it.dao;

import com.it.bean.Education;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class EducationdaoImpl implements IEducationdao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Education> queryAllEducation() {
        String sql = "select * from education";
        List<Education> educationList = null;
        try {
            educationList = queryRunner.query(sql,new BeanListHandler<>(Education.class));
            System.out.println(educationList);
            if(educationList != null && educationList.size()>0){
                System.out.println("查找成功！！！");
                return educationList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
