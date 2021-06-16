package com.ttbmp.cinehub.app.repository.shift.usher;


import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;

public class JdbcUsherShiftRepository extends JdbcRepository implements UsherShiftRepository {

    public JdbcUsherShiftRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

}
