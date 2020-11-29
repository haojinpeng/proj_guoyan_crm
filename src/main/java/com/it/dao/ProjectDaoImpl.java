package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.dao.ProjectDao;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public boolean addProject(Project project) {
        //1.编写SQL语句
        String sql = "INSERT INTO project (identifier,project_name,uptime,prolong,createtime,createp_id,status)VALUES(?,?,?,?,?,?,?)";
        //2 打印SQL
        System.out.println("sql--->" + sql);
        Object params[] = {project.getIdentifier(), project.getProject_name(), project.getUptime(), project.getProlong(), project.getCreatetime(), project.getCreatep_id(), project.getStatus()};

        try {
            int rs = queryRunner.update(sql, params);
            if (rs > 0) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败！！");
        } finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException("关闭资源失败!");
            }
            return true;
        }


    }

    @Override
    public boolean updateProject(Project project) {
        // 1 ��дsql���
        String sql = "UPDATE project SET \n" +
                " \n" +
                "  `project_name` = ?,\n" +
                " \n" +
                "  `createp_id` = ?,\n" +

                "  \n" +
                "  `createtime` = ?,\n" +
                "  `status` = ?\n" +
                "  \n" +
                "WHERE `identifier` = ?;";

         System.out.println("sql-->" + sql);

        // 3 ����ռλ��
        Object params[] = {project.getProject_name(),  project.getCreatep_id(), project.getCreatetime(), project.getStatus(),project.getIdentifier()};

        try {
            int rs = queryRunner.update(sql, params);
            System.out.println("rs-->"+rs);

          return rs>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("修改失败！");
        }

    }

    @Override
    public boolean deleteProject(Project project) {
        // 1 ��дsql���
        String sql = "DELETE FROM project WHERE identifier =?";

         System.out.println("sql-->" + sql);

        Object params[] = {project.getIdentifier() };

        try {
            queryRunner.update(sql, params);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("删除失败！");
        }
        return false;
    }

    @Override
    public List<Project> queryAllProject(PageBean pageBean, Project project,String startTime, String endTime) {
        //编写sql
        StringBuffer sql = new StringBuffer("select identifier,project_name,uptime,prolong,createtime,createp_id,status from project where 1=1 ");

        if (project != null) {
            //判定账户名的文本框的合法性
            // "" null判定1nu
            if (!"".equals(project.getCreatep_id()) && project.getCreatep_id()!=null) {
                sql.append(" and project.createp_id = " + project.getCreatep_id() + " ");
            }

            if (startTime != null && !"".equals(startTime)) {
                if (endTime != null && !"".equals(endTime)) {

                    sql.append(" and project.createtime between '" + startTime + "'  and  '" + endTime + "'  ");
                }
            }


        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<Project> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(Project.class));

            for (Project st:listU
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }

        return listU;
    }
    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM project";
        int result = 0;

        try {
            // 转long包装类 然后 转成int
            result = ((Long) queryRunner.query(sql, new ScalarHandler())).intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}