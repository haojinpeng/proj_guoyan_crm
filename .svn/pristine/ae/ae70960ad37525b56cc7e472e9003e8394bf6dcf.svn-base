package com.it.services.impl;

import com.it.bean.*;
import com.it.dao.EmployeeContrDAO;
import com.it.dao.EmployeeContrDAOImpl;
import com.it.services.EmployeeContrService;

import java.util.List;

public class EmployeeContrServiceImpl implements EmployeeContrService {
    EmployeeContrDAO employeeContrDAO = new EmployeeContrDAOImpl();

    @Override
    public EmployeeContribution selectById(EmployeeContribution ec) {
        return employeeContrDAO.selectById(ec);
    }


    @Override
    public List<Project> selectProName(Project project) {
        return employeeContrDAO.selectProName(project);
    }
    @Override
    public List<Station> selectStatName(Station station) {
        return employeeContrDAO.selectStatName(station);
    }

    @Override
    public List<User> selectEmploNum(User user) {
        return employeeContrDAO.selectEmploNum(user);
    }

    @Override
    public int queryPorjName(EmployeeContribution ec) {
        return employeeContrDAO.queryPorjName(ec);
    }

    @Override
    public int queryStationName(EmployeeContribution ec) {
        return employeeContrDAO.queryStationName(ec);
    }

    @Override
    public int queryEmplNum(EmployeeContribution ec) {
        return employeeContrDAO.queryEmplNum(ec);
    }

    @Override
    public int queryCount(EmployeeContribution ec) {
        return employeeContrDAO.queryCount(ec);
    }

    @Override
    public int queryPorjNamea(EmployeeContribution ec) {
        return employeeContrDAO.queryPorjName(ec);
    }

    @Override
    public int queryStationNamae(EmployeeContribution ec) {
        return employeeContrDAO.queryStationNamae(ec);
    }

    @Override
    public int queryEmplNuma(EmployeeContribution ec) {
        return employeeContrDAO.queryEmplNuma(ec);
    }

    @Override
    public int queryCounta(EmployeeContribution ec) {
        return employeeContrDAO.queryCounta(ec);
    }

    @Override
    public List<EmployeeContribution> queryAll(EmployeeContribution ec, String beginTime, String endTime) {
       return employeeContrDAO.queryAll(ec,beginTime,endTime);
    }

    @Override
    public List<EmployeeContribution> queryAlls(EmployeeContribution ec,String beginTme,String endTime,PageBean pageBean) {
        return employeeContrDAO.queryAlls(ec,beginTme,endTime,pageBean);
    }
}
