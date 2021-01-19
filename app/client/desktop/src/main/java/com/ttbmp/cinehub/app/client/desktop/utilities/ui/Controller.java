package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;

public abstract class Controller {

    protected Activity activity;
    protected NavController navController;

    public void load(Activity activity, NavController navController) {
        this.activity = activity;
        this.navController = navController;
        onLoad();
    }

    protected abstract void onLoad();

    @FXML
    private void initialize() {

    }

}
