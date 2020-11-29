package com.it.dao;

import com.it.bean.BusinessOpporitunity;
import com.it.bean.Business_User;
import com.it.bean.Business_contact;
import com.it.bean.Business_type;
import com.it.dao.IBusinessOppoDao;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.lang.model.element.QualifiedNameable;
import java.sql.SQLException;
import java.util.List;

public class IBusinessOppoDaoImpl implements IBusinessOppoDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean add(BusinessOpporitunity businessOppo) {
        //1.编写sql语句
        String sql = "insert into businessopporitunity(customers_id,business_type_id,contact,name,status,intention,userid,remarks) values(?,?,?,?,?,?,?,?)";
        //2.打印sql语句
        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {businessOppo.getCustomers_id(),businessOppo.getBusiness_type_id(),businessOppo.getContact(),businessOppo.getName(),businessOppo.getStatus(),businessOppo.getIntention(),businessOppo.getUserid(),businessOppo.getRemarks()};
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
    public boolean del(BusinessOpporitunity businessOppo) {
        //1.编写sql语句
        String sql = "delete from businessopporitunity where id = ?";
        //2.打印sql语句
//        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {businessOppo.getId()};
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
    public boolean upd(BusinessOpporitunity businessOppo) {
        //1.编写sql语句
        String sql = "UPDATE businessopporitunity SET customers_id=?,business_type_id=?,contact=?,name=?,status=?,intention=?,userid=?,remarks=? WHERE id=?";
        //2.打印sql语句
//        System.out.println("sql--->" + sql);
        //3.处理占位符
        Object params[] = {businessOppo.getCustomers_id(),businessOppo.getBusiness_type_id(),businessOppo.getContact(),businessOppo.getName(),businessOppo.getStatus(),businessOppo.getIntention(),businessOppo.getUserid(),businessOppo.getRemarks(),businessOppo.getId()};
        try {
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
    /**
     * 删除多条记录
     * @param delIds
     * @return
     */
    @Override
    public int dels(String  delIds) {
        System.out.println(delIds);
        String sql = "delete from  businessopporitunity where id in (" + delIds + ")";
        System.out.println("sql--->"+sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<BusinessOpporitunity> queryAllLike(String sql) {
        try {
            System.out.println(sql);
            List<BusinessOpporitunity> businessOppo = queryRunner.query(sql,new BeanListHandler<>(BusinessOpporitunity.class));
            if(businessOppo!=null){
                System.out.println(businessOppo);
                return businessOppo;
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
            num = (Number)queryRunner.query(sql.toString(),new ScalarHandler<>());

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

    @Override
    public List<Business_contact> queryContact() {
        //编写sql语句
        String sql = "select contact.id,contact.name,contact.phone from contact";
        try {
            System.out.println(sql);
            List<Business_contact> business_contacts= queryRunner.query(sql,new BeanListHandler<>(Business_contact.class));
            if(business_contacts!=null){
                System.out.println(business_contacts);
                return business_contacts;
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
    public List<BusinessOpporitunity> query() {
        String sql = "select * from BusinessOpporitunity";
        try {
            System.out.println(sql);
            List<BusinessOpporitunity> businessOppo = queryRunner.query(sql,new BeanListHandler<>(BusinessOpporitunity.class));
            if(businessOppo!=null){
                System.out.println(businessOppo);
                return businessOppo;
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
    public List<Business_User> queryUser() {
        String sql = "select * from user";

        try {
            System.out.println(sql);
            List<Business_User> business_types= queryRunner.query(sql,new BeanListHandler<>(Business_User.class));
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

    @Override
    public List<Business_type> queryFid() {
        String sql = "select * from business_tracking";
        try {
            System.out.println(sql);
            List<Business_type> business_types = queryRunner.query(sql, new BeanListHandler<>(Business_type.class));
            if (business_types != null) {
                System.out.println(business_types);
                return business_types;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}