package com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.stage.DialogStage;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class NavController {

    private final Deque<NavDestination> navBackStack = new ArrayDeque<>();
    Stage stage;
    private NavDestination currentDestination = null;

    public NavController(Stage stage) {
        this.stage = stage;
    }

    public NavDestination getCurrentDestination() {
        return currentDestination;
    }

    public Class<? extends Activity> getCurrentDestinationActivityClass() {
        return currentDestination.activity.getClass();
    }

    public Class<? extends Activity> getPreviousDestinationActivityClass() {
        for (NavDestination destination : navBackStack) {
            if (destination.activity != currentDestination.activity) {
                return destination.activity.getClass();
            }
        }
        return null;
    }

    public void open(NavActivityDestination destination) throws IOException {
        Objects.requireNonNull(destination);
        navBackStack.clear();
        currentDestination = destination;
        destination.initialize(this);
        navBackStack.push(destination);
        stage.setScene(destination.scene);
    }

    public void openInDialog(NavDestination destination, String title) throws IOException {
        Objects.requireNonNull(destination);
        Objects.requireNonNull(title);
        NavController dialogNavController = new NavController(new DialogStage(stage, title));
        dialogNavController.navigate(new NavActivityDestination(currentDestination.activity, destination.view));
        dialogNavController.stage.show();
    }

    public void navigate(NavDestination destination) throws IOException {
        Objects.requireNonNull(destination);
        if (destination instanceof NavActivityDestination) {
            currentDestination = destination;
        } else {
            Activity activity = currentDestination.activity;
            currentDestination = destination;
            currentDestination.activity = activity;
        }
        destination.initialize(this);
        navBackStack.push(destination);
        stage.setScene(destination.scene);
    }

    public void navBack() throws IOException {
        if (!navBackStack.isEmpty()) {
            navBackStack.pop();
            if (navBackStack.isEmpty()) {
                stage.close();
            } else {
                NavDestination destination = navBackStack.pop();
                navigate(destination);
            }
        }
    }

}
