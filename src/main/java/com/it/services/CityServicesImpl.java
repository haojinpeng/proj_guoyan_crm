package com.it.services;

import com.it.bean.City;
import com.it.dao.CitydaoImpl;
import com.it.dao.ICitydao;

import java.util.List;

public class CityServicesImpl implements ICityServices{
    ICitydao iCitydao = new CitydaoImpl();
    @Override
    public List<City> queryAllCity() {
        return iCitydao.queryAllCity();
    }
}
