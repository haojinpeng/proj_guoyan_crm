package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.ProjectApp;
import com.it.bean.ProjectVo;
import com.it.dao.ProjectAppDao;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProjectAppDapImpl implements ProjectAppDao {

    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<ProjectApp> queryAllProjectApp(PageBean pageBean, ProjectApp projectApp, String startTime, String endTime) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT p2.`project_id`,p1.`project_name`,p2.`name`,p1.`createtime`,p2.`manager_id`,p4.`status`\n" +
                "FROM project AS p1,project_task AS p2,project_expenses_apply AS p3,project_expenses_approval AS p4\n" +
                "WHERE p1.`id`=p2.`project_id`and p1.id=p3.project_id and p3.id = p4.expenses_id  and  1=1  ");

      System.out.println("projectApp--->"+projectApp);
        if (projectApp!=null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(projectApp.getProject_name()) && projectApp.getProject_name()!=null) {
                sql.append(" and p1.project_name like '%" + projectApp.getProject_name() + "%' ");
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
        List<ProjectApp> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(ProjectApp.class));

            for (ProjectApp st:listU
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
        String sql = "SELECT COUNT(*)\n" +
                "FROM project AS p1,project_task AS p2,project_expenses_apply AS p3,project_expenses_approval AS p4\n" +
                "WHERE  p1.`id`=p2.`project_id`AND p1.id=p3.project_id AND p3.id = p4.expenses_id";
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

    @Override
    public List<ProjectApp> selectProjectApp(ProjectApp projectApp) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT p3.apply_status,p3.remarks \n" +
                "FROM project AS p1,project_task AS p2,project_expenses_apply AS p3,project_expenses_approval AS p4\n" +
                "WHERE p1.`id`=p2.`project_id`and p1.id=p3.project_id and p3.id = p4.expenses_id  ");


        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<ProjectApp> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(ProjectApp.class));

            for (ProjectApp st:listU
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

}
