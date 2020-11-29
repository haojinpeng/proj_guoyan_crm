package com.it.services.impl;

import com.it.bean.PageBean;
import com.it.bean.Project_cost_statistics;
import com.it.dao.Project_cost_statisticsDAO;
import com.it.dao.Project_cost_statisticsDAOImpl;
import com.it.services.IProject_cost_statisticsServices;

import java.util.List;

public class Project_cost_statisticsServicesImpl implements IProject_cost_statisticsServices {
    Project_cost_statisticsDAO project_cost_statisticsDAO=new Project_cost_statisticsDAOImpl();

    @Override
    public List<Project_cost_statistics> queryAll(PageBean pageBean, Project_cost_statistics project_cost_statistics) {
        return project_cost_statisticsDAO.queryAll(pageBean,project_cost_statistics);
    }

    @Override
    public int count(Project_cost_statistics project_cost_statistics) {
        return project_cost_statisticsDAO.count(project_cost_statistics);
    }
}
