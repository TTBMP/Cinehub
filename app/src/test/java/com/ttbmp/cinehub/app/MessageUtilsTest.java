package com.ttbmp.cinehub.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageUtilsTest {
    @Test void testGetMessage() {
        String message = "Hello, JavaFX " +
                System.getProperty("javafx.version") +
                "\nRunning on Java " +
                System.getProperty("java.version") +
                ".";
        assertEquals(message, MessageUtils.getMessage());
    }
}
