package com.it.services.impl;

import com.it.bean.Approval_type;
import com.it.bean.PageBean;
import com.it.dao.Approval_typeDAO;
import com.it.dao.Approval_typeDAOImpl;
import com.it.services.IApproval_typeServices;
//import com.it.service.IApproval_typeServices;

import java.util.List;

public class Approval_typeSerivcesImpl implements IApproval_typeServices {
    Approval_typeDAO approval_typeDAO = new Approval_typeDAOImpl();

    @Override
    public int add(Approval_type approval_type) {
        return approval_typeDAO.add(approval_type);
    }

    @Override
    public int del(String delIds) {
        return approval_typeDAO.del(delIds);
    }

    @Override
    public int upd(Approval_type approval_type) {
        return approval_typeDAO.upd(approval_type);
    }

    @Override
    public List<Approval_type> queryAll(PageBean pageBean, Approval_type approval_type) {
        return approval_typeDAO.queryAll(pageBean, approval_type);
    }

    @Override
    public Approval_type select(Approval_type approval_type) {
        return approval_typeDAO.select(approval_type);
    }

    @Override
    public int count(Approval_type approval_type) {
        return approval_typeDAO.count(approval_type);
    }
}
