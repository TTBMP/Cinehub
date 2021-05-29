package com.ttbmp.cinehub.ui.desktop.error;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.View;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ErrorView extends View {

    private final ErrorViewController controller;
    private final Parent root;

    public ErrorView(String errorMessage, NavController.NavFunction onClose) {
        super();
        this.root = new VBox();
        this.controller = new ErrorViewController((VBox) root, errorMessage, onClose);
    }

    @Override
    public void load() {
        // unused
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    @Override
    public ViewController getController() {
        return controller;
    }

}
