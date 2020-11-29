package com.it.services;

import com.it.bean.Privilege;
import com.it.bean.Resources;
import com.it.bean.User;

import java.util.List;

public interface PrivilegeService {
    List<Privilege> findPrivilegeByResourcesAndUser(Resources resources, User user);
}
