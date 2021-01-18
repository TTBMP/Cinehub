package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public abstract class FxmlView extends View {

    private final FXMLLoader loader;

    protected FxmlView(String fxmlResourceName) throws IOException {
        super();
        Objects.requireNonNull(fxmlResourceName);
        URL fxmlResourceUrl = this.getClass().getResource("/layout/" + fxmlResourceName);
        Objects.requireNonNull(fxmlResourceUrl);
        loader = new FXMLLoader(fxmlResourceUrl);
    }

    @Override
    public void load() throws IOException {
        loader.load();
    }

    @Override
    public Parent getRoot() {
        return loader.getRoot();
    }

    @Override
    public Controller getController() {
        return loader.getController();
    }
}
