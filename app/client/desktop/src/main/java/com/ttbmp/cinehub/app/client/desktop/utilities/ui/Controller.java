package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;

public abstract class Controller {

    protected Activity activity;
    protected NavController navController;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    @FXML
    private void initialize() {

    }

    public abstract void onLoad();

}
