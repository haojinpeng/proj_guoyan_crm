package com.it.services;

import com.it.bean.Expenses_approval;
import com.it.bean.PageBean;
import com.it.dao.Expenses_approval_DAOImpl;
import com.it.dao.IExpenses_approval_DAO;
import com.it.services.IExpenses_approval_Services;

import java.util.List;

public class Expenses_approval_ServicesImpl implements IExpenses_approval_Services {
    IExpenses_approval_DAO iExpenses_approval_dao = new Expenses_approval_DAOImpl();
    @Override
    public boolean addExpenses_approval(Expenses_approval expenses_approval) {
        return iExpenses_approval_dao.addExpenses_approval(expenses_approval);
    }

    @Override
    public boolean updExpenses_approval(Expenses_approval expenses_approval) {
        return iExpenses_approval_dao.updExpenses_approval(expenses_approval);
    }

    @Override
    public boolean updExpenses_apply_Status(Expenses_approval expenses_approval) {
        return iExpenses_approval_dao.updExpenses_apply_Status(expenses_approval);
    }

    @Override
    public int queryParent_id(Expenses_approval expenses_approval) {
        return iExpenses_approval_dao.queryParent_id(expenses_approval);
    }

    @Override
    public List<Expenses_approval> queryAllLikeExpenses_approval(Expenses_approval expenses_approval) {
        return null;
    }

    @Override
    public List<Expenses_approval> queryAllExpenses_approvalAndLimit(PageBean pageBean, Expenses_approval expenses_approval) {
        return iExpenses_approval_dao.queryAllExpenses_approvalAndLimit(pageBean,expenses_approval);
    }

    @Override
    public int queryExpenses_approvalCount(Expenses_approval expenses_approval) {
        return iExpenses_approval_dao.queryExpenses_approvalCount(expenses_approval);
    }
}
