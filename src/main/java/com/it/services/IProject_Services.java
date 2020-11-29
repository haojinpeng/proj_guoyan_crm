package com.it.services;

import com.it.bean.Project;

import java.util.List;

public interface IProject_Services {
    //查询项目下拉框
    List<Project> queryProject_ProjectId(Project project);
}
