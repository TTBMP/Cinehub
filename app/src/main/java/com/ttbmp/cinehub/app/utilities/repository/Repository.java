package com.ttbmp.cinehub.app.utilities.repository;

import com.ttbmp.cinehub.app.di.ServiceLocator;

public abstract class Repository {

    private final ServiceLocator serviceLocator;

    protected Repository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

}
