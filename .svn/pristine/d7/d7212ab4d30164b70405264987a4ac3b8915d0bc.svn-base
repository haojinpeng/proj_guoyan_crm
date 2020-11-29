package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.User;
import com.it.bean.User_Role;

import java.util.List;

public interface IUdao {
    public User selectUserByLogin_Name(String login_name);
    //注册
    public boolean add(User user);
    //登陆
    public boolean logion(User user);
    //带分页查询所有用户和角色
    public List<User_Role> queryAllUsrole(PageBean pageBean, User_Role user_role);
//    //模糊查询
//    public List<User> mohu(User user);

//删除多条记录
public int Deletess(String delIds);
    //删
    public boolean del(User user);
    //查询所有
    public boolean updateb(User user);
    //修改密码
    public boolean upd(User user);
    //带分页查询所有
    public List<User> queryAllUser(PageBean pageBean, User user);
    //查询总记录数
    public int userCount(User user);
}
