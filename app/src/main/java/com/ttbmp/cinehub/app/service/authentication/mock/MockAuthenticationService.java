package com.ttbmp.cinehub.app.service.authentication.mock;

import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Integer sigIn() {
        return 1;
    }

}
