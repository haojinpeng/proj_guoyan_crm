package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project_cost_statistics;
import com.it.bean.ResourceAndJurisdiction;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Project_cost_statisticsDAOImpl implements Project_cost_statisticsDAO{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public List<Project_cost_statistics> queryAll(PageBean pageBean, Project_cost_statistics project_cost_statistics) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT \n" +
                "  pro.`id`,\n" +
                "  pro.`project_name` AS projectName,\n" +
                "  cus.`customer_name` AS customerName,\n" +
                "  pim.`start_time` AS startTime,\n" +
                "  pim.`actual_time` AS endTime,\n" +
                "  pim.`actual_cycle` AS workHourCost,\n" +
                "  SUM(pica.`money`) AS implementCost,\n" +
                "  SUM(pea.`apply_money`) AS projectProcurementCost,\n" +
                "  SUM(bos.`reality_consume`) AS businessOpportunityCost \n" +
                " FROM\n" +
                "  project AS pro,\n" +
                "  contract AS con,\n" +
                "  customer AS cus,\n" +
                "  project_implementation AS pim,\n" +
                "  project_implementation_cost_apply AS pica,\n" +
                "  project_expenses_apply AS pea,\n" +
                "  businessopporitunity AS bo,\n" +
                "  businessopporitunity_cost AS bos \n" +
                " WHERE pro.`status` = 2 \n" +
                "  AND pro.`id` = con.`project_id` \n" +
                "  AND con.`custom_id` = cus.`id` \n" +
                "  AND pro.`id` = pim.`project_id` \n" +
                "  AND pim.`id` = pica.`project_implementation_id` \n" +
                "  AND pica.`status` = 1 \n" +
                "  AND pro.`id` = pea.`project_id` \n" +
                "  AND pea.`apply_status` = 1 \n" +
                "  AND pro.`business_id` = bo.`id` \n" +
                "  AND bo.`status` = 2 \n" +
                "  AND bo.`id` = bos.`business_id` \n" +
                "  AND bos.`audit_status` = 1 \n");
        if (project_cost_statistics!=null){
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(project_cost_statistics.getProjectName()) && project_cost_statistics.getProjectName() != null) {
                //拼接sql
                sql.append(" and pro.`project_name` like '%" + project_cost_statistics.getProjectName() + "%' ");
            }
            if (!"".equals(project_cost_statistics.getCustomerName()) && project_cost_statistics.getCustomerName() != null) {
                //拼接sql
                sql.append(" and cus.`customer_name` like '%" + project_cost_statistics.getCustomerName() + "%' ");
            }
            if (!"".equals(project_cost_statistics.getStartTime()) && project_cost_statistics.getStartTime() != null) {

                if (!"".equals(project_cost_statistics.getEndTime()) && project_cost_statistics.getEndTime() != null) {
                    //拼接sql
                    sql.append(" and pim.`start_time` between '" + project_cost_statistics.getStartTime() + "' and '"+ project_cost_statistics.getEndTime() +"'");
                    //拼接sql
                    sql.append(" and pim.`actual_time` between '" + project_cost_statistics.getStartTime() + "' and '"+ project_cost_statistics.getEndTime() +"'");
                }

            }
        }
        sql.append(" GROUP BY pro.`id`");
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<Project_cost_statistics> project_cost_statisticsList = null;
        try {
            project_cost_statisticsList = queryRunner.query(sql.toString(), new BeanListHandler<>(Project_cost_statistics.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return project_cost_statisticsList;
    }

    @Override
    public int count(Project_cost_statistics project_cost_statistics) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT \n" +
                "  pro.`id`,\n" +
                "  pro.`project_name` AS projectName,\n" +
                "  cus.`customer_name` AS customerName,\n" +
                "  pim.`start_time` AS startTime,\n" +
                "  pim.`actual_time` AS endTime,\n" +
                "  pim.`actual_cycle` AS workHourCost,\n" +
                "  SUM(pica.`money`) AS implementCost,\n" +
                "  SUM(pea.`apply_money`) AS projectProcurementCost,\n" +
                "  SUM(bos.`reality_consume`) AS businessOpportunityCost \n" +
                " FROM\n" +
                "  project AS pro,\n" +
                "  contract AS con,\n" +
                "  customer AS cus,\n" +
                "  project_implementation AS pim,\n" +
                "  project_implementation_cost_apply AS pica,\n" +
                "  project_expenses_apply AS pea,\n" +
                "  businessopporitunity AS bo,\n" +
                "  businessopporitunity_cost AS bos \n" +
                " WHERE pro.`status` = 2 \n" +
                "  AND pro.`id` = con.`project_id` \n" +
                "  AND con.`custom_id` = cus.`id` \n" +
                "  AND pro.`id` = pim.`project_id` \n" +
                "  AND pim.`id` = pica.`project_implementation_id` \n" +
                "  AND pica.`status` = 1 \n" +
                "  AND pro.`id` = pea.`project_id` \n" +
                "  AND pea.`apply_status` = 1 \n" +
                "  AND pro.`business_id` = bo.`id` \n" +
                "  AND bo.`status` = 2 \n" +
                "  AND bo.`id` = bos.`business_id` \n" +
                "  AND bos.`audit_status` = 1 \n");
        if (project_cost_statistics!=null){
            //判定账户名的文本框的合法性
            // "" null判定
            if (!"".equals(project_cost_statistics.getProjectName()) && project_cost_statistics.getProjectName() != null) {
                //拼接sql
                sql.append(" and pro.`project_name` like '%" + project_cost_statistics.getProjectName() + "%' ");
            }
            if (!"".equals(project_cost_statistics.getCustomerName()) && project_cost_statistics.getCustomerName() != null) {
                //拼接sql
                sql.append(" and cus.`customer_name` like '%" + project_cost_statistics.getCustomerName() + "%' ");
            }
            if (!"".equals(project_cost_statistics.getStartTime()) && project_cost_statistics.getStartTime() != null) {

                if (!"".equals(project_cost_statistics.getEndTime()) && project_cost_statistics.getEndTime() != null) {
                    //拼接sql
                    sql.append(" and pim.`start_time` between '" + project_cost_statistics.getStartTime() + "' and '"+ project_cost_statistics.getEndTime() +"'");
                    //拼接sql
                    sql.append(" and pim.`actual_time` between '" + project_cost_statistics.getStartTime() + "' and '"+ project_cost_statistics.getEndTime() +"'");
                }

            }
        }
        sql.append(" GROUP BY pro.`id`");
        System.out.println("sql-->" + sql);
        //存放所有Users信息
        List<Project_cost_statistics> project_cost_statisticsList = null;
        try {
            project_cost_statisticsList = queryRunner.query(sql.toString(), new BeanListHandler<>(Project_cost_statistics.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
        return project_cost_statisticsList.size();
    }
}
