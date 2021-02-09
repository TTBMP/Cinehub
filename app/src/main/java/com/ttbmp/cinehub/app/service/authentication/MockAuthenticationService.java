package com.ttbmp.cinehub.app.service.authentication;

/**
 * @author Fabio Buracchi
 */
public class MockAuthenticationService implements AuthenticationService {

    @Override
    public Integer sigIn() {
        return 1;
    }

    @Override
    public String sigInFireBase()  {
        return "Key:value";
    }

    @Override
    public String register(String email, String password){
         return "Key:value";
    }


}
