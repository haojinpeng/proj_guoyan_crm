package com.it.services.impl;

import com.it.bean.Jurisdiction;
import com.it.bean.PageBean;
import com.it.dao.JurisdictionDAO;
import com.it.dao.JurisdictionDAOImpl;
import com.it.services.IJurisdictionServices;
//import com.it.service.IJurisdictionServices;

import java.util.List;

public class JurisdictionServicesImpl implements IJurisdictionServices {
    JurisdictionDAO jurisdictionDAO=new JurisdictionDAOImpl();

    public JurisdictionServicesImpl() {
        System.out.println("servicesImpl======");
    }

    @Override
    public int add(Jurisdiction jurisdiction) {
        return jurisdictionDAO.add(jurisdiction);
    }

    @Override
    public int del(String delIds) {
        return jurisdictionDAO.del(delIds);
    }

    @Override
    public int delRes_jur(String delIds) {
        return jurisdictionDAO.delRes_jur(delIds);
    }

    @Override
    public int upd(Jurisdiction jurisdiction) {
        return jurisdictionDAO.upd(jurisdiction);
    }

    @Override
    public List<Jurisdiction> queryAll(PageBean pageBean, Jurisdiction jurisdiction) {
        return jurisdictionDAO.queryAll(pageBean,jurisdiction);
    }

    @Override
    public Jurisdiction select(Jurisdiction jurisdiction) {
        return jurisdictionDAO.select(jurisdiction);
    }

    @Override
    public int count(Jurisdiction jurisdiction) {
        return jurisdictionDAO.count(jurisdiction);
    }
}
