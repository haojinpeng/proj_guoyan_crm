package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.dao.ProjectDao;
import com.it.dao.ProjectDaoImpl;
import com.it.services.ProjectServices;

import java.util.List;

public class ProjectServicesImpl implements ProjectServices {
    private ProjectDao projectDao = new ProjectDaoImpl();

    @Override
    public boolean addProject(Project project) {
        return projectDao.addProject(project);
    }

    @Override
    public boolean updateProject(Project project) {
        return projectDao.updateProject(project);
    }

    @Override
    public boolean deleteProject(Project project) {
        return projectDao.deleteProject(project);
    }

    @Override
    public List<Project> queryAllProject(PageBean pageBean, Project project, String startTime, String endTime) {
        return projectDao.queryAllProject(pageBean,project,startTime,endTime);
}
    @Override
    public int count() {
        // TODO Auto-generated method stub
        return projectDao.count();
    }


}
