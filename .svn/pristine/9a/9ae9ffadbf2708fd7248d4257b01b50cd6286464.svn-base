package com.it.dao;

import com.it.bean.Customer;
import com.it.bean.PageBean;
import com.it.bean.User;

import java.util.List;

public interface ICustomerdao {
    //增
    public boolean add(Customer customer);
    //删
    public boolean delete(Customer customer);
    //删除多条记录
    public int Deletess(String delIds);
    //
    public List<Customer> queryAllkehu(PageBean pageBean, Customer customer);
    public boolean update(Customer customer);

    //带分页查询所有
    public List<Customer> queryAll(PageBean pageBean, Customer customer);
    //查询总记录数
    public int Count(Customer customer);
}
