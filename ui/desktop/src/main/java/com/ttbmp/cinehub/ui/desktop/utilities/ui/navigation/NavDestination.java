package com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.View;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class NavDestination {

    protected final View view;
    protected Activity activity;
    protected Scene scene;
    private boolean loaded;

    public NavDestination(View view) {
        this.view = view;
        loaded = false;
    }

    public NavDestination(View view, Activity activity) {
        this(view);
        this.activity = activity;
    }

    public void initialize(NavController navController) {
        initialize(navController.getCurrentDestination().activity, navController);
    }

    protected void initialize(Activity activity, NavController navController) {
        try {
            if (!loaded) {
                this.activity = activity;
                view.load();
                view.getController().load(activity, navController);
                scene = new Scene(view.getRoot());
                scene.getStylesheets().addAll(view.getStylesheetList());
                loaded = true;
            }
        } catch (IOException e) {
            navController.openErrorDialog(e.getMessage(), true);
        }
    }

}
