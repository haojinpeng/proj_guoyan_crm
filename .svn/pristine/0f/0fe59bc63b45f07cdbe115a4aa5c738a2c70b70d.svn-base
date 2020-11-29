package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.ProjectVo;
import com.it.bean.Project_task;
import com.it.dao.Project_taskDao;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class Project_taskDaoImpl implements Project_taskDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public boolean addProjectVo(ProjectVo projectVo) {
        //1.编写SQL语句
        String sql = "INSERT INTO project_task (project_id,name,status)VALUES(?,?,?)";
        //2 打印SQL
        System.out.println("sql--->" + sql);
        Object params[] = {projectVo.getProject_id(),projectVo.getName(),projectVo.getStatus()};

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
    public boolean updateProject_task(Project_task project_task) {
        // 1 ��дsql���
        String sql = "UPDATE `project_task`\n" +
                "SET  `project_id` = ?,\n" +
                "`status` = ?,\n" +
                "  `actul_time` = ? \n" +
                "WHERE  `project_id` = ?;";

        System.out.println("sql-->" + sql);

        // 3 ����ռλ��
        Object params[] = {project_task.getProject_id(), project_task.getStatus(),project_task.getActul_time(),project_task.getProject_id() };

        try {
            int rs = queryRunner.update(sql, params);
            return rs>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("修改失败！");
        }

    }

    @Override
    public List<ProjectVo> queryAllProjectVo(PageBean pageBean, ProjectVo projectVo,String startTime, String endTime) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT p2.`project_id`,p1.`project_name`,p2.`name`,p2.`plan_time`,p2.`actul_time`,p1.`createtime`,p2.`manager_id`,p2.`status`\n" +
                "FROM project AS p1,project_task AS p2\n" +
                "WHERE p1.`id`=p2.`project_id` and  1=1 ");

        if (projectVo != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(projectVo.getProject_name()) && projectVo.getProject_name()!=null) {
                sql.append(" and p1.project_name like  '%" + projectVo.getProject_name() + "%' ");
            }

            if (startTime != null && !"".equals(startTime)) {
                if (endTime != null && !"".equals(endTime)) {

                    sql.append(" and p1.createtime between '" + startTime + "'  and  '" + endTime + "'  ");
                }
            }

        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<ProjectVo> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(ProjectVo.class));

            for (ProjectVo st:listU
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
        String sql = "SELECT COUNT(*) FROM project_task";
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
