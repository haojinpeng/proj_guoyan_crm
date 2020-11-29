package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.Procurement;
import com.it.dao.ProcurementDao;
import com.it.dao.ProcurementDaoImpl;
import com.it.services.ProcurementServices;

import java.util.List;

public class ProcurementServicesImpl implements ProcurementServices {
private ProcurementDao procurementDao = new ProcurementDaoImpl();
    @Override
    public boolean addProcurement(Procurement procurement) {
        return procurementDao.addProcurement(procurement);
    }

    @Override
    public boolean updateProcurement(Procurement procurement) {
        return procurementDao.updateProcurement(procurement);
    }

    @Override
    public boolean deleteProcurement(Procurement procurement) {
        return procurementDao.deleteProcurement(procurement);
    }

    @Override
    public List<Procurement> queryAllProcurement(PageBean pageBean, Procurement procurement) {
        return procurementDao.queryAllProcurement(pageBean,procurement);
    }

    @Override
    public int count() {
        return procurementDao.count();
    }
}
