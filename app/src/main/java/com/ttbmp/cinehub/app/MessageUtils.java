package com.ttbmp.cinehub.app;

class MessageUtils {

    private MessageUtils() {

    }

    public static String getMessage() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        return "Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".";
    }

}
