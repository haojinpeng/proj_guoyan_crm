package com.it.dao;

import com.it.bean.Approval_type;
import com.it.bean.PageBean;

import java.util.List;

public interface Approval_typeDAO {

    //增
    public int add(Approval_type approval_type);

    //删除多条记录
    public int del(String delIds);

    //改
    public int upd(Approval_type approval_type);

    //带分页查询所有
    public List<Approval_type> queryAll(PageBean pageBean, Approval_type approval_type);

    //查单条
    public Approval_type select(Approval_type approval_type);

    //带分页查询所有的记录数
    public int count(Approval_type approval_type);
}
