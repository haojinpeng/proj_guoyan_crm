package com.it.dao;

import com.it.bean.BusinessOpporitunity;
import com.it.bean.Business_tracking;
import com.it.bean.Business_type;

import java.util.List;

public interface IBTrackingDao {
    //添加商机
    boolean add(Business_tracking tracking);
    boolean del(Business_tracking tracking);
    boolean upd(Business_tracking tracking);
    List<Business_tracking> queryAllLike(String sql);
    int count(String sql);
    List<Business_type> queryType();
    int dels(String delIds);
}
