package com.it.services;

import com.it.bean.Contact;
import com.it.bean.PageBean;
import com.it.dao.Contactdao;
import com.it.dao.IContactdao;

import java.util.List;

public class ContactServicesImpl implements main.java.com.it.services.IContactServices {
   IContactdao iContactdao = new Contactdao();
    @Override
    public boolean add(Contact contact) {
        return iContactdao.add(contact);
    }

    @Override
    public boolean logion(Contact contact) {
        return false;
    }


    @Override
    public boolean del(Contact contact) {
        return iContactdao.del(contact);
    }

    @Override
    public int Deletess(String delIds) {
        return iContactdao.Deletess(delIds);
    }

    @Override
    public boolean update(Contact contact) {
        return iContactdao.update(contact);
    }

    @Override
    public List<Contact> queryAllCotact(PageBean pageBean, Contact contact) {
        return iContactdao.queryAllCotact(pageBean, contact);
    }

    @Override
    public int contactCount(Contact contact) {
        return iContactdao.contactCount(contact);
    }
}
