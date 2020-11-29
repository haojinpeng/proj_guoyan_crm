package com.it.services;

import com.it.bean.*;

import java.util.List;

public interface IResourceSercices {
    //添加资源
    public int addResource(Resource resource);

    //添加资源_权限连接
    public int addResources_jurisdiction(Resources_jurisdiction resources_jurisdiction);

    //删除多条记录
    public int del(String delIds);

    //改资源
    public int updResource(Resource resource);

    //带分页查询所有
    public List<ResourceAndJurisdiction> queryAll(PageBean pageBean, ResourceAndJurisdiction resourceAndJurisdiction);

    //查单条资源
    public Resource select(Resource resource);

    //带分页查询所有的记录数
    public int count(ResourceAndJurisdiction resourceAndJurisdiction);

    //查询所有权限
    public List<Jurisdiction> queryAllJurisdiction(Jurisdiction jurisdiction);

    //根据resource_id查找Resources_jurisdiction表的内容
    public List<Resources_jurisdiction> queryResources_jurisdiction(Long res_id);

    //根据Resources_jurisdiction删除Resources_jurisdiction表的内容
    public int delResources_jurisdictionByResources_jurisdiction(Resources_jurisdiction resources_jurisdiction);

    //根据user_id查找资源
    public List<Resource> findResourcesByUserId(User user);

    //根据url(即resource path)，user查找对应权限
    public List<Jurisdiction> queryJurisdictionByUserAndUrl(User user,String url);
}
