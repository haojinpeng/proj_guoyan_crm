package main.java.com.it.services;

import com.it.bean.Contact;
import com.it.bean.PageBean;

import java.util.List;

public interface IContactServices {
    //注册
    public boolean add(Contact contact);
    //登陆
    public boolean logion(Contact contact);
    //删
    public boolean del(Contact contact);
    //删除多条记录
    public int Deletess(String delIds);
    //查询所有
    public boolean update(Contact contact);
    //带分页查询所有
    public List<Contact> queryAllCotact(PageBean pageBean, Contact contact);
    //查询总记录数
    public int contactCount(Contact contact);
}
