package com.it.dao;

import com.it.bean.Company;
import com.it.bean.PageBean;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public boolean addCompany(Company company) {
        //1.编写SQL语句
        String sql = "INSERT INTO company (company_id,company_type)VALUES(?,?)";
        //2 打印SQL
        System.out.println("sql--->" + sql);
        Object params[] = {company.getCompany_id(),company.getCompany_type()};

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
    public boolean updateCompany(Company company) {
        String sql = "UPDATE company SET \n" +
                " \n" +
                "  `company_id` = ?,\n" +
                " \n" +
                "  `company_type` = ?\n" +
                "  \n" +
                "WHERE `company_id` = ?;";

        System.out.println("sql-->" + sql);

        // 3 ����ռλ��
        Object params[] = {company.getCompany_id(),company.getCompany_type(),company.getCompany_id()};

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
    public boolean deleteCompany(Company company) {
        String sql = "DELETE FROM company WHERE company_id =?";

        System.out.println("sql-->" + sql);

        Object params[] = {company.getCompany_id() };

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
    public List<Company> queryAllCompany(PageBean pageBean, Company company) {
        StringBuffer sql = new StringBuffer("select company_id,company_type  from company where 1=1 ");

        if (company != null) {
            //判定账户名的文本框的合法性
            // "" null判定1nu
            if (!"".equals(company.getCompany_id()) && company.getCompany_id()!=null) {
                sql.append(" and company_id like '%" + company.getCompany_id() + "%' ");
            }
            if (!"".equals(company.getCompany_type()) && company.getCompany_type()!=null) {
                sql.append(" and company_type = '" + company.getCompany_type() + "' ");
            }
        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        //存放所有Users信息
        List<Company> listU = null;
        try {

            listU = queryRunner.query(sql.toString(), new BeanListHandler<>(Company.class));

            for (Company st:listU
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
        String sql = "SELECT COUNT(*) FROM company";
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
