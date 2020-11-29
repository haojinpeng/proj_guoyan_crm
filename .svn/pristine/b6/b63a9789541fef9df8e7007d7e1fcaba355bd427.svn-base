package com.it.services;

import com.it.bean.Nation;
import com.it.dao.INationdao;
import com.it.dao.NationdaoImpl;

import java.util.List;

public class NationServicesImpl implements INationServices{
    INationdao iNationdao = new NationdaoImpl();
    @Override
    public List<Nation> queryAllNation() {

        return  iNationdao.queryAllNation();
    }
}
