package com.it.services;

import com.it.bean.Country;
import com.it.dao.CountrydaoImpl;
import com.it.dao.ICountrydao;

import java.util.List;

public class CountryservicesImpl implements ICountryServices{
   ICountrydao iCountrydao = new CountrydaoImpl();
    @Override
    public List<Country> queryAllCountry() {
        return iCountrydao.queryAllCountry();
    }
}
