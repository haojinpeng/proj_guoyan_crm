package com.it.services.impl;

import com.it.bean.PageBean;
import com.it.bean.ResourceAndJurisdiction;
import com.it.bean.Role;
import com.it.bean.RoleAndResourcesAndJurisdiction;
import com.it.dao.RoleDAO;
import com.it.dao.RoleDAOImpl;
import com.it.services.IRoleServices;

import java.util.List;

public class RoleServicesImpl implements IRoleServices {
    RoleDAO roleDAO = new RoleDAOImpl();

    @Override
    public int addRole(Role role) {
        return roleDAO.addRole(role);
    }

    @Override
    public int addRoleAndResourcesAndJurisdiction(RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction) {
        return roleDAO.addRoleAndResourcesAndJurisdiction(roleAndResourcesAndJurisdiction);
    }

    @Override
    public int del(String delIds) {
        return roleDAO.del(delIds);
    }

    @Override
    public int delRole_res_jurByRole_id(Long id) {
        return roleDAO.delRole_res_jurByRole_id(id);
    }

    @Override
    public int updRole(Role role) {
        return roleDAO.updRole(role);
    }

    @Override
    public List<RoleAndResourcesAndJurisdiction> queryAll(PageBean pageBean, RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction) {
        return roleDAO.queryAll(pageBean, roleAndResourcesAndJurisdiction);
    }

    @Override
    public int count(RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction) {
        return roleDAO.count(roleAndResourcesAndJurisdiction);
    }

    @Override
    public Role selectRole(Role role) {
        return roleDAO.selectRole(role);
    }

    @Override
    public List<ResourceAndJurisdiction> queryAllResAndJur(ResourceAndJurisdiction resourceAndJurisdiction) {
        return roleDAO.queryAllResAndJur(resourceAndJurisdiction);
    }
}
