package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.Workorder;
import com.it.dao.IWorkorder_DAO;
import com.it.dao.Workorder_DAOImpl;
import com.it.services.IWorkorder_Services;

import java.util.List;

public class Workorder_ServicesImpl implements IWorkorder_Services {
    IWorkorder_DAO iWorkorder_dao = new Workorder_DAOImpl();

    @Override
    public List<Workorder> queryDistinctProject_name(Workorder workorder) {
        return iWorkorder_dao.queryDistinctProject_name(workorder);
    }

    @Override
    public int queryWorkorderCountByPro(Workorder workorder) {
        return iWorkorder_dao.queryWorkorderCountByPro(workorder);
    }

    @Override
    public int queryWorkorder_ProcessCountByPro(Workorder workorder) {
        return iWorkorder_dao.queryWorkorder_ProcessCountByPro(workorder);
    }

    @Override
    public int queryWorkorder_VisitCountByPro(Workorder workorder) {
        return iWorkorder_dao.queryWorkorder_VisitCountByPro(workorder);
    }

    @Override
    public int queryWorkorderCount(Workorder workorder) {
        return iWorkorder_dao.queryWorkorderCount(workorder);
    }

    @Override
    public int queryWorkorder_ProcessCount(Workorder workorder) {
        return iWorkorder_dao.queryWorkorder_ProcessCount(workorder);
    }

    @Override
    public int queryWorkorder_VisitCount(Workorder workorder) {
        return iWorkorder_dao.queryWorkorder_VisitCount(workorder);
    }

    @Override
    public List<Workorder> queryAllWorkorderAndLimit(PageBean pageBean, Workorder workorder) {
        return iWorkorder_dao.queryAllWorkorderAndLimit(pageBean,workorder);
    }

    @Override
    public int queryAllWorkorderCount(Workorder workorder) {
        return iWorkorder_dao.queryAllWorkorderCount(workorder);
    }
}
