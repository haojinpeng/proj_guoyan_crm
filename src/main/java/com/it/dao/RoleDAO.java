package com.it.dao;

import com.it.bean.*;

import java.util.List;

public interface RoleDAO {
    //添加角色
    public int addRole(Role role);

    //添加角色_资源_权限连接
    public int addRoleAndResourcesAndJurisdiction(RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction);

    //删除多条记录
    public int del(String delIds);

    // 根据role_id删除Role_res_jur
    public int delRole_res_jurByRole_id(Long id);

    //根据role_id修改role_name
    public int updRole(Role role);

    //带分页查询所有
    public List<RoleAndResourcesAndJurisdiction> queryAll(PageBean pageBean, RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction);

    //带分页查询所有的记录数
    public int count(RoleAndResourcesAndJurisdiction roleAndResourcesAndJurisdiction);

    // 查找Role
    public Role selectRole(Role role);

    //查询所有的res_jur
    public List<ResourceAndJurisdiction> queryAllResAndJur(ResourceAndJurisdiction resourceAndJurisdiction);

}
