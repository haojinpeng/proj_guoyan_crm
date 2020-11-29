package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Project1DAOImpl implements Project1DAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    //添加功能
    @Override
    public boolean add(Project project) {
        String sql = "INSERT INTO project VALUES(?,?,?,?,?,?,?,?,?,?)\n";
        System.out.println(sql);
        Object []params = {project.getId(),project.getIdentifier(),project.getProject_name(),project.getBusiness_id(),project.getCreatep_id(),project.getStatus(),project.getUptime(),project.getProlong(),project.getCreatetime(),project.getDescription()};
        try {
            int rs = queryRunner.update(sql,params);
            if (rs>0){
                System.out.println("操作成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
        return false;
    }
    //删除单个
    @Override
    public boolean delete(Project project) {
        String sql = "DELETE FROM project\n" +
                "WHERE id=?";
        System.out.println(sql);
        Object []params = {project.getId()};
        try {
            int rs = queryRunner.update(sql,params);
            if (rs>0){
                System.out.println("操作成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
        return false;
    }
    //按id查询单行所有数据
    @Override
    public Project queryAllById(Project project) {
        String sql = "SELECT * FROM project\n" +
                "WHERE id = ?";
        System.out.println(sql);
        Object []params = {project.getId()};
        Project project1 = null;
        try {
            project1 = queryRunner.query(sql,new BeanHandler<>(Project.class),params);
            if (project1!=null){
                System.out.println("操作成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
        return null;
    }
    //修改
    @Override
    public boolean update(Project project) {
        String sql = "UPDATE project\n" +
                "SET `identifier`=?,`project_name`=?,`business_id`=?,`createp_id`=?,`status`=?,`uptime`=?,`prolong`=?,`createtime`=?,`description`=?\n" +
                "WHERE id =?";
        System.out.println(sql);
        Object []params = {project.getIdentifier(),project.getProject_name(),project.getBusiness_id(),project.getCreatep_id(),project.getStatus(),project.getUptime(),project.getProlong(),project.getCreatetime(),project.getDescription(),project.getId()};
        try {
            int rs = queryRunner.update(sql,params);
            if (rs>0){
                System.out.println("操作成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
        return false;
    }
    //按项目名称查找
    @Override
    public List<Project> selectProjName(Project project) {
        String sql="SELECT `project_name` FROM `project`";
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
    //查询所有
    @Override
    public List<Project> queryAll(Project project) {
        StringBuffer sql = new StringBuffer("SELECT * \n" +
                "FROM project");
        System.out.println(sql);
//        if (project!=null){
//            if (!"".equals(project.getProject_name())&& project.getProject_name()!=null){
//                sql.append(" and project_name LIKE '%"+project.getProject_name()+"%'");
//            }
//        }
        List<Project> list =null;
        try {
            list = queryRunner.query(String.valueOf(sql),new BeanListHandler<>(Project.class));
            if (list.size()>0 && list!=null){
                System.out.println("list======"+list);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询所有带分页
    @Override
    public List<Project> queryAllPage(Project project, PageBean pageBean) {
        StringBuffer sql = new StringBuffer("SELECT * \n" +
                "FROM project");
        System.out.println(sql);
//        if (project!=null){
//            if (!"".equals(project.getProject_name())&& project.getProject_name()!=null){
//                sql.append(" and project_name LIKE '%"+project.getProject_name()+"%'");
//            }
//        }
        if (pageBean!=null){
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        List<Project> list =null;
        try {
            list = queryRunner.query(String.valueOf(sql),new BeanListHandler<>(Project.class));
            if (list.size()>0 && list!=null){
                System.out.println("list====="+list);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
