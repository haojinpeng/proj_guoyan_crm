package com.it.dao;

import com.it.bean.Contact;
import com.it.bean.PageBean;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class Contactdao implements IContactdao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public boolean add(Contact contact) {
        //编写sql语句/
        String sql=" INSERT INTO `contact`(`name`,`sex`,`age`,`type`,`nation`,`country`,`province`,`city`,`phone`,`address`) \n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        Object[] parame={contact.getName(),contact.getSex(),contact.getAge(),contact.getType(),contact.getNation(),contact.getCountry(),contact.getProvince(),contact.getCity(),contact.getPhone(),contact.getAddress()};

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
    public boolean logion(Contact contact) {
        return false;
    }

    @Override
    public boolean del(Contact contact) {

        //编写sql语句
        String sql="delete from contact where id=?";
        System.out.println(sql);
        Object[] parame={contact.getId()};
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
        String sql = "delete from  contact where id in (" + delIds + ")";
        System.out.println("sql--->"+sql);
        try {
            return queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean update(Contact contact) {
        //编写sql语句
        String sql="UPDATE contact SET `name`=?,`age`=?,`type`=?,`phone`=?,`address`=?,`nation`=?,`country`=?,`province`=?,`city`=?,`sex`=? WHERE id=? ";
        System.out.println(sql);
        Object[] parame={contact.getName(),contact.getAge(),contact.getType(),contact.getPhone(),contact.getAddress(),contact.getNation(),contact.getCountry(),contact.getProvince(),contact.getCity(),contact.getSex(),contact.getId()};
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
    public List<Contact> queryAllCotact(PageBean pageBean, Contact contact) {
        //编写sql
        StringBuffer sql = new StringBuffer("SELECT c.id,c.`name`,c.`sex`,c.`age`,ct.`name` AS username,n.`n_name` AS n_name,co.`c_name`,p.`p_name`,ci.`city_name`,c.`position`,c.`company`,c.`phone`,c.`email`,c.`qq`,c.`wechat`,c.`address`\n" +
                "FROM `contact` c,`contact_type` ct,`nation` n,`country` co,`province` p,city ci\n" +
                "WHERE c.`type`=ct.`id` AND c.`nation`=n.`id` AND c.`country`=co.`id` AND c.`province`=p.`id` AND c.`city`=ci.`id` and 1=1 ");
        if(contact!=null) {

            if (!" ".equals(contact.getName()) && contact.getName() != null) {
                //2.拼接sql
                sql.append(" and c.`name` like '%" + contact.getName() + "%' ");

            }
//            if(!" ".equals(user.getUsername())&&user.getUsername()!=null){
//                //2.拼接sql
//                sql.append(" and u.username like '%"+user.getUsername()+"%' ");
//            }
        }
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }


        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<Contact> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(Contact.class));

            for (Contact st:listU
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
    public int contactCount(Contact contact) {
        StringBuffer sb = new StringBuffer("select count(*) from contact where 1=1 ");
        //有条件判断条件，重新拼接sql
//        if(billsVo!=null){
//            //判定账户名的文本框的合法性
//            // "" null判定
//            if(!"".equals(student.getSno()) && student.getSno()!=null) {
//
//                //拼接sb
//                sb .append("and sno like '%"+student.getSno()+"%' ");
//
//            }
//
//            if(!"".equals(student.getSname()) && student.getSname()!=null) {
//                sb .append(" and sname like '%"+student.getSname()+"%'");
//
//            }
//        }
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
