package com.ttbmp.cinehub.ui.desktop.utilities.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public abstract class FxmlView extends View {

    private final FXMLLoader loader;

    protected FxmlView(String fxmlResourceName) {
        super();
        var fxmlResourceUrl = this.getClass().getResource("/layout/" + fxmlResourceName);
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
    public ViewController getController() {
        return loader.getController();
    }

}
