package com.ttbmp.cinehub.domain.service.authentication.mock;

import com.ttbmp.cinehub.domain.service.authentication.AuthenticationService;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Integer sigIn() {
        return 1;
    }

}
