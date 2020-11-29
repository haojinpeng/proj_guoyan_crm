package com.it.services.impl;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.Project_task;
import com.it.dao.ProjectTaskDAO;
import com.it.dao.ProjectTaskDAOImpl;
import com.it.services.ProjectTaskServices;

import java.util.List;

public class ProjectTaskServicesImpl implements ProjectTaskServices {
    ProjectTaskDAO project_taskDAO = new ProjectTaskDAOImpl();

    @Override
    public List<Project> selectProjName(Project project) {
        return project_taskDAO.selectProjName(project);
    }

    @Override
    public List<Project_task> queryAll(Project_task project_task) {
        return project_taskDAO.queryAll(project_task);
    }

    @Override
    public List<Project_task> queryTask(Project_task project_task, PageBean pageBean) {
        return project_taskDAO.queryTask(project_task,pageBean);
    }
}
