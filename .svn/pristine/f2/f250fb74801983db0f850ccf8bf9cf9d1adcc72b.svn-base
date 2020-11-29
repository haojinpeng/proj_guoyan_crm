package com.it.dao;

import com.it.bean.*;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class EmployeeContrDAOImpl implements EmployeeContrDAO {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    //按id查找
    @Override
    public EmployeeContribution selectById(EmployeeContribution ec) {
        String sql = "SELECT `employeecontribution`.`id`,user.`employee_number`,project.`project_name`,station.`station_name`,`workorder_process`.`workorder_id`,workorder.`number`,`project_implementation`.`cycle`,`project_work`.`actual_working_hours`,`project_expenses_apply`.`createtime`\n" +
                "FROM `user`,`project`,`station`,`workorder_process`,`workorder`,`project_implementation`,`project_work`,`project_expenses_apply`,employeeContribution \n" +
                "WHERE user.`id`=employeeContribution.`user_id`\n" +
                "AND project.`id`=employeeContribution.`project_id`\n" +
                "AND station.`id`=employeeContribution.`station_id`\n" +
                "AND workorder_process.`id`=employeeContribution.`workProcess_id`\n" +
                "AND workorder.`id`=employeeContribution.`workoders_id` \n" +
                "AND project_implementation.`id`=employeeContribution.`projImpl_id`\n" +
                "AND project_work.`id`=employeeContribution.`projWork_id`\n" +
                "AND project_expenses_apply.`id`=employeeContribution.`projApply_id`\n" +
                "AND employeecontribution.`id`=?";
        //System.out.println(sql);
        Object []params = {ec.getId()};
        List<EmployeeContribution> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<>(EmployeeContribution.class),params);
            if (list.size()>0 && list!=null){
                return (EmployeeContribution) list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //按项目名称查询
    @Override
    public List<Project> selectProName(Project project) {
        String sql="SELECT `project_name` FROM `project`";
        System.out.println(sql);
        List<Project> list =null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<>(Project.class));
            if (list.size()>0 && list!=null){
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //按照岗位名称查询
    @Override
    public List<Station> selectStatName(Station station) {
        String sql = "SELECT station_name FROM station";
        System.out.println(sql);
        List<Station> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<>(Station.class));
            if (list.size()>0 && list!=null){
                return  list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //按员工号查询
    @Override
    public List<User> selectEmploNum(User user) {
        String sql = "SELECT employee_number FROM USER";
        System.out.println(sql);
        List<User> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<>(User.class));
            if (list.size()>0 && list!=null){
                return  list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //根据项目名称查询工单接受数量
    @Override
    public int queryPorjName(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM project,workorder\n" +
                "WHERE project.`project_name`=?\n" +
                "AND project.`id`=`workorder`.`project_id`\n" +
                "AND `workorder`.`status`=1";
        Object []params = {ec.getProject_name()};
        System.out.println(ec.getProject_name());
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //根据岗位名称查询工单接受数量
    @Override
    public int queryStationName(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM station,workorder\n" +
                "WHERE station.`station_name`=?\n" +
                "AND `station`.`id`=`workorder`.`station_id`\n" +
                "AND `workorder`.`status`=1";
        Object []params = {ec.getStation_name()};
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //根据员工编号查询工单接受数量
    @Override
    public int queryEmplNum(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM USER,workorder\n" +
                "WHERE user.`employee_number`=?'\n" +
                "AND user.`id`=`workorder`.`user_id`\n" +
                "AND `workorder`.`status`=1";
        Object []params = {ec.getStation_name()};
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
}
    //根据项目名称、岗位名称、员工编号、时间查询接受工单数量
    @Override
    public int queryCount(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM `project_expenses_apply`,workorder,project,station,USER\n" +
                "WHERE `project_expenses_apply`.`id`=`workorder`.`user_id`\n" +
                "AND user.`id`=`workorder`.`user_id`\n" +
                "AND `station`.`id`=`workorder`.`station_id`\n" +
                "AND project.`id`=`workorder`.`project_id`\n" +
                "AND user.`employee_number`=?\n" +
                "AND station.`station_name`=?\n" +
                "AND project.`project_name`=?\n" +
                "AND `project_expenses_apply`.`createtime`=?\n" +
                "AND `workorder`.`status`=1";
        Object []params = {ec.getEmployee_number(),ec.getProject_name(),ec.getStation_name(),ec.getCreatetime()};
        System.out.println("u=="+ec.getEmployee_number());
        System.out.println("p=="+ec.getProject_name());
        System.out.println("s=="+ec.getStation_name());
        System.out.println("s=="+ec.getCreatetime());
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //根据项目名称查询工单解决数量
    @Override
    public int queryPorjNamea(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM project,workorder\n" +
                "WHERE project.`id`=`workorder`.`project_id`\n" +
                "AND project.`project_name`=?\n" +
                "AND `workorder`.`status`=2";
        Object []params = {ec.getProject_name()};
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //根据岗位名称查询工单解决数量
    @Override
    public int queryStationNamae(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM station,workorder\n" +
                "WHERE `station`.`id`=`workorder`.`station_id`\n" +
                "AND station.`station_name`=?'\n" +
                "AND `workorder`.`status`=2";
        Object []params = {ec.getStation_name()};
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //根据员工编号查询工单解决数量
    @Override
    public int queryEmplNuma(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM USER,workorder\n" +
                "WHERE user.`id`=`workorder`.`user_id`\n" +
                "AND user.`employee_number`=?\n" +
                "AND `workorder`.`status`=2";
        Object []params = {ec.getEmployee_number()};
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //根据项目名称、岗位名称、员工编号、时间查询解决工单数量
    @Override
    public int queryCounta(EmployeeContribution ec) {
        String sql = "SELECT COUNT(`workorder`.`id`)\n" +
                "FROM `project_expenses_apply`,workorder,project,station,USER\n" +
                "WHERE `project_expenses_apply`.`id`=`workorder`.`user_id`\n" +
                "AND user.`id`=`workorder`.`user_id`\n" +
                "AND `station`.`id`=`workorder`.`station_id`\n" +
                "AND project.`id`=`workorder`.`project_id`\n" +
                "AND user.`employee_number`=?\n" +
                "AND station.`station_name`=?\n" +
                "AND project.`project_name`=?\n" +
                "AND `project_expenses_apply`.`createtime`=?\n" +
                "AND `workorder`.`status`=2";
        Object []params = {ec.getEmployee_number(),ec.getStation_name(),ec.getStation_name(),ec.getCreatetime()};
        System.out.println("u=="+ec.getEmployee_number());
        System.out.println("p=="+ec.getProject_name());
        System.out.println("s=="+ec.getStation_name());
        System.out.println("s=="+ec.getCreatetime());
        System.out.println(sql);
        Number num;
        try {
            num = (Number) queryRunner.query(sql, new ScalarHandler<>(), params);
            System.out.println("count" + num);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据失败!");
        }
    }
    //查询所有
    @Override
    public List<EmployeeContribution> queryAll(EmployeeContribution ec, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer("SELECT `employeecontribution`.`id`,user.`employee_number`,project.`project_name`,station.`station_name`,`workorder_process`.`workorder_id`,workorder.`number`,`project_expenses_apply`.`createtime`,`project_implementation`.`cycle` ,`project_work`.`actual_working_hours`\n" +
                "FROM `user`,`project`,`station`,`workorder_process`,`workorder`,`project_implementation`,`project_work`,`project_expenses_apply`,employeeContribution \n" +
                "WHERE user.`id`=employeeContribution.`user_id`\n" +
                "AND project.`id`=employeeContribution.`project_id`\n" +
                "AND station.`id`=employeeContribution.`station_id`\n" +
                "AND workorder_process.`id`=employeeContribution.`workProcess_id`\n" +
                "AND workorder.`id`=employeeContribution.`workoders_id` \n" +
                "AND project_implementation.`id`=employeeContribution.`projImpl_id`\n" +
                "AND project_work.`id`=employeeContribution.`projWork_id`\n" +
                "AND project_expenses_apply.`id`=employeeContribution.`projApply_id`");
        if (ec!=null){
            if(!"".equals(ec.getProject_name()) && ec.getProject_name()!=null) {
                sql.append(" AND project.project_name LIKE '%"+ec.getProject_name()+"%'");
            }
            if(!"".equals(ec.getStation_name()) && ec.getStation_name()!=null) {
                sql.append(" AND station.station_name LIKE '%"+ec.getStation_name()+"'");
            }
            if(!"".equals(ec.getEmployee_number()) && ec.getEmployee_number()!=null) {
                sql.append(" AND user.employee_number = '"+ec.getEmployee_number()+"'");
            }
        }
        if(beginTime != null && !"".equals(beginTime)){
            sql.append(" AND project_expenses_apply.createtime > '"+beginTime+"'");
        }
        if(endTime != null && !"".equals(endTime)){
            sql.append(" AND project_expenses_apply.createtime < '"+endTime+"'");
        }
        //System.out.println("sql-->"+sql);
        List<EmployeeContribution> list = null;
        try {
            list = queryRunner.query(String.valueOf(sql),new BeanListHandler<>(EmployeeContribution.class));
            if (list!=null && list.size()>0){
                System.out.println("没分页"+list);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //带分页的查询
    @Override
    public List<EmployeeContribution> queryAlls(EmployeeContribution ec,String beginTime,String endTime, PageBean pageBean) {
        StringBuffer sql = new StringBuffer("SELECT `employeecontribution`.`id`,user.`employee_number`,project.`project_name`,station.`station_name`,`workorder_process`.`workorder_id`,workorder.`number`,`project_expenses_apply`.`createtime`,`project_implementation`.`cycle` ,`project_work`.`actual_working_hours`\n" +
                "FROM `user`,`project`,`station`,`workorder_process`,`workorder`,`project_implementation`,`project_work`,`project_expenses_apply`,employeeContribution \n" +
                "WHERE user.`id`=employeeContribution.`user_id`\n" +
                "AND project.`id`=employeeContribution.`project_id`\n" +
                "AND station.`id`=employeeContribution.`station_id`\n" +
                "AND workorder_process.`id`=employeeContribution.`workProcess_id`\n" +
                "AND workorder.`id`=employeeContribution.`workoders_id` \n" +
                "AND project_implementation.`id`=employeeContribution.`projImpl_id`\n" +
                "AND project_work.`id`=employeeContribution.`projWork_id`\n" +
                "AND project_expenses_apply.`id`=employeeContribution.`projApply_id`\n"+"and 1=1");
        System.out.println("ec---->"+ec);
        if (ec!=null){
            if(!"".equals(ec.getProject_name()) && ec.getProject_name()!=null) {
                sql.append(" AND project.project_name LIKE '%"+ec.getProject_name()+"%'");
            }
            if(!"".equals(ec.getStation_name()) && ec.getStation_name()!=null) {
                sql.append(" AND station.station_name LIKE '%"+ec.getStation_name()+"'");
            }
            if(!"".equals(ec.getEmployee_number()) && ec.getEmployee_number()!=null) {
                sql.append(" AND user.employee_number = '"+ec.getEmployee_number()+"'");
            }
//            if(!"".equals(ec.getCreatetime()) && ec.getCreatetime()!=null) {
//                sql.append(" AND createtime  = '"+ec.getCreatetime()+"'");
//            }
        }
        if(beginTime != null && !"".equals(beginTime)){
            sql.append(" AND project_expenses_apply.createtime > '"+beginTime+"'");

        }
        if(endTime != null && !"".equals(endTime)){
            sql.append(" AND project_expenses_apply.createtime < '"+endTime+"'");
        }
        if (pageBean!=null){
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }
        System.out.println("sql-->"+sql);
        List<EmployeeContribution> list = null;
        try {
            list = queryRunner.query(String.valueOf(sql),new BeanListHandler<>(EmployeeContribution.class));
            if (list.size()>0 && list!=null){
                System.out.println("已分页"+list);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
