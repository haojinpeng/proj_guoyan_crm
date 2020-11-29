package com.it.services;

import com.it.bean.Jurisdiction;
import com.it.bean.PageBean;

import java.util.List;

public interface IJurisdictionServices {
    //增
    public int add(Jurisdiction jurisdiction);

    //删除多条记录
    public int del(String delIds);

    //删除resources_jurisdiction
    public int delRes_jur(String delIds);

    //改
    public int upd(Jurisdiction jurisdiction);

    //带分页查询所有
    public List<Jurisdiction> queryAll(PageBean pageBean, Jurisdiction jurisdiction);

    //查单条
    public Jurisdiction select(Jurisdiction jurisdiction);

    //带分页查询所有的记录数
    public int count(Jurisdiction jurisdiction);
}
