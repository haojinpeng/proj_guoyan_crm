package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Wvo;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class WorkorderDAOImpl implements WorkorderDAO {

    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Wvo> queryall(String str) {

        System.out.println("进入sql");
        //��дsql
        StringBuffer sql = new StringBuffer("\n" +
                "SELECT `workorder`.*,`user`.`username`,`customer`.`customer_name`,`project`.`project_name`\n" +
                "FROM `workorder`,`user`,`customer`,`project`\n" +
                "WHERE `workorder`.`customer_id`=`customer`.`id`\n" +
                "AND `workorder`.`project_id`=`project`.`id`\n" +
                "AND `workorder`.`recive_uer`=`user`.`id`  and `workorder`.`number`='"+str+"' and 1=1");



        System.out.println("sql-->"+sql);
        //
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql.toString(), new BeanListHandler<>(Wvo.class));


            System.out.println(listW);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }

    @Override
    public List<Wvo> queryall(PageBean pageBean, Wvo wvo) {
        System.out.println("进入sql");
        StringBuffer sql = new StringBuffer("SELECT `workorder_process`.`id`,`workorder`.`number`,`workorder`.`recive_uer`,`user`.`username`,`workorder_process`.`problem_solve`,`workorder_process`.`remarks`,`workorder_process`.`status`\n" +
                "FROM `workorder`,`workorder_process`,`user`\n" +
                "WHERE `workorder_process`.`workorder_id`=`workorder`.`id`\n" +
                "AND `workorder_process`.`handle_user`=`user`.`id`  and 1=1     ");

        if(!"".equals(wvo.getNumber()) && wvo.getNumber()!=null){
            sql.append("    AND `workorder`.`number` LIKE '%"+ wvo.getNumber()+"%'       ");
        }

        if(!"".equals(wvo.getStatus()) && wvo.getStatus()!=0){

            sql.append("    AND `workorder_process`.`status`="+wvo.getStatus());
        }


        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql.toString(), new BeanListHandler<>(Wvo.class));



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }


    @Override
    public List<Wvo> queryalllike(PageBean pageBean, Wvo wvo,String startTime,String endTime) {

        StringBuffer sql = new StringBuffer("\n" +
                "SELECT `workorder`.*,`user`.`username`,`customer`.`customer_name`,`project`.`project_name`\n" +
                "FROM `workorder`,`user`,`customer`,`project`\n" +
                "WHERE `workorder`.`customer_id`=`customer`.`id`\n" +
                "AND `workorder`.`project_id`=`project`.`id`\n" +
                "AND `workorder`.`recive_uer`=`user`.`id` and 1=1      ");

        if(!"".equals(wvo.getProject_name()) && wvo.getProject_name()!=null){
            sql.append("        AND  `project`.`project_name` like '%"+ wvo.getProject_name()+"%'");
        }

        if(!"".equals(wvo.getStatus()) && wvo.getStatus()!=0){

            sql.append("    AND `workorder`.`status`="+wvo.getStatus());
        }

        if(!"".equals(startTime) && !"".equals(endTime) && startTime!=null && endTime !=null){

            sql.append("    AND `workorder`.`receive_time`>"+startTime+" and  `workorder`.`require_time`<"+endTime);
        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql.toString(), new BeanListHandler<>(Wvo.class));


            System.out.println(listW);
            System.out.println("打印st:--->");
            for (Wvo st:listW
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }

    @Override
    public List<Wvo> queryallpj(PageBean pageBean, Wvo wvo, String startTime, String endTime) {
        StringBuffer sql = new StringBuffer("SELECT `workorder_visit`.`id`,`workorder_visit`.`evaluate`,`workorder_visit`.`suggest`,`workorder_visit`.`visit_time`,\n" +
                "`workorder`.`problem_description`,`workorder_visit`.`remark`,`workorder`.`number`,`user`.`username`,`project`.`project_name`,\n" +
                "`customer`.`customer_name`\n" +
                "FROM `project`,`user`,`workorder`,`workorder_visit`,`customer`\n" +
                "WHERE `workorder_visit`.`order_id`=`workorder`.`id`\n" +
                "AND `workorder_visit`.`create_user`=`user`.`id`\n" +
                "AND `workorder`.`project_id`=`project`.`id`\n" +
                "AND `workorder`.`customer_id`=`customer`.`id`\n" +
                "AND 1=1    ");

        if(!"".equals(wvo.getCustomer_name()) && wvo.getCustomer_name()!=null){
            sql.append("        AND `customer`.`customer_name` like '%"+ wvo.getCustomer_name()+"%'");
        }



        if(!"".equals(startTime) && !"".equals(endTime) && startTime!=null && endTime !=null){

            sql.append("    AND `workorder_visit`.`visit_time` BETWEEN '"+startTime+"' and  '"+endTime+"'");
        }

        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + ","
                    + pageBean.getRows());
        }

        System.out.println("sql-->"+sql);
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql.toString(), new BeanListHandler<>(Wvo.class));


            System.out.println(listW);
            System.out.println("打印st:--->");
            for (Wvo st:listW
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }

    @Override
    public List<Wvo> findprojname() {
        String sql = "SELECT `project`.`id`,`project`.`project_name` FROM `project`";
        System.out.println("sql-->"+sql);
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql, new BeanListHandler<>(Wvo.class));




        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }



    @Override
    public List<Wvo> findusertname() {
        String sql = "SELECT * FROM `user`  ";
        System.out.println("sql-->"+sql);
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql, new BeanListHandler<>(Wvo.class));


            System.out.println(listW);
            System.out.println("打印st:--->");
            for (Wvo st:listW
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }

    @Override
    public List<Wvo> findcustomername() {
        String sql = "SELECT * FROM `customer`  ";
        System.out.println("sql-->"+sql);
        List<Wvo> listW = null;
        try {

            listW = queryRunner.query(sql.toString(), new BeanListHandler<>(Wvo.class));


            System.out.println(listW);
            System.out.println("打印st:--->");
            for (Wvo st:listW
            ) {
                System.out.println(st);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("操作失败!");
        }


        return listW;
    }


    @Override
    public boolean add(Wvo wvo) {
        //编写sql
        //生成随机编号
        double i = Math.random();
        String numbers = "G"+String.valueOf(i).substring(2,8);
        //1.编写sql语句
        String sql = "INSERT INTO `crm`.`workorder`(`number`,`recive_uer`,`customer_id`,`project_id`,`receive_time`,`require_time`,`status`,`problem_description`)\n" +
                "VALUES ('"+numbers+"',(SELECT `user`.`id` FROM `user` WHERE `user`.`username`='"+wvo.getUsername()+"' limit 1),(SELECT `customer`.`id` FROM `customer` WHERE `customer`.`customer_name`='"+wvo.getCustomer_name()+"' limit 1),(SELECT `project`.`id` FROM `project` WHERE `project`.`project_name`='"+wvo.getProject_name()+"' limit 1),'"+wvo.getReceive_time()+"','"+wvo.getRequire_time()+"', '"+wvo.getStatus()+"','"+wvo.getProblem_description()+"');";

        //3.处理占位符
       // Object params[] = {,,,,,,};

        // 2.打印sql语句
        System.out.println("sql--->" + sql);

        try {
            int row = queryRunner.update(sql);
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
    public boolean update(Wvo wvo) {
        //编写sql

        //1.编写sql语句
        String sql = "UPDATE `crm`.`workorder_process`\n" +
                "SET `workorder_process`.`handle_user` = (SELECT `user`.`id` FROM `user` WHERE `user`.`username`='"+wvo.getUsername()+"'),`workorder_process`.`status` = '"+wvo.getStatus()+"'\n" +
                "WHERE `workorder_process`.`id`= (SELECT `workorder`.`id` FROM `workorder` WHERE `workorder`.`number`='"+wvo.getNumber()+"');";

        //3.处理占位符
        // Object params[] = {,,,,,,};

        // 2.打印sql语句
        System.out.println("sql--->" + sql);

        try {
            int row = queryRunner.update(sql);
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
    public boolean updatepj(Wvo wvo) {
        //1.编写sql语句
        String sql = "UPDATE `crm`.`workorder_visit`\n" +
                "SET `evaluate` = '"+wvo.getEvaluate()+"',`suggest` = '"+wvo.getSuggest()+"'\n" +
                "WHERE `order_id` = (SELECT `workorder`.`id` FROM `workorder` WHERE `workorder`.`number` ='"+wvo.getNumber()+"' )";

        //3.处理占位符
        // Object params[] = {,,,,,,};

        // 2.打印sql语句
        System.out.println("sql--->" + sql);

        try {
            int row = queryRunner.update(sql);
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
    public int count(Wvo wvo) {
        StringBuffer sb = new StringBuffer("select count(*) from workorder where 1=1 ");

        Number num;
        try {
            num = (Number)queryRunner.query(sb.toString(),new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("��ѯ����ʧ��!");
        }
    }
    @Override
    public int count2(Wvo wvo) {
        StringBuffer sb = new StringBuffer("select count(*) from `workorder_process` where 1=1 ");

        Number num;
        try {
            num = (Number)queryRunner.query(sb.toString(),new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("��ѯ����ʧ��!");
        }
    }

    @Override
    public int count3(Wvo wvo) {
        StringBuffer sb = new StringBuffer("select count(*) from `workorder_visit` where 1=1 ");

        Number num;
        try {
            num = (Number)queryRunner.query(sb.toString(),new ScalarHandler<>());

            return num.intValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("��ѯ����ʧ��!");
        }
    }

}
