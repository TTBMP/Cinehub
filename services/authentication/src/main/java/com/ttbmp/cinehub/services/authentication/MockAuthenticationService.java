package com.ttbmp.cinehub.services.authentication;

import com.ttbmp.cinehub.core.entity.session.Session;
import com.ttbmp.cinehub.core.services.AuthenticationService;

public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Session getCurrentSession() {
        return new Session();
    }

}
