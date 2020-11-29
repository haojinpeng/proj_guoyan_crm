package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;
import com.it.bean.Project_task;

import java.util.List;

public interface ProjectTaskDAO {
    //按项目名称查询
    List<Project> selectProjName(Project project);
    List<Project_task> queryAll(Project_task project_task);
    //根据项目名称查询项目任务名称，任务描述以及负责人
    List<Project_task> queryTask(Project_task project_task, PageBean pageBean);
}
