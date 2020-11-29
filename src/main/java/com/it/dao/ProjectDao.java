package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Project;

import java.util.List;

public interface ProjectDao {
    // 添加
    boolean addProject(Project project);

    // 修改
    boolean updateProject(Project project);

    // 删除账目
    boolean deleteProject(Project project);

    //带分页查询所有
    public List<Project> queryAllProject(PageBean pageBean, Project project,String startTime, String endTime);

    // 统计数据
    public int count();


}
