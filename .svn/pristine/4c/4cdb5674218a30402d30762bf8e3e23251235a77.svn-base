package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.ProjectVo;
import com.it.bean.Project_task;
import com.it.dao.Project_taskDao;
import com.it.dao.Project_taskDaoImpl;
import com.it.services.Project_taskServices;

import java.util.List;

public class Project_taskServicesImpl implements Project_taskServices {
    private Project_taskDao project_taskDao = new Project_taskDaoImpl();

    @Override
    public boolean addProjectVo(ProjectVo projectVo) {
        return project_taskDao.addProjectVo(projectVo);
    }

    @Override
    public boolean updateProject_task(Project_task project_task) {
        return project_taskDao.updateProject_task(project_task);
    }

    @Override
    public List<ProjectVo> queryAllProjectVo(PageBean pageBean, ProjectVo projectVo,String startTime, String endTime) {
        return project_taskDao.queryAllProjectVo(pageBean,projectVo,startTime,endTime);
    }
    @Override
    public int count() {
        // TODO Auto-generated method stub
        return project_taskDao.count();
    }
}
