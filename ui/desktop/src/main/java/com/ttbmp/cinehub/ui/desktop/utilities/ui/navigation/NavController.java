package com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation;

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

    public void navigate(NavDestination destination) throws IOException {
        Objects.requireNonNull(destination);
        if (destination instanceof NavActivityDestination) {
            currentDestination = destination;
        } else {
            var activity = currentDestination.activity;
            currentDestination = destination;
            currentDestination.activity = activity;
        }
        destination.initialize(this);
        navBackStack.push(destination);
        stage.setScene(destination.scene);
    }

    public void openInDialog(NavDestination destination, String title) throws IOException {
        Objects.requireNonNull(destination);
        Objects.requireNonNull(title);
        var dialogNavController = new NavController(new DialogStage(stage, title));
        dialogNavController.navigate(new NavActivityDestination(currentDestination.activity, destination.view));
        dialogNavController.stage.show();
    }

    public NavDestination getCurrentDestination() {
        return currentDestination;
    }

    public void popBackStack() throws IOException {
        if (!navBackStack.isEmpty()) {
            navBackStack.pop();
            if (navBackStack.isEmpty()) {
                stage.close();
            } else {
                var destination = navBackStack.pop();
                navigate(destination);
                currentDestination = destination;
            }
        }
    }

}
