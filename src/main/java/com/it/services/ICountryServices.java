package com.it.services;

import com.it.bean.Country;

import java.util.List;

public interface ICountryServices {
    // 查询国家
    List<Country> queryAllCountry();
}
