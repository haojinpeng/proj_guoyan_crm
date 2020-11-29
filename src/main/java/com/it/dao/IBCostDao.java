package com.it.dao;

import com.it.bean.Business_cost;
import com.it.bean.Business_tracking;
import com.it.bean.Business_type;

import java.util.List;

public interface IBCostDao {
    //添加商机
    boolean add(Business_cost cost);
    boolean del(Business_cost cost);
    boolean upd(Business_cost cost);
    List<Business_cost> queryAllLike(String sql);
    int count(String sql);
    List<Business_cost> queryType();
    int dels(String delIds);
    boolean update(Business_cost cost);
}
