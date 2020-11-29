package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.ProjectApp;

import java.util.List;

public interface ProjectAppDao {

    //带分页查询所有
    public List<ProjectApp> queryAllProjectApp(PageBean pageBean, ProjectApp projectApp, String startTime, String endTime);

    // 统计数据
    public int count();


    //日报审批
    public List<ProjectApp> selectProjectApp(ProjectApp projectApp);
}
