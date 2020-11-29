package com.it.dao;

import com.it.bean.Expenses_apply;
import com.it.bean.Expenses_approval;
import com.it.bean.PageBean;
import com.it.dao.IExpenses_approval_DAO;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class Expenses_approval_DAOImpl implements IExpenses_approval_DAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean addExpenses_approval(Expenses_approval expenses_approval) {
        //1.编写sql
        String sql="INSERT INTO project_expenses_approval(expenses_id,reviewer_id)\n" +
                "VALUES (?,?)";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object[] params = {expenses_approval.getExpenses_id(),expenses_approval.getParent_Reviewer()};
        //4.执行并返回
        try {
            int rows = queryRunner.update(sql,params);
            if (rows > 0){
                System.out.println("添加成功");
            }
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
    }

    @Override
    public boolean updExpenses_approval(Expenses_approval expenses_approval) {
        //1.编写sql
        String sql = "UPDATE project_expenses_approval SET createtime=?,STATUS=?,description=?,parent_Reviewer=?\n" +
                "WHERE id = ?\n";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {expenses_approval.getCreatetime(),expenses_approval.getStatus(),
        expenses_approval.getDescription(),expenses_approval.getParent_Reviewer(),expenses_approval.getId()};
        //4.执行并返回结果
        try {
            int rows = queryRunner.update(sql,params);
            if (rows > 0){
                System.out.println("修改成功");
            }
            return rows > 0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public boolean updExpenses_apply_Status(Expenses_approval expenses_approval) {
        //1.编写sql
        String sql = "UPDATE `project_expenses_apply` SET `project_expenses_apply`.`apply_status` = ? \n" +
                "WHERE `project_expenses_apply`.`id` = ?\n";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        System.out.println("here"+expenses_approval.getStatus());
        Object params[] = {expenses_approval.getStatus(),expenses_approval.getExpenses_id()};
        //4.执行并返回结果
        try {
            int rows = queryRunner.update(sql,params);
            if (rows > 0){
                System.out.println("修改成功");
            }
            return rows > 0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public int queryParent_id(Expenses_approval expenses_approval) {
        String sql = "SELECT ap.parent_id\n" +
                "FROM `approval_process` ap,USER us\n" +
                "WHERE ap.approval_user = \n" +
                "(SELECT us.id\n" +
                "FROM USER us\n" +
                "WHERE us.`login_name` = ?)\n" +
                "AND ap.approval_user = us.`id` ";
        //传递参数
        Object params[] = {expenses_approval.getLogin_name()};
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(),params);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    @Override
    public boolean updExpenses_apply_Status_Boos(Expenses_approval expenses_approval) {
        //1.编写sql
        String sql = "UPDATE `project_expenses_apply` pea\n" +
                "SET pea.apply_status = ?\n" +
                "WHERE pea.`id` = ?";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {expenses_approval.getStatus(),expenses_approval.getId()};
        //4.执行并返回结果
        try {
            int rows = queryRunner.update(sql,params);
            if (rows > 0){
                System.out.println("修改成功");
            }
            return rows > 0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public List<Expenses_approval> queryAllLikeExpenses_approval(Expenses_approval expenses_approval) {
        return null;
    }

    @Override
    public List<Expenses_approval> queryAllExpenses_approvalAndLimit(PageBean pageBean, Expenses_approval expenses_approval) {
        //1.编写sql
        StringBuffer sql = new StringBuffer("SELECT us.username approvalUsername,us.`login_name`,us1.username applyUsername,pea.id,pea.expenses_id,pea.createtime,STATUS,description,parent_Reviewer\n" +
                "FROM USER us,USER us1,`project_expenses_approval` pea,`project_expenses_apply` pe\n" +
                "WHERE us.`login_name` = ?\n" +
                "AND us.id = pea.reviewer_id\n" +
                "AND pea.`expenses_id` = pe.id\n" +
                "AND us1.id = pe.apply_user ");
        //传递参数
        Object params[] = {expenses_approval.getLogin_name()};

        if(expenses_approval !=null){
            //判定账户名的文本框的合法性
            // "" null判定
            if(!"".equals(expenses_approval.getExpenses_id()) && String.valueOf(expenses_approval.getExpenses_id())!=null && expenses_approval.getExpenses_id()!=0) {
                sql.append("AND pea.`expenses_id` = '"+ expenses_approval.getExpenses_id()+"'");
            }
            if(!"".equals(expenses_approval.getStatus()) && String.valueOf(expenses_approval.getStatus())!=null && expenses_approval.getStatus()!=0) {
                sql.append("AND pea.`status` = '"+ expenses_approval.getStatus()+"'");
            }
            if(!"".equals(expenses_approval.getCreatetime()) && expenses_approval.getCreatetime()!=null) {
                sql.append("AND pea.`createtime` = '"+ expenses_approval.getCreatetime()+"'");
            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        //2.打印sql
        System.out.println(sql);
        System.out.println(expenses_approval.getExpenses_id());
        //3.执行并返回
        List<Expenses_approval> expenses_approvalList = null;
        try {
            expenses_approvalList = queryRunner.query(sql.toString(),new BeanListHandler<>(Expenses_approval.class),params);
            for (Expenses_approval expenses_approval1 : expenses_approvalList) {
                System.out.print(expenses_approval1);
            }
            return expenses_approvalList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    @Override
    public int queryExpenses_approvalCount(Expenses_approval expenses_approval) {
        StringBuffer sql = new StringBuffer("SELECT COUNT(*)\n" +
                "FROM USER us,`project_expenses_approval` pea\n" +
                "WHERE us.`login_name` = ?\n" +
                "AND us.id = pea.reviewer_id ");
        //传递参数
        Object params[] = {expenses_approval.getLogin_name()};
        //有条件判断条件，重新拼接sql
        if(expenses_approval !=null){
            //判定账户名的文本框的合法性
            // "" null判定
            if(!"".equals(expenses_approval.getExpenses_id()) && String.valueOf(expenses_approval.getExpenses_id())!=null && expenses_approval.getExpenses_id()!=0) {
                sql.append("AND pea.`expenses_id` = '"+ expenses_approval.getExpenses_id()+"'");
            }
            if(!"".equals(expenses_approval.getStatus()) && String.valueOf(expenses_approval.getStatus())!=null && expenses_approval.getStatus()!=0) {
                sql.append("AND pea.`status` = '"+ expenses_approval.getStatus()+"'");
            }
            if(!"".equals(expenses_approval.getCreatetime()) && expenses_approval.getCreatetime()!=null) {
                sql.append("AND pea.`createtime` = '"+ expenses_approval.getCreatetime()+"'");
            }
        }
        System.out.println(sql.toString());
        Number num;
        try {
            num = (Number) queryRunner.query(sql.toString(), new ScalarHandler<>(),params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
}
