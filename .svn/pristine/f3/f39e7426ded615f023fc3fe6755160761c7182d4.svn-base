package com.it.services;

import com.it.bean.BusinessOpporitunity;
import com.it.bean.Business_User;
import com.it.bean.Business_contact;
import com.it.bean.Business_type;
import com.it.dao.IBusinessOppoDao;
import com.it.dao.IBusinessOppoDaoImpl;
import com.it.services.IBusinessOppoServices;

import java.util.List;

public class IBusinessOppoServiceImpl implements IBusinessOppoServices {
    private IBusinessOppoDao businessOppoDao  = new IBusinessOppoDaoImpl();

    @Override
    public boolean add(BusinessOpporitunity businessOpporitunity) {

        

        return businessOppoDao.add(businessOpporitunity);
    }

    @Override
    public boolean del(BusinessOpporitunity businessOpporitunity) {
        return businessOppoDao.del(businessOpporitunity);
    }

    @Override
    public boolean upd(BusinessOpporitunity businessOpporitunity) {
        return businessOppoDao.upd(businessOpporitunity);
    }

    @Override
    public List<BusinessOpporitunity> queryAllLike(String sql) {
        return businessOppoDao.queryAllLike(sql);
    }

    @Override
    public int count(String sql) {
        return businessOppoDao.count(sql);
    }

    @Override
    public List<Business_type> queryType() {
        return businessOppoDao.queryType();
    }
    @Override
    public List<Business_contact> queryContact() {
        return businessOppoDao.queryContact();
    }

    @Override
    public int dels(String delIds) {
        return businessOppoDao.dels(delIds);
    }

    @Override
    public List<BusinessOpporitunity> query() {
        return businessOppoDao.query();
    }

    @Override
    public List<Business_User> queryUser() {
        return businessOppoDao.queryUser();
    }

    @Override
    public List<Business_type> queryFid() {
        return businessOppoDao.queryFid();
    }

}