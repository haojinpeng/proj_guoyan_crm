package com.it.dao;

import com.it.bean.Company;
import com.it.bean.PageBean;
import com.it.bean.Procurement;
import com.it.dao.ProcurementDao;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProcurementDaoImpl implements ProcurementDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public boolean addProcurement(Procurement procurement) {
        //1.编写SQL语句
        String sql = "INSERT INTO procurement (procurement_id,procurement_type)VALUES(?,?,?)";
        //2 打印SQL
        System.out.println("sql--->" + sql);
        Object params[] = {procurement.getProcurement_id(),procurement.getProcurement_type()};

        try {
            int rs = queryRunner.update(sql, params);
            if (rs > 0) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败！！");
        } finally {
            try {
                JdbcUtils.getDataSource().getConnection().close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException("关闭资源失败!");
            }
            return true;
        }

    }

    @Override
    public boolean updateProcurement(Procurement procurement) {
        String sql = "UPDATE procurement SET \n" +
                " \n" +
                "  `procurement_id` = ?,\n" +
                " \n" +
                "  `procurement_type` = ?\n" +
                "  \n" +
                "WHERE `procurement_id` = ?;";

        System.out.println("sql-->" + sql);

        // 3 ����ռλ��
        Object params[] = {procurement.getProcurement_id(),procurement.getProcurement_type(),procurement.getProcurement_id()};

        try {
            int rs = queryRunner.update(sql, params);
            System.out.println("rs-->"+rs);

            return rs>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("修改失败！");
        }

    }

    @Override
    public boolean deleteProcurement(Procurement procurement) {
        String sql = "DELETE FROM procurement WHERE procurement_id =?";

        System.out.println("sql-->" + sql);

        Object params[] = {procurement.getProcurement_id() };

        try {
            queryRunner.update(sql, params);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("删除失败！");
        }
        return false;
    }

    @Override
    public List<Procurement> queryAllProcurement(PageBean pageBean, Procurement procurement) {
        StringBuffer sql = new StringBuffer("select procurement_id,procurement_type  from procurement where 1=1 ");

        if (procurement != null) {
            //判定账户名的文本框的合法性
            // "" null判定1nu
            if (!"".equals(procurement.getProcurement_id()) && procurement.getProcurement_id()!=null) {
                sql.append(" and procurement_id like '%" + procurement.getProcurement_id() + "%' ");
            }
            if (!"".equals(procurement.getProcurement_type()) && procurement.getProcurement_type()!=null) {
                sql.append(" and procurement_type = '" + procurement.getProcurement_type() + "' ");
            }
        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<Procurement> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(Procurement.class));

            for (Procurement st:listU
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
        String sql = "SELECT COUNT(*) FROM procurement";
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

}
