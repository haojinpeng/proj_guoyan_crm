package com.it.services.impl;

import com.it.bean.*;
import com.it.dao.ResourceDAO;
import com.it.dao.ResourceDAOImpl;
import com.it.services.IResourceSercices;

import java.util.List;

public class ResourceServicesImpl implements IResourceSercices {

    ResourceDAO resourceDAO=new ResourceDAOImpl();

    @Override
    public int addResource(Resource resource) {
        return resourceDAO.addResource(resource);
    }

    @Override
    public int addResources_jurisdiction(Resources_jurisdiction resources_jurisdiction) {
        return resourceDAO.addResources_jurisdiction(resources_jurisdiction);
    }

    @Override
    public int del(String delIds) {
        return resourceDAO.del(delIds);
    }

    @Override
    public int updResource(Resource resource) {
        return resourceDAO.updResource(resource);
    }

    @Override
    public List<ResourceAndJurisdiction> queryAll(PageBean pageBean, ResourceAndJurisdiction resourceAndJurisdiction) {
        return resourceDAO.queryAll(pageBean, resourceAndJurisdiction);
    }

    @Override
    public Resource select(Resource resource) {
        return resourceDAO.select(resource);
    }

    @Override
    public int count(ResourceAndJurisdiction resourceAndJurisdiction) {
        return resourceDAO.count(resourceAndJurisdiction);
    }

    @Override
    public List<Jurisdiction> queryAllJurisdiction(Jurisdiction jurisdiction) {
        return resourceDAO.queryAllJurisdiction(jurisdiction);
    }

    @Override
    public List<Resources_jurisdiction> queryResources_jurisdiction(Long res_id) {
        return resourceDAO.queryResources_jurisdiction(res_id);
    }

    @Override
    public int delResources_jurisdictionByResources_jurisdiction(Resources_jurisdiction resources_jurisdiction) {
        return resourceDAO.delResources_jurisdictionByResources_jurisdiction(resources_jurisdiction);
    }

    @Override
    public List<Resource> findResourcesByUserId(User user) {
        return resourceDAO.findResourcesByUserId(user);
    }

    @Override
    public List<Jurisdiction> queryJurisdictionByUserAndUrl(User user, String url) {
        return resourceDAO.queryJurisdictionByUserAndUrl(user,url);
    }


}
