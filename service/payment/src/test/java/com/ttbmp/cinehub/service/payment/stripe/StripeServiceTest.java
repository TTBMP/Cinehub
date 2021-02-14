package com.ttbmp.cinehub.service.payment.stripe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class StripeServiceTest {



    @Test
    void pay_withIncorrectNumberOfCard_generateThrows() {
        StripeService stripeService = new StripeService();
        Assertions.assertThrows(
                StripeServiceException.class,
                ()->  stripeService.pay("prova@perojhs.ds","prova","89548958934758437",12L)
        );
    }

    @Test
    void pay_withInvalidEmailUser_generateThrows() {
        StripeService stripeService = new StripeService();
        Assertions.assertThrows(
                StripeServiceException.class,
                ()->  stripeService.pay("","","89548958934758437",12L)
        );
    }

    @Test
    void pay_whitCorrectValue_notGenerateThrows() {
        StripeService stripeService = new StripeService();
        Assertions.assertDoesNotThrow(()->  stripeService.pay("prova@perojhs.ds","prova","4242424242424242",12L)
        );
    }
}