package com.it.services;

import com.it.bean.Province;
import com.it.dao.IProvincedao;
import com.it.dao.Provincedao;

import java.util.List;

public class ProvinceServiceImpl implements IProvinceService{
    IProvincedao iProvincedao = new Provincedao();
    @Override
    public List<Province> queryAllProvince() {
        return iProvincedao.queryAllProvince();
    }
}
