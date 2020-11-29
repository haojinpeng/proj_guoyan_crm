package com.it.dao;

import com.it.bean.BusinessOpporitunity;
import com.it.bean.Business_tracking;
import com.it.bean.Business_type;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class IBTrackingDaoImpl implements IBTrackingDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean add(Business_tracking tracking) {
        //1.编写sql语句
        String sql = "insert into business_tracking(business,type,createdate,recorder,message,feedback_result,remarks) values(?,?,?,?,?,?,?)";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        System.out.println("sajhdgjsahd--->" + tracking.getFeedback_result());
        Object params[] = {tracking.getBusiness(),tracking.getType(),tracking.getCreatedate(),tracking.getRecorder(),tracking.getMessage(),tracking.getFeedback_result(),tracking.getRemarks()};
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
    public boolean del(Business_tracking tracking) {
        //1.编写sql语句
        String sql = "delete from business_tracking where id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {tracking.getId()};
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
    /**
     * 删除多条记录
     * @param delIds
     * @return
     */
    @Override
    public int dels(String  delIds) {
        System.out.println(delIds);
        String sql = "delete from  business_tracking where id in (" + delIds + ")";
        System.out.println("sql--->"+sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean upd(Business_tracking tracking) {
        //1.编写sql语句
        String sql = "UPDATE business_tracking SET business=?,type=?,createdate=?,recorder=?,message=?,feedback_result=?,remarks=? WHERE id=?";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {tracking.getBusiness(),tracking.getType(),tracking.getCreatedate(),tracking.getRecorder(),tracking.getMessage(),tracking.getFeedback_result(),tracking.getRemarks(),tracking.getId()};
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
    public List<Business_tracking> queryAllLike(String sql) {
        try {
            System.out.println(sql);
            List<Business_tracking> trackings = queryRunner.query(sql,new BeanListHandler<>(Business_tracking.class));
            if(trackings!=null){
                System.out.println(trackings);
                return trackings;
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
    public List<Business_type> queryType() {
        //编写sql语句
        String sql = "select * from business_type";
        try {
            System.out.println(sql);
            List<Business_type> business_types= queryRunner.query(sql,new BeanListHandler<>(Business_type.class));
            if(business_types!=null){
                System.out.println(business_types);
                return business_types;
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
}
