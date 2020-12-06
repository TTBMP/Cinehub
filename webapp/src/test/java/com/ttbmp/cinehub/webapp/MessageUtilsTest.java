package com.ttbmp.cinehub.webapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageUtilsTest {
    @Test void testGetMessage() {
        assertEquals("Hello world!", MessageUtils.getMessage());
    }
}
