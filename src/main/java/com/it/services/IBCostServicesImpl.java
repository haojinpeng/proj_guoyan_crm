package com.it.services;

import com.it.bean.Business_cost;
import com.it.dao.IBCostDao;
import com.it.dao.IBCostDaoImpl;
import com.it.dao.IBTrackingDao;
import com.it.dao.IBTrackingDaoImpl;

import java.util.List;

public class IBCostServicesImpl implements IBCostServices {

    private IBCostDao costDao = new IBCostDaoImpl();

    @Override
    public boolean add(Business_cost cost) {
        return costDao.add(cost);
    }

    @Override
    public boolean del(Business_cost cost) {
        return costDao.del(cost);
    }

    @Override
    public boolean upd(Business_cost cost) {
        return costDao.upd(cost);
    }

    @Override
    public List<Business_cost> queryAllLike(String sql) {
        return costDao.queryAllLike(sql);
    }

    @Override
    public int count(String sql) {
        return costDao.count(sql);
    }

    @Override
    public List<Business_cost> queryType() {
        return null;
    }

    @Override
    public int dels(String delIds) {
        return costDao.dels(delIds);
    }

    @Override
    public boolean update(Business_cost cost) {
        return costDao.update(cost);
    }
}
