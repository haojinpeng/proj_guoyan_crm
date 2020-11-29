package com.it.services.impl;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.dao.Project1DAO;
import com.it.dao.Project1DAOImpl;

import com.it.services.ProjectService;
import com.it.services.ProjectServices;

import java.util.List;

public class ProjectServicesImpl implements ProjectService {
    Project1DAO projectDAO = new Project1DAOImpl();
    @Override
    public boolean add(Project project) {
        return projectDAO.add(project);
    }

    @Override
    public boolean delete(Project project) {
        return projectDAO.delete(project);
    }

    @Override
    public Project queryAllById(Project project) {
        return projectDAO.queryAllById(project);
    }

    @Override
    public boolean update(Project project) {
        return projectDAO.update(project);
    }

    @Override
    public List<Project> selectProjName(Project project) {
        return projectDAO.selectProjName(project);
    }

    @Override
    public List<Project> queryAll(Project project) {
        return projectDAO.queryAll(project);
    }

    @Override
    public List<Project> queryAllPage(Project project, PageBean pageBean) {
        return projectDAO.queryAllPage(project,pageBean);
    }
}
