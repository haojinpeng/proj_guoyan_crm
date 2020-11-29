package com.it.services;

import com.it.bean.Resources;
import com.it.bean.User;
import com.it.dao.ResourcesDao;
import com.it.dao.ResourcesDaoImpl;

import java.util.List;

public class ResourcesServicesImpl implements ResourcesService{
ResourcesDao resourcesDao = new ResourcesDaoImpl();
    @Override
    public List<Resources> findResourcesByUserId(User user) {
        return resourcesDao.selectResourcesByUserId(user);
    }

    @Override
    public Resources findResourcesByPath(String requestURI) {
        return resourcesDao.selectResourcesByPath(requestURI);
    }
}
