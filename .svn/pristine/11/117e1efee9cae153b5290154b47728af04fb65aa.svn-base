package com.it.dao;

import com.it.bean.BusinessOpporitunity;
import com.it.bean.Business_User;
import com.it.bean.Business_contact;
import com.it.bean.Business_type;

import java.util.List;

public interface IBusinessOppoDao {
    //添加商机
    boolean add(BusinessOpporitunity businessOpporitunity);
    boolean del(BusinessOpporitunity businessOpporitunity);
    boolean upd(BusinessOpporitunity businessOpporitunity);
    int dels(String idns);
    List<BusinessOpporitunity> queryAllLike(String sql);
    int count(String sql);
    List<Business_type> queryType();
    List<Business_contact> queryContact();
    List<BusinessOpporitunity> query();
    List<Business_User> queryUser();
    List<Business_type> queryFid();
}
