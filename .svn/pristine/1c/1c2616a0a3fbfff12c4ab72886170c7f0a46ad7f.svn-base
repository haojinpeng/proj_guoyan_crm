package com.it.services;

import com.it.bean.WorkSpaces;
import com.it.dao.IWorkspavesDao;
import com.it.dao.IWorkspavesDaoImpl;

import java.util.List;

public class IWorkspacesServicesImpl implements IWorkspacesServices {
    private IWorkspavesDao workspavesDao = new IWorkspavesDaoImpl();
    @Override
    public List<WorkSpaces> query() {
        return workspavesDao.query();
    }
}
