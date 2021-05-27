package com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.View;

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
    public void initialize(NavController navController) {
        initialize(activity, navController);
    }

}
