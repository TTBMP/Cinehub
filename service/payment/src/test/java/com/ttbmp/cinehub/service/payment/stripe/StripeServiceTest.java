package com.ttbmp.cinehub.service.payment.stripe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Palmieri Ivan
 */
class StripeServiceTest {

    @Test
    void pay_withInvalidEmailUser_generateThrows() {
        var stripeService = new StripeService();
        Assertions.assertThrows(
                StripeServiceException.class,
                () -> stripeService.pay("", "", "89548958934758437", 12L)
        );
    }

    @Test
    void pay_whitCorrectValue_notGenerateThrows() {
        var stripeService = new StripeService();
        Assertions.assertDoesNotThrow(() -> stripeService.pay("prova@perojhs.ds", "prova", "4242424242424242", 12L)
        );
    }


}