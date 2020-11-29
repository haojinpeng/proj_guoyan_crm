package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.ProjectVo;
import com.it.bean.Project_task;

import java.util.List;

public interface Project_taskServices {
    // 添加
    boolean addProjectVo(ProjectVo projectVo);

    // 修改
    boolean updateProject_task(Project_task project_task);

    //带分页查询所有
    public List<ProjectVo> queryAllProjectVo(PageBean pageBean, ProjectVo projectVo,String startTime, String endTime);
    // 统计数据
    public int count();
}
