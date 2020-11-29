package com.it.services;

import com.it.bean.Expenses_apply;
import com.it.bean.PageBean;
import com.it.dao.Expenses_apply_DAOImpl;
import com.it.dao.IExpenses_apply_DAO;
import com.it.services.IExpenses_apply_Services;

import java.util.List;

public class Expenses_apply_ServicesImpl implements IExpenses_apply_Services {
    IExpenses_apply_DAO iExpenses_apply_dao = new Expenses_apply_DAOImpl();
    @Override
    public boolean addExpenses_apply(Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.addExpenses_apply(expenses_apply);
    }

    @Override
    public boolean delExpenses_apply(Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.delExpenses_apply(expenses_apply);
    }

    @Override
    public boolean updExpenses_apply(Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.updExpenses_apply(expenses_apply);
    }

    @Override
    public boolean updExpenses_apply_Apply_status(Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.updExpenses_apply_Apply_status(expenses_apply);
    }

    @Override
    public List<Expenses_apply> queryAllExpenses_apply() {
        return null;
    }

    @Override
    public boolean submitExpenses_apply(Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.submitExpenses_apply(expenses_apply);
    }

    @Override
    public List<Expenses_apply> queryAllLikeExpenses_apply(Expenses_apply expenses_apply) {
        return null;
    }

    @Override
    public List<Expenses_apply> queryAllExpenses_applyAndLimit(PageBean pageBean, Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.queryAllExpenses_applyAndLimit(pageBean,expenses_apply);
    }

    @Override
    public int queryExpenses_applyCount(Expenses_apply expenses_apply) {
        return iExpenses_apply_dao.queryExpenses_applyCount(expenses_apply);
    }

}
