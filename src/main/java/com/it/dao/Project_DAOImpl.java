package com.it.dao;

import com.it.bean.Project;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Project_DAOImpl implements IProject_DAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Project> queryProject_ProjectId(Project project) {
        //1.编写sql
        String sql = "SELECT id,project_name FROM project  WHERE 1=1";
        //2.打印sql
        System.out.println(sql);
        //3.执行并返回结果
        List<Project> projectList = null;
        try {
            projectList = queryRunner.query(sql,new BeanListHandler<>(Project.class));
            for (Project project1 : projectList){
                System.out.println("111111111111-"+project1);
            }
            return projectList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
