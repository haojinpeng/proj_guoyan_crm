package com.it.dao;

import com.it.bean.Expenses_apply;
import com.it.bean.PageBean;
import com.it.bean.Workorder;
import com.it.dao.IExpenses_apply_DAO;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class Expenses_apply_DAOImpl implements IExpenses_apply_DAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean addExpenses_apply(Expenses_apply expenses_apply) {
        //1.编写sql
        String sql="INSERT INTO project_expenses_apply(project_id,apply_user,createtime,\n" +
                "apply_money,apply_type,apply_description,commodity,reviewer_id) VALUES\n" +
                "(?,?,?,?,?,?,?,?)";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object[] params = {expenses_apply.getProject_id(),expenses_apply.getApply_user(),
        expenses_apply.getCreatetime(),expenses_apply.getApply_money(),expenses_apply.getApply_type(),
        expenses_apply.getApply_description(),expenses_apply.getCommodity(),expenses_apply.getReviewer_id()};
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
    public boolean delExpenses_apply(Expenses_apply expenses_apply) {
        //1.编写sql
        String sql = "DELETE FROM project_expenses_apply WHERE id = ?";
        //2.打印sql
        System.out.println(sql);
        //3.处理参数
        Object[] params = {expenses_apply.getId()};
        //4.执行并返回结果
        try {
            int rows = queryRunner.update(sql,params);
            if (rows >0){
                System.out.println("删除成功");
            }
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public boolean updExpenses_apply(Expenses_apply expenses_apply) {
        //1.编写sql
        String sql = "UPDATE project_expenses_apply SET \n" +
                "project_id=?,apply_user = ?,createtime =?,\n" +
                "apply_money=?,apply_type=?,apply_description=?,commodity=?\n" +
                "WHERE id = ?";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {expenses_apply.getProject_id(),expenses_apply.getApply_user(),
        expenses_apply.getCreatetime(),expenses_apply.getApply_money(),expenses_apply.getApply_type(),
        expenses_apply.getApply_description(),expenses_apply.getCommodity(),expenses_apply.getId()};
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
    public boolean updExpenses_apply_Apply_status(Expenses_apply expenses_apply) {
        //1.编写sql
        String sql = "UPDATE `project_expenses_apply` pea\n" +
                "SET pea.apply_status = 3\n" +
                "WHERE pea.id = ?";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        Object params[] = {expenses_apply.getId()};
        //4.执行并返回结果
        try {
            int rows = queryRunner.update(sql,params);
            if (rows > 0){
                System.out.println("状态修改成功");
            }
            return rows > 0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("状态修改失败");
        }
    }

    @Override
    public List<Expenses_apply> queryAllExpenses_apply() {
        return null;
    }

    @Override
    public boolean submitExpenses_apply(Expenses_apply expenses_apply) {
        //1.编写sql
        String sql="INSERT INTO project_expenses_approval(expenses_id,reviewer_id)\n" +
                "SELECT id,reviewer_id FROM project_expenses_apply WHERE id = ?\n";
        //2.打印sql
        System.out.println(sql);
        //3.传递参数
        System.out.println(expenses_apply.getId());
        Object[] params = {expenses_apply.getId()};
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
    public List<Expenses_apply> queryAllLikeExpenses_apply(Expenses_apply expenses_apply) {
        return null;
    }

    @Override
    public List<Expenses_apply> queryAllExpenses_applyAndLimit(PageBean pageBean, Expenses_apply expenses_apply) {
        //1.编写sql
        StringBuffer sql = new StringBuffer("SELECT pea.id,pro.project_name,us.username,pea.createtime\n" +
                ",pea.apply_money,pea.apply_type,pea.apply_status,pea.apply_description,\n" +
                "pea.gistaddr,pea.commodity,pea.reviewer_id,\n" +
                "pea.project_id\n" +
                "FROM project_expenses_apply pea,project pro,USER us\n" +
                "WHERE us.`login_name` = ?\n" +
                "AND pea.project_id = pro.id\n" +
                "AND pea.apply_user = us.id\n ");
        if(expenses_apply !=null){
            //判定账户名的文本框的合法性
            // "" null判定
            //这里有个bug,就是project_id不能为0
            if(String.valueOf(expenses_apply.getProject_id())!=null&& !"".equals(expenses_apply.getProject_id()) &&expenses_apply.getProject_id()!=0) {
                //拼接sql
                sql.append("HAVING project_id = '"+ expenses_apply.getProject_id()+"' ");
            }
            if(!"".equals(expenses_apply.getCreatetime()) && expenses_apply.getCreatetime()!=null) {
                sql.append(" HAVING createtime = '"+ expenses_apply.getCreatetime()+"'");
            }
            if (String.valueOf(expenses_apply.getApply_status()) != null && !"".equals(expenses_apply.getApply_status()) &&expenses_apply.getApply_status()!=0){
                sql.append(" HAVING apply_status = '"+ expenses_apply.getApply_status()+"'");
            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        //2.打印sql
        System.out.println(sql);
        //传递参数
        Object[] params = {expenses_apply.getLogin_name()};
        //3.执行并返回
        List<Expenses_apply> expenses_applyList = null;
        try {
            expenses_applyList = queryRunner.query(sql.toString(),new BeanListHandler<>(Expenses_apply.class),params);
            for (Expenses_apply expenses_apply1 : expenses_applyList) {
                System.out.println(expenses_apply1);
            }
            return expenses_applyList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }

    @Override
    public int queryExpenses_applyCount(Expenses_apply expenses_apply) {
        StringBuffer sql = new StringBuffer("SELECT * \n" +
                "FROM project_expenses_apply pea,USER us \n" +
                "WHERE us.`login_name` = ?\n" +
                "AND pea.`apply_user` = us.`id`");
        //有条件判断条件，重新拼接sql
        if (expenses_apply != null) {
            //判定账户名的文本框的合法性
            // "" null判定
            if(String.valueOf(expenses_apply.getProject_id())!=null&& !"".equals(expenses_apply.getProject_id()) &&expenses_apply.getProject_id()!=0) {
                //拼接sql
                sql.append("and project_id = '"+ expenses_apply.getProject_id()+"' ");
            }
            if(!"".equals(expenses_apply.getCreatetime()) && expenses_apply.getCreatetime()!=null) {
                sql.append(" and createtime = '"+ expenses_apply.getCreatetime()+"'");
            }
            if (String.valueOf(expenses_apply.getApply_status()) != null && !"".equals(expenses_apply.getApply_status()) &&expenses_apply.getApply_status()!=0){
                sql.append(" and apply_status = '"+ expenses_apply.getApply_status()+"'");
            }
        }
        System.out.println(sql);
        //传递参数
        Object[] params = {expenses_apply.getLogin_name()};
        Number num;
        List<Expenses_apply> expenses_applyList = null;
        try {
            expenses_applyList = queryRunner.query(sql.toString(), new BeanListHandler<>(Expenses_apply.class),params);
            System.out.println(expenses_applyList);
            num = expenses_applyList.size();
            return (int) num;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
}
