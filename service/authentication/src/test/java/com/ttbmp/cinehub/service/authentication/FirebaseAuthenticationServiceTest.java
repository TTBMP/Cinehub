package com.ttbmp.cinehub.service.authentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirebaseAuthenticationServiceTest {

    @Test
    void createUser_withCorrectParam_notGenerateThrows() throws FirebaseException {
        FirebaseAuthenticationService firebaseAuthenticationService = new FirebaseAuthenticationService();
       Assertions.assertDoesNotThrow(()->firebaseAuthenticationService.createUser("provami@provami.provami","password"));
    }

}