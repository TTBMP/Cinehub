package com.ttbmp.cinehub.service.authentication;

import com.ttbmp.cinehub.core.entity.session.Session;
import com.ttbmp.cinehub.core.services.AuthenticationService;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Session getCurrentSession() {
        return new Session();
    }

}
