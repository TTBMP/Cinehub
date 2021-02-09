package com.ttbmp.cinehub.app.service.authentication;


/**
 * @author Fabio Buracchi
 */
public interface AuthenticationService {

    Integer sigIn();

    String sigInFireBase() throws  LoginException;

     String register(String email, String password) throws LoginException;


    }
