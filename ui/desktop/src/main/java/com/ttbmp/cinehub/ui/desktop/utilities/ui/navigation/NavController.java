package com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.ui.desktop.error.ErrorView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.stage.DialogStage;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class NavController {

    private final Deque<NavDestination> navBackStack = new ArrayDeque<>();
    private final Stage stage;
    private NavDestination currentDestination = null;

    public NavController(Stage stage) {
        this.stage = stage;
    }

    public NavDestination getCurrentDestination() {
        return currentDestination;
    }

    public void open(NavActivityDestination destination) {
        try {
            Objects.requireNonNull(destination);
            navBackStack.clear();
            currentDestination = destination;
            destination.initialize(this);
            navBackStack.push(destination);
            stage.setScene(destination.scene);
        } catch (Exception e) {
            openErrorDialog(e.getMessage(), true);
        }
    }

    public void openInDialog(NavDestination destination, String title) {
        Objects.requireNonNull(destination);
        Objects.requireNonNull(title);
        var dialogNavController = new NavController(new DialogStage(stage, title));
        dialogNavController.navigate(new NavActivityDestination(currentDestination.activity, destination.view));
        dialogNavController.stage.show();
    }

    public void openErrorDialog(String errorMessage, boolean isFatal) {
        Objects.requireNonNull(errorMessage);
        NavFunction onCloseFunction = isFatal ? navController -> Platform.exit() : NavController::goBack;
        var errorView = new ErrorView(errorMessage, onCloseFunction);
        openInDialog(new NavDestination(errorView), "Error");
        var errorStage = ((Stage) errorView.getRoot().getScene().getWindow());
        errorStage.setResizable(false);
        errorStage.setAlwaysOnTop(true);
        errorStage.setOnCloseRequest(w -> onCloseFunction.execute(errorView.getController().getNavController()));
    }

    public void navigate(NavDestination destination) {
        try {
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
        } catch (Exception e) {
            openErrorDialog(e + ": " + e.getMessage(), true);
        }
    }

    public void goBack() {
        if (!navBackStack.isEmpty()) {
            navBackStack.pop();
            if (navBackStack.isEmpty()) {
                stage.close();
            } else {
                var destination = navBackStack.pop();
                navigate(destination);
            }
        }
    }

    public void openPreviousActivity() {
        try {
            Class<? extends Activity> prevActivityClass = null;
            for (var destination : navBackStack) {
                if (destination.activity != currentDestination.activity) {
                    prevActivityClass = destination.activity.getClass();
                    break;
                }
            }
            var prevActivity = Objects.requireNonNull(prevActivityClass).getConstructor().newInstance();
            open(new NavActivityDestination(prevActivity));
        } catch (Exception e) {
            openErrorDialog(e.getMessage(), true);
        }
    }

    public interface NavFunction {
        void execute(NavController navController);
    }

}
