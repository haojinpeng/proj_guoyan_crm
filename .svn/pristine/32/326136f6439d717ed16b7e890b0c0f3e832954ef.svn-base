package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.Wvo;
import com.it.dao.WorkorderDAO;
import com.it.dao.WorkorderDAOImpl;

import java.util.List;

public class WorkorderServicesImpl implements WorkorderServices {

    WorkorderDAO workorderDAO = new WorkorderDAOImpl();
    @Override
    public List<Wvo> queryall(String str) {
        return workorderDAO.queryall(str);
    }



    @Override
    public List<Wvo> queryall(PageBean pageBean, Wvo wvo) {
        return workorderDAO.queryall(pageBean,wvo);
    }

    @Override
    public List<Wvo> queryalllike(PageBean pageBean, Wvo wvo,String startTime,String endTime) {
        return workorderDAO.queryalllike(pageBean,wvo,startTime,endTime);
    }

    @Override
    public List<Wvo> queryallpj(PageBean pageBean, Wvo wvo, String startTime, String endTime) {
        return workorderDAO.queryallpj(pageBean,wvo,startTime,endTime);
    }

    @Override
    public List<Wvo> findprojname() {
        return workorderDAO.findprojname();
    }

    @Override
    public List<Wvo> findusertname() {
        return workorderDAO.findusertname();
    }

    @Override
    public List<Wvo> findcustomername() {
        return workorderDAO.findcustomername();
    }


    @Override
    public int count(Wvo wvo) {
        return workorderDAO.count(wvo);
    }

    @Override
    public boolean update(Wvo wvo) {
        return workorderDAO.update(wvo);
    }

    @Override
    public boolean updatepj(Wvo wvo) {
        return workorderDAO.updatepj(wvo);
    }

    @Override
    public boolean add(Wvo wvo) {
        return workorderDAO.add(wvo);
    }

    @Override
    public int count2(Wvo wvo) {
        return workorderDAO.count2(wvo);
    }

    @Override
    public int count3(Wvo wvo) {
        return workorderDAO.count3(wvo);
    }
}
