package com.ttbmp.cinehub.app.repository.shift.usher;


import com.ttbmp.cinehub.app.di.ServiceLocator;

public class JdbcUsherShiftRepository implements UsherShiftRepository{

    private final ServiceLocator serviceLocator;

    public JdbcUsherShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
