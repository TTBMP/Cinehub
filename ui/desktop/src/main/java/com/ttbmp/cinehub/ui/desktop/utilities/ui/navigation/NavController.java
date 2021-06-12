package com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation;

import com.ttbmp.cinehub.ui.desktop.error.ErrorView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.View;
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

    public void openActivity(Class<? extends Activity> activityClass) {
        try {
            Objects.requireNonNull(activityClass);
            var activity = activityClass.getConstructor().newInstance();
            var destination = new NavActivityDestination(activity);
            openDestination(destination);
        } catch (Exception e) {
            openErrorDialog(e + ": " + e.getMessage(), true);
        }
    }

    public void openActivityInDialog(Class<? extends Activity> activityClass, String title) {
        var dialogNavController = new NavController(new DialogStage(stage, title));
        dialogNavController.openActivity(activityClass);
        dialogNavController.stage.show();
    }

    public void openView(Class<? extends View> viewClass) {
        try {
            Objects.requireNonNull(viewClass);
            var view = viewClass.getConstructor().newInstance();
            var destination = new NavDestination(view, currentDestination.activity);
            openDestination(destination);
        } catch (Exception e) {
            openErrorDialog(e + ": " + e.getMessage(), true);
        }
    }

    public void openViewInDialog(Class<? extends View> viewClass, String title) {
        var dialogNavController = new NavController(new DialogStage(stage, title));
        dialogNavController.currentDestination = currentDestination;
        dialogNavController.openView(viewClass);
        dialogNavController.stage.show();
    }

    public void openErrorDialog(String errorMessage, boolean isFatal) {
        Objects.requireNonNull(errorMessage);
        NavFunction onCloseFunction = isFatal ? navController -> Platform.exit() : NavController::goBack;
        var errorView = new ErrorView(errorMessage, onCloseFunction);
        var destination = new NavDestination(errorView, currentDestination.activity);
        var dialogNavController = new NavController(new DialogStage(stage, "Error"));
        dialogNavController.openDestination(destination);
        dialogNavController.stage.show();
        dialogNavController.stage.setResizable(false);
        dialogNavController.stage.setAlwaysOnTop(true);
        dialogNavController.stage.setOnCloseRequest(w -> onCloseFunction.execute(errorView.getController().getNavController()));
    }

    public void replaceActivity(Class<? extends Activity> activityClass) {
        navBackStack.clear();
        openActivity(activityClass);
    }

    public void replaceWithPreviousActivity() {
        while (navBackStack.peek() != null && navBackStack.peek().activity.equals(currentDestination.activity)) {
            navBackStack.pop();
        }
        replaceActivity(navBackStack.pop().activity.getClass());
    }

    public void goBack() {
        if (!navBackStack.isEmpty()) {
            navBackStack.pop();
            if (navBackStack.isEmpty()) {
                stage.close();
            } else {
                openDestination(navBackStack.pop());
            }
        }
    }

    public void goBackToPreviousActivity() {
        var lastPopped = currentDestination;
        while (navBackStack.peek() != null && navBackStack.peek().activity.equals(currentDestination.activity)) {
            lastPopped = navBackStack.pop();
        }
        navBackStack.push(lastPopped);
        goBack();
    }

    private void openDestination(NavDestination destination) {
        currentDestination = destination;
        destination.initialize(this);
        navBackStack.push(destination);
        stage.setScene(destination.scene);
    }

    public interface NavFunction {
        void execute(NavController navController);
    }

}
