package com.it.services;

import com.it.bean.User;

import java.util.List;

public interface IUser_Services {
    //查询上级审批人
    public List<User> queryParentUser(String loginname);
    //查询当前登录人的姓名，展示给申请添加中
    public List<User> queryLoginUsername(String loginname);
    //通过登录名查找申请人id
    public User queryApply_idByLoginname(String loginname);
}
