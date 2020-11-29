package com.it.services;

import com.it.bean.User;
import com.it.dao.IUser_DAO;
import com.it.dao.User_DAOImpl;
import com.it.services.IUser_Services;

import java.util.List;

public class User_ServicesImpl implements IUser_Services {
    IUser_DAO iUser_dao = new User_DAOImpl();
    @Override
    public List<User> queryParentUser(String loginname) {
        return iUser_dao.queryParentUser(loginname);
    }
    @Override
    public List<User> queryLoginUsername(String loginname) {
        return iUser_dao.queryLoginUsername(loginname);
    }
    @Override
    public User queryApply_idByLoginname(String loginname) {
        return iUser_dao.queryApply_idByLoginname(loginname);
    }
}
