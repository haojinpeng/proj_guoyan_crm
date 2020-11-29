package com.it.dao;

import com.it.bean.WorkSpaces;
import com.it.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.lang.model.element.QualifiedNameable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IWorkspavesDaoImpl implements IWorkspavesDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<WorkSpaces> query() {
        String sql = "select status as id,count(status) as value from businessopporitunity GROUP BY `status`";
        try {
            List<WorkSpaces> workSpaces = queryRunner.query(sql,new BeanListHandler<>(WorkSpaces.class));

            List<WorkSpaces> workSpaces2 = new ArrayList<>();


            if(workSpaces!=null){
                for (int i = 0; i < workSpaces.size(); i++) {
                    WorkSpaces workSpaces1 = new WorkSpaces();
                    workSpaces1.setId(workSpaces.get(i).getId());
                    if(1 == workSpaces.get(i).getId()){
                        workSpaces1.setName("跟进中");
                    }else if(2 == workSpaces.get(i).getId()){
                        workSpaces1.setName("已报备");
                    }else if(3 == workSpaces.get(i).getId()){
                        workSpaces1.setName("已流失");
                    }
                    workSpaces1.setValue(workSpaces.get(i).getValue());
                    workSpaces2.add(workSpaces1);
                }


                System.out.println(workSpaces2);
                return workSpaces2;
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
    public List<WorkSpaces> queryContract() {
        String sql = "select count(*) from contract";
        try {
            List<WorkSpaces> workSpaces = queryRunner.query(sql,new BeanListHandler<>(WorkSpaces.class));

            if(workSpaces!=null){

                return workSpaces;
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
