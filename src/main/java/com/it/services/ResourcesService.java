package com.it.services;

import com.it.bean.Resources;
import com.it.bean.User;

import java.util.List;

public interface ResourcesService {
    public List<Resources> findResourcesByUserId(User user);

    Resources findResourcesByPath(String requestURI);
}
