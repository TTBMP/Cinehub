package com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.View;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class NavDestination {

    protected View view;
    protected Activity activity;
    protected Scene scene;
    private boolean loaded;

    public NavDestination(View view) {
        this.view = view;
        loaded = false;
    }

    public void initialize(NavController navController) throws IOException {
        initialize(navController.getCurrentDestination().activity, navController);
    }

    protected void initialize(Activity activity, NavController navController) throws IOException {
        if (!loaded) {
            this.activity = activity;
            view.load();
            view.getController().load(activity, navController);
            scene = new Scene(view.getRoot());
            scene.getStylesheets().addAll(view.getStylesheetList());
            loaded = true;
        }
    }

}
