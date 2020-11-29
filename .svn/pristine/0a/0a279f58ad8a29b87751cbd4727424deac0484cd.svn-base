package com.it.services;

import com.it.bean.Education;
import com.it.dao.EducationdaoImpl;
import com.it.dao.IEducationdao;

import java.util.List;

public class EducationServicesImpl implements IEducationServices{
   IEducationdao iEducationdao = new EducationdaoImpl();

    @Override
    public List<Education> queryAllEducation() {
        return iEducationdao.queryAllEducation();
    }
}
