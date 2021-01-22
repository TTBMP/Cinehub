package com.ttbmp.cinehub.service.authentication;

import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Integer sigIn() {
        return 0;
    }

}
