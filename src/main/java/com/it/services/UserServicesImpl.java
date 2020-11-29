package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.User;
import com.it.bean.User_Role;
import com.it.dao.IUdao;
import com.it.dao.IUserImpl;

import java.util.List;


public class UserServicesImpl implements IUserServices {
   IUdao iUdao = new IUserImpl();

    @Override
    public User findUserByLogin_Name(String login_name) {
        return  iUdao.selectUserByLogin_Name(login_name);
    }

    @Override
    public void finUserAll() {

    }

    @Override
    public boolean add(User user) {
        return iUdao.add(user);
    }

    @Override
    public boolean logion(User user) {
        return iUdao.logion(user);
    }

    @Override
    public boolean del(User user) {
        return iUdao.del(user);
    }

    @Override
    public int Deletess(String delIds) {
        return iUdao.Deletess(delIds);
    }


    //修改
    @Override
    public boolean updateb(User user) {
        return iUdao.updateb(user);
    }

    @Override
    public boolean upd(User user) {
        return iUdao.upd(user);
    }

    @Override
    public List<User_Role> queryAllUsrole(PageBean pageBean, User_Role user_role) {
        return iUdao.queryAllUsrole(pageBean,user_role);
    }

    @Override
    public List<User> queryAllUser(PageBean pageBean, User user) {
        return iUdao.queryAllUser(pageBean, user);
    }

    @Override
    public int userCount(User user) {
        return iUdao.userCount(user);
    }
}
