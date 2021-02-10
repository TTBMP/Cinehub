package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Integer sigIn() {
        return 1;
    }

}
