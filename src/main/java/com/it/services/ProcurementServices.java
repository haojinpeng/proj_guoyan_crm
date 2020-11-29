package com.it.services;

import com.it.bean.Company;
import com.it.bean.PageBean;
import com.it.bean.Procurement;

import java.util.List;

public interface ProcurementServices {
    // 添加
    boolean addProcurement(Procurement procurement);

    // 修改
    boolean updateProcurement(Procurement procurement);

    // 删除账目
    boolean deleteProcurement(Procurement procurement);

    //带分页查询所有
    public List<Procurement> queryAllProcurement(PageBean pageBean, Procurement procurement);
    // 统计数据
    public int count();
}
