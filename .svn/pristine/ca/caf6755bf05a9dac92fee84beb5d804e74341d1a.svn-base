package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.ProjectApp;
import com.it.dao.ProjectAppDao;
import com.it.dao.ProjectAppDapImpl;
import com.it.services.ProjectAppServices;

import java.util.List;

public class ProjectAppServicesImpl implements ProjectAppServices {
private ProjectAppDao projectAppDao = new ProjectAppDapImpl();

    @Override
    public List<ProjectApp> queryAllProjectApp(PageBean pageBean, ProjectApp projectApp, String startTime, String endTime) {
        return projectAppDao.queryAllProjectApp(pageBean,projectApp,startTime,endTime);
    }

    @Override
    public int count() {
        return projectAppDao.count();
    }

    @Override
    public List<ProjectApp> selectProjectApp(ProjectApp projectApp) {
        return projectAppDao.selectProjectApp(projectApp);
    }
}
