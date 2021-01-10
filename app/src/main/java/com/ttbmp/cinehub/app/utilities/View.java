package com.ttbmp.cinehub.app.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public abstract class View {

    private final FXMLLoader loader;

    protected View(String fxmlResourceName) throws IOException {
        Objects.requireNonNull(fxmlResourceName);
        String instanceName = this.getClass().getSimpleName();
        if (!this.getClass().getName().matches(".*View$")) {
            throw new IOException("Invalid View class name: " + instanceName + "view classes must end with the View suffix");
        }
        URL fxmlResourceUrl = this.getClass().getResource("/layout/" + fxmlResourceName);
        Objects.requireNonNull(fxmlResourceUrl);
        loader = new FXMLLoader(fxmlResourceUrl);
        loader.load();
    }

    public Parent getRoot() {
        return loader.getRoot();
    }

    public Object getController() {
        return loader.getController();
    }

}
