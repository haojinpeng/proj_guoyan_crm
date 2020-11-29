package com.it.services;

import com.it.bean.Business_tracking;
import com.it.bean.Business_type;
import com.it.dao.IBTrackingDao;
import com.it.dao.IBTrackingDaoImpl;

import java.util.List;

public class IBTrackingServicesImpl implements IBTrackingServices {
    private IBTrackingDao trackingDao = new IBTrackingDaoImpl();
    @Override
    public boolean add(Business_tracking tracking) {
        return trackingDao.add(tracking);
    }

    @Override
    public boolean del(Business_tracking tracking) {
        return trackingDao.del(tracking);
    }

    @Override
    public boolean upd(Business_tracking tracking) {
        return trackingDao.upd(tracking);
    }

    @Override
    public List<Business_tracking> queryAllLike(String sql) {
        return trackingDao.queryAllLike(sql);
    }

    @Override
    public int count(String sql) {
        return trackingDao.count(sql);
    }

    @Override
    public List<Business_type> queryType() {
        return trackingDao.queryType();
    }

    @Override
    public int dels(String delIds) {
        return trackingDao.dels(delIds);
    }
}
