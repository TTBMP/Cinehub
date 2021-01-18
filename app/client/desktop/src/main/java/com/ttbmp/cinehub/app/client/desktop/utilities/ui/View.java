package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

import javafx.scene.Parent;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public abstract class View {

    protected View() throws IOException {
        String instanceName = this.getClass().getSimpleName();
        if (!this.getClass().getName().matches(".*View$")) {
            throw new IOException("Invalid View class name: " + instanceName + "view classes must end with the View suffix");
        }
    }

    public abstract void load() throws IOException;

    public abstract Parent getRoot();

    public abstract Controller getController();

}
