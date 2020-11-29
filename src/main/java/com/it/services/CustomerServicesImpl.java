package com.it.services;

import com.it.bean.Customer;
import com.it.bean.PageBean;
import com.it.dao.CustomerdaoImpl;
import com.it.dao.ICustomerdao;

import java.util.List;

public class CustomerServicesImpl implements ICustomerServices{
   ICustomerdao iCustomerdao = new CustomerdaoImpl();

    @Override
    public boolean add(Customer customer) {
        return iCustomerdao.add(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return iCustomerdao.delete(customer);
    }

    @Override
    public int Deletess(String delIds) {
        return iCustomerdao.Deletess(delIds);
    }

    @Override
    public List<Customer> queryAllkehu(PageBean pageBean, Customer customer) {
        return iCustomerdao.queryAllkehu(pageBean,customer);
    }

    @Override
    public boolean update(Customer customer) {
        return iCustomerdao.update(customer);
    }

    @Override
    public List<Customer> queryAll(PageBean pageBean, Customer customer) {
        return iCustomerdao.queryAll(pageBean,customer);
    }

    @Override
    public int Count(Customer customer) {
        return iCustomerdao.Count(customer);
    }
}
