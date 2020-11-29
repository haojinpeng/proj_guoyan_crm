package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.Project_task;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProjectTaskDAOImpl implements ProjectTaskDAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public List<Project> selectProjName(Project project) {
        String sql="SELECT `project_name` FROM `project`";
        System.out.println(sql);
        List<Project> list =null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<>(Project.class));
            if (list.size()>0 && list!=null){
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project_task> queryAll(Project_task project_task) {
        StringBuffer sql = new StringBuffer("SELECT project_task.`name`,`project_task`.`description`,user.`username`\n" +
                "FROM project,`project_task`,USER\n" +
                "WHERE project.`id`=`project_task`.`project_id`\n" +
                "AND `project_task`.`manager_id`=user.`id`");
        System.out.println(sql);
        if (project_task!=null){
            if(!"".equals(project_task.getProject_name()) && project_task.getProject_name()!=null) {
                sql.append(" AND project.project_name LIKE '%"+project_task.getProject_name()+"%'");
            }
        }
        try {
            List<Project_task> list = queryRunner.query(String.valueOf(sql),new BeanListHandler<>(Project_task.class));
            if (list.size()>0 && list!=null){
                System.out.println(list);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project_task> queryTask(Project_task project_task, PageBean pageBean) {
        StringBuffer sql = new StringBuffer("SELECT project_task.`name`,`project_task`.`description`,user.`username`\n" +
                "FROM project,`project_task`,USER\n" +
                "WHERE project.`id`=`project_task`.`project_id`\n" +
                "AND `project_task`.`manager_id`=user.`id`");
        System.out.println(sql);
        if (project_task!=null){
            if(!"".equals(project_task.getProject_name()) && project_task.getProject_name()!=null) {
                sql.append(" AND project.project_name LIKE '%"+project_task.getProject_name()+"%'");
            }
        }
        if (pageBean!=null){
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        try {
            List<Project_task> list = queryRunner.query(String.valueOf(sql),new BeanListHandler<>(Project_task.class));
            if (list.size()>0 && list!=null){
                System.out.println(list);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
