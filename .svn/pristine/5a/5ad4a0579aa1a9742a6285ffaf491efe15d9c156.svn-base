package com.it.services;

import com.it.bean.Project;
import com.it.dao.IProject_DAO;
import com.it.dao.Project_DAOImpl;
import com.it.services.IProject_Services;

import java.util.List;

public class Project_ServicesImpl implements IProject_Services {
    IProject_DAO iProject_dao = new Project_DAOImpl();
    @Override
    public List<Project> queryProject_ProjectId(Project project) {
        return iProject_dao.queryProject_ProjectId(project);
    }
}
