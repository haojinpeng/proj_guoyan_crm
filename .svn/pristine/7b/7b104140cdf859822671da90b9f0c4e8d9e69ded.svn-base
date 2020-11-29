package com.it.services;

import com.it.bean.Company;
import com.it.bean.PageBean;
import com.it.dao.CompanyDao;
import com.it.dao.CompanyDaoImpl;

import java.util.List;

public class CompanyServicesImpl implements CompanyService {
private CompanyDao companyDao = new CompanyDaoImpl();
    @Override
    public boolean addCompany(Company company) {
        return companyDao.addCompany(company);
    }

    @Override
    public boolean updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }

    @Override
    public boolean deleteCompany(Company company) {
        return companyDao.deleteCompany(company);
    }

    @Override
    public List<Company> queryAllCompany(PageBean pageBean, Company company) {
        return companyDao.queryAllCompany(pageBean,company);
    }

    @Override
    public int count() {
        return companyDao.count();
    }
}
