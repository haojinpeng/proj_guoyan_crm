package com.it.services;

import com.it.bean.Approval_Process;
import com.it.bean.Business_Check;
import com.it.bean.User;
import com.it.dao.IBCheckDao;
import com.it.dao.IBCheckDaoImpl;

import java.util.List;

public class IBCheckServicesImpl implements IBCheckServices {
    private IBCheckDao checkDao = new IBCheckDaoImpl();
    @Override
    public boolean add(Business_Check check) {
        return checkDao.add(check);
    }

    @Override
    public boolean del(Business_Check check) {
        return checkDao.del(check);
    }

    @Override
    public boolean upd(Business_Check check,String login_name) {
        return checkDao.upd(check,login_name);
    }

    @Override
    public List<Business_Check> queryAllLike(String sql) {
        return checkDao.queryAllLike(sql);
    }

    @Override
    public int count(String sql) {
        return checkDao.count(sql);
    }

    @Override
    public List<Business_Check> queryType() {
        return null;
    }

    @Override
    public List<Approval_Process> queryAllProcess() {
        return checkDao.queryAllProcess();
    }

    @Override
    public List<User> queryAllProcess(String loginname) {
        return checkDao.queryParentUser(loginname);
    }

    @Override
    public boolean addCheck(Business_Check check) {
        return checkDao.addCheck(check);
    }

}
