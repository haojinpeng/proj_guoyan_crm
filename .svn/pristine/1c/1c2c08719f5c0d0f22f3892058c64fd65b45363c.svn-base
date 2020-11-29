package com.it.services;

import com.it.bean.Approval_Process;
import com.it.bean.Business_Check;
import com.it.bean.Business_cost;
import com.it.bean.User;

import java.util.List;

public interface IBCheckServices {
    //添加商机
    boolean add(Business_Check check);
    boolean del(Business_Check check);
    boolean upd(Business_Check check, String login_name);
    List<Business_Check> queryAllLike(String sql);
    int count(String sql);
    List<Business_Check> queryType();
    List<Approval_Process> queryAllProcess();
    List<User> queryAllProcess(String loginname);
    boolean addCheck(Business_Check check);

}
