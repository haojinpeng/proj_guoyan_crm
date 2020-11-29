package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Workorder;
import com.it.dao.IWorkorder_DAO;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.sql.SQLException;
import java.util.List;

public class Workorder_DAOImpl implements IWorkorder_DAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    //查询不重复的项目名称
    @Override
    public List<Workorder> queryDistinctProject_name(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT DISTINCT pj.project_name \n" +
                "FROM workorder wk,project pj\n" +
                "WHERE wk.`project_id` = pj.id";
        //2.打印sql
        System.out.println(sql);
        //3.执行并返回结果
        List<Workorder> workorderList = null;
        try {
            workorderList = queryRunner.query(sql,new BeanListHandler<>(Workorder.class));
            for (Workorder workorder1 : workorderList) {
                System.out.print(workorder1);
            }
            return workorderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询工单申请数量 一个条件
    @Override
    public int queryWorkorderCountByPro(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT COUNT(workorder.id) \n" +
                "FROM workorder,project \n" +
                "WHERE project_name = ?\n" +
                "AND workorder.`project_id`=project.`id`";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {workorder.getProject_name()};
        //4.执行并返回结果
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(),params);
            System.out.println("count"+num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //查询工单解决数量 一个条件
    @Override
    public int queryWorkorder_ProcessCountByPro(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT COUNT(workorder.id) \n" +
                "FROM workorder,project\n" +
                "WHERE project_name = ?\n" +
                "AND workorder.`status` = 1\n" +
                "AND workorder.`project_id`=project.`id`";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {workorder.getProject_name()};
        //4.执行并返回结果
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //查询工单回访数量 一个条件
    @Override
    public int queryWorkorder_VisitCountByPro(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT COUNT(wkp.id)\n" +
                "FROM workorder wk,workorder_process wkp,project\n" +
                "WHERE wkp.`workorder_id` IN \n" +
                "(SELECT wk.id\n" +
                "FROM workorder wk,project pj\n" +
                "WHERE pj.project_name = ?\n" +
                "AND wk.`project_id`=pj.`id`)\n" +
                "AND wk.STATUS = 1\n" +
                "AND wkp.status = 1\n" +
                "AND wk.`project_id`=project.`id`\n" +
                "AND wk.id = wkp.id";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {workorder.getProject_name()};
        //4.执行并返回结果
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    //查询工单申请数量 两个条件
    @Override
    public int queryWorkorderCount(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT COUNT(wk.id) \n" +
                "FROM workorder wk,customer co,project pj\n" +
                "WHERE co.customer_name = ?\n" +
                "AND pj.project_name = ?\n" +
                "AND wk.`project_id`=pj.`id`\n" +
                "AND wk.`customer_id` = co.id";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {workorder.getCustomer_name(),workorder.getProject_name()};
        //4.执行并返回结果
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(),params);
            System.out.println("count"+num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //查询工单解决数量 两个条件
    @Override
    public int queryWorkorder_ProcessCount(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT COUNT(wk.id) \n" +
                "FROM workorder wk,customer co,project pj\n" +
                "WHERE co.customer_name = ?\n" +
                "AND pj.project_name = ? \n" +
                "AND wk.STATUS = 1\n" +
                "AND wk.`project_id`=pj.`id`\n" +
                "AND wk.`customer_id` = co.id";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {workorder.getCustomer_name(),workorder.getProject_name()};
        //4.执行并返回结果
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //查询工单回访数量 两个条件
    @Override
    public int queryWorkorder_VisitCount(Workorder workorder) {
        //1.编写sql
        String sql = "SELECT COUNT(wkp.id) \n" +
                "FROM workorder wk,workorder_process wkp,customer co,project pj\n" +
                "WHERE co.customer_name = ? \n" +
                "AND pj.project_name = ? \n" +
                "AND wk.status = 1\n" +
                "AND wkp.status = 1\n" +
                "AND wk.id = wkp.workorder_id\n" +
                "AND wk.`project_id`=pj.`id`\n" +
                "AND wk.`customer_id` = co.id";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {workorder.getCustomer_name(),workorder.getProject_name()};
        //4.执行并返回结果
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    @Override
    public List<Workorder> queryAllWorkorderAndLimit(PageBean pageBean, Workorder workorder) {
        //1.编写sql
        StringBuffer sql = new StringBuffer("SELECT DISTINCT pj.project_name,co.customer_name,ct.`createtime`\n" +
                "FROM project pj,customer co,workorder wk,contract ct\n" +
                "WHERE wk.`project_id` = pj.id\n" +
                "AND wk.`customer_id` = co.id\n" +
                "AND ct.`project_id` = pj.id\n" +
                "AND ct.`custom_id` = co.id\n ");
        if(workorder !=null){
            //判定账户名的文本框的合法性
            // "" null判定
            if(!"".equals(workorder.getCustomer_name()) && workorder.getCustomer_name()!=null) {
                sql.append(" AND customer_name LIKE '%"+workorder.getCustomer_name()+"%'");
            }
            if(!"".equals(workorder.getProject_name()) && workorder.getProject_name()!=null) {
                sql.append(" AND project_name = '"+workorder.getProject_name()+"'");
            }
            if(!"".equals(workorder.getCreatetime()) && workorder.getCreatetime()!=null) {
                sql.append(" AND ct.createtime  = '"+workorder.getCreatetime()+"'");
            }

        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        //2.打印sql
        System.out.println(sql);
        //3.执行并返回
        List<Workorder> workorderList = null;
        try {
            workorderList = queryRunner.query(sql.toString(),new BeanListHandler<>(Workorder.class));
            for (Workorder workorder1 : workorderList) {
                System.out.print(workorder1);
            }
            return workorderList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    @Override
    public int queryAllWorkorderCount(Workorder workorder) {
        StringBuffer sql = new StringBuffer("SELECT DISTINCT pj.project_name,co.customer_name\n" +
                "FROM project pj,customer co,workorder wk,contract ct\n" +
                "WHERE wk.`project_id` = pj.id\n" +
                "AND wk.`customer_id` = co.id\n" +
                "AND ct.`project_id` = pj.id\n" +
                "AND ct.`custom_id` = co.id ");
        //有条件判断条件，重新拼接sql
        if(workorder !=null){
            //判定账户名的文本框的合法性
            // "" null判定
            if(!"".equals(workorder.getCustomer_name()) && workorder.getCustomer_name()!=null) {
                sql.append(" AND customer_name LIKE '%"+workorder.getCustomer_name()+"%'");
            }
            if(!"".equals(workorder.getProject_name()) && workorder.getProject_name()!=null) {
                sql.append(" AND project_name = '"+workorder.getProject_name()+"'");
            }
            if(!"".equals(workorder.getCreatetime()) && workorder.getCreatetime()!=null) {
                sql.append(" AND ct.createtime  = '"+workorder.getCreatetime()+"'");
            }
        }
        Number num;
        List<Workorder> workorderList = null;
        try {
            workorderList = queryRunner.query(sql.toString(), new BeanListHandler<>(Workorder.class));
            num = workorderList.size();
            System.out.println("2333334"+num);
            return (int) num;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
}
