package com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.stage.DialogStage;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class NavController {

    private final Deque<NavDestination> navBackStack = new ArrayDeque<>();
    Stage stage;

    public NavController(Stage stage) {
        this.stage = stage;
    }

    public void navigate(NavDestination destination) throws IOException {
        Objects.requireNonNull(destination);
        destination.initialize(this);
        navBackStack.push(destination);
        stage.setScene(destination.scene);
    }

    public void openInDialog(NavDestination destination, String title) throws IOException {
        Objects.requireNonNull(destination);
        Objects.requireNonNull(title);
        NavController dialogNavController = new NavController(new DialogStage(stage, title));
        dialogNavController.navigate(new NavActivityDestination(getCurrentDestination().activity, destination.view));
        dialogNavController.stage.show();
    }

    public NavDestination getCurrentDestination() {
        return navBackStack.isEmpty() ? null : navBackStack.peek();
    }

    public void popBackStack() throws IOException {
        if (!navBackStack.isEmpty()) {
            navBackStack.pop();
            if (navBackStack.isEmpty()) {
                stage.close();
            } else {
                navigate(navBackStack.pop());
            }
        }
    }

}
