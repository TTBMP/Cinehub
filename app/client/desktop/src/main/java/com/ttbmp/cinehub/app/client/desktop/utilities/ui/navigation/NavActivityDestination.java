package com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.View;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class NavActivityDestination extends NavDestination {

    public NavActivityDestination(Activity activity) {
        this(activity, activity.getInitialView());
    }

    protected NavActivityDestination(Activity activity, View view) {
        super(view);
        this.activity = activity;
    }

    @Override
    public void initialize(NavController navController) throws IOException {
        initialize(activity, navController);
    }

}
