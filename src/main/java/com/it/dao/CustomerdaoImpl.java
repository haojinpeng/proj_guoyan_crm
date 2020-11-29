package com.it.dao;

import com.it.bean.Customer;
import com.it.bean.PageBean;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CustomerdaoImpl implements ICustomerdao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public boolean add(Customer customer) {
        //编写sql语句
        String sql="insert into customer values(?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        Object[] parame={customer.getId(),customer.getCustomer_name(),customer.getCountry(),customer.getProvince(),customer.getCity(),customer.getAddress(),customer.getClientType(),customer.getContact()};

        try {
            int rows=queryRunner.update(sql, parame);
            return rows>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("添加失败!!");
        }
    }

    @Override
    public boolean delete(Customer customer) {
        //编写sql语句
        String sql="delete from customer where id=?";
        System.out.println(sql);
        Object[] parame={customer.getId()};
        try {
            int i=queryRunner.update(sql, parame);
            return i>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("删除失败!!");
        }
    }

    @Override
    public int Deletess(String delIds) {
        System.out.println(delIds);
        String sql = "delete from  customer where id in (" + delIds + ")";
        System.out.println("sql--->"+sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     ///查询客户
    @Override
    public List<Customer> queryAllkehu(PageBean pageBean, Customer customer) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT id,customer_name FROM `customer` where 1=1 ");


        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<Customer> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(Customer.class));

          /*  for (Customer st:listU
            ) {
                System.out.println(st);

            }*/
            System.out.println("listU--->"+listU);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }


        return listU;
    }

    @Override
    public boolean update(Customer customer) {
        //编写sql语句
        String sql="update customer set customer_name=?,country=?,province=?,city=?,address=?,clientType=?,contact=? where id=? ";
        System.out.println(sql);
        Object[]parame={customer.getCustomer_name(),customer.getCountry(),customer.getProvince(),customer.getCity(),customer.getAddress(),customer.getClientType(),customer.getContact(),customer.getId()};
        try {
            int in=queryRunner.update(sql, parame);
            return in>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("修改失败!!");
        }
    }

    @Override
    public List<Customer> queryAll(PageBean pageBean, Customer customer) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT c.`id`,c.`customer_name`,co.`c_name`,p.`p_name`,ci.`city_name`,c.`address`,c.`clientType`,c.`contact` \n" +
                " FROM customer c,`country` co,`province` p,`city`ci WHERE c.`country`=co.`id` AND c.`province`=p.`id` AND c.`city`=ci.`id` and 1=1 ");

        if(customer!=null){

            if(!"".equals(customer.getId()) && customer.getId()!=null){
                //2.拼接sql
                sql.append(" and c.id like '%"+customer.getId()+"%' ");

            }
            if(!"".equals(customer.getCustomer_name())&&customer.getCustomer_name()!=null){
                //2.拼接sql
                sql.append(" and c.customer_name like '%"+customer.getCustomer_name()+"%' ");
            }
            //判定账户名的文本框的合法性

        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<Customer> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(Customer.class));

          /*  for (Customer st:listU
            ) {
                System.out.println(st);

            }*/
            System.out.println("listU--->"+listU);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }


        return listU;
    }

    @Override
    public int Count(Customer customer) {
        StringBuffer sb = new StringBuffer("select count(*) from customer where 1=1 ");
        Number num;
        try {
            num = (Number)queryRunner.query(sb.toString(),new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
}
