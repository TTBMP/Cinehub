package com.ttbmp.cinehub.service.payment.stripe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Palmieri Ivan
 */
class StripeServiceTest {

    @Test
    void pay_whitCorrectValue_notGenerateThrows() {
        var stripeService = new StripeService();
        Assertions.assertDoesNotThrow(() -> stripeService.requestPayment(
                "prova@perojhs.ds",
                "Mario",
                "4242424242424242",
                12L,
                "2024-05-11",
                "354"
                ));
    }

}