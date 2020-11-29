package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Workorder;

import java.util.List;

public interface IWorkorder_DAO {
    //查询不重复的项目名称
    List<Workorder> queryDistinctProject_name(Workorder workorder);
    //查询工单申请数量 一个条件
    int queryWorkorderCountByPro(Workorder workorder);
    //查询工单解决数量 一个条件
    int queryWorkorder_ProcessCountByPro(Workorder workorder);
    //查询工单回访数量 一个条件
    int queryWorkorder_VisitCountByPro(Workorder workorder);
    //查询工单申请数量 两个条件
    int queryWorkorderCount(Workorder workorder);
    //查询工单解决数量 两个条件
    int queryWorkorder_ProcessCount(Workorder workorder);
    //查询工单回访数量 两个条件
    int queryWorkorder_VisitCount(Workorder workorder);
    //带分页查询所有
    List<Workorder> queryAllWorkorderAndLimit(PageBean pageBean, Workorder workorder);
    //查询总记录数
    int queryAllWorkorderCount(Workorder workorder);
}
