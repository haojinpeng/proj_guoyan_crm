package com.it.dao;

import com.it.bean.Country;

import java.util.List;

public interface ICountrydao {
    // 查询国家
    List<Country> queryAllCountry();
}
