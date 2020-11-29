package com.it.dao;

import com.it.bean.*;

import java.util.List;

public interface EmployeeContrDAO {

    //按员工编号查询
    EmployeeContribution selectById(EmployeeContribution ec);
    //按项目名称查询
    List<Project> selectProName(Project project);
    //按岗位查询
    List<Station> selectStatName(Station station);
    //按员工号查询
    List<User> selectEmploNum(User user);
    //根据项目名称查询工单接受数量
    int queryPorjName(EmployeeContribution ec);
    //根据岗位名称查询工单接受数量
    int queryStationName(EmployeeContribution ec);
    //根据员工编号查询工单接受数量
    int queryEmplNum(EmployeeContribution ec);
    //根据项目名称、岗位名称、员工编号、时间查询接受工单数量
    int queryCount(EmployeeContribution ec);
    //根据项目名称查询工单解决数量
    int queryPorjNamea(EmployeeContribution ec);
    //根据岗位名称查询工单解决数量
    int queryStationNamae(EmployeeContribution ec);
    //根据员工编号查询工单解决数量
    int queryEmplNuma(EmployeeContribution ec);
    //根据项目名称、岗位名称、员工编号、时间查询解决工单数量
    int queryCounta(EmployeeContribution ec);
    //查询所有
    List<EmployeeContribution> queryAll(EmployeeContribution ec, String beginTime, String endTime);
    //查询所有带分页
    List<EmployeeContribution> queryAlls(EmployeeContribution ec, String beginTime, String endTime, PageBean pageBean);
}
