package com.it.dao;

import com.it.bean.Business_cost;
import com.it.bean.Business_tracking;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class IBCostDaoImpl implements IBCostDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean add(Business_cost cost) {
        //1.编写sql语句
        String sql = "insert into businessopporitunity_cost(id,business_id,business_tracking,consume_type,reality_consume,perdict_consume,cost_declarant,cost_description) values(?,?,?,?,?,?,?,?)";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {cost.getId(),cost.getBusiness_id(),cost.getBusiness_tracking(),cost.getConsume_type(),cost.getReality_consume(),cost.getPerdict_consume(),cost.getCost_declarant(),cost.getCost_description()};
        try {
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.释放资源
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean del(Business_cost cost) {
        //1.编写sql语句
        String sql = "delete from businessopporitunity_cost where id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {cost.getId()};
        try {
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean update(Business_cost cost) {
        //1.编写sql语句
        String sql = "UPDATE businessopporitunity_cost SET audit_status = ?  WHERE id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {cost.getAudit_status(),cost.getId()};
        try{
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.释放资源
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean upd(Business_cost cost) {
        //1.编写sql语句
        String sql = "UPDATE businessopporitunity_cost SET business_id=?,business_tracking=?,consume_type=?,reality_consume=?,perdict_consume=?,cost_declarant=?,cost_description=? WHERE id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {cost.getBusiness_id(),cost.getBusiness_tracking(),cost.getConsume_type(),cost.getReality_consume(),cost.getPerdict_consume(),cost.getCost_declarant(),cost.getCost_description(),cost.getId()};
        try{
            int row = queryRunner.update(sql,params);
            if(row>0){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.释放资源
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Business_cost> queryAllLike(String sql) {
        try {
            System.out.println(sql);
            List<Business_cost> costs = queryRunner.query(sql,new BeanListHandler<>(Business_cost.class));
            if(costs!=null){
                System.out.println(costs);
                return costs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 删除多条记录
     * @param delIds
     * @return
     */
    @Override
    public int dels(String  delIds) {
        System.out.println(delIds);
        String sql = "delete from  businessopporitunity_cost where id in (" + delIds + ")";
        System.out.println("sql--->"+sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int count(String sql) {
        Number num;
        try {
            num = (Number)queryRunner.query(sql,new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Business_cost> queryType() {
        return null;
    }
}
