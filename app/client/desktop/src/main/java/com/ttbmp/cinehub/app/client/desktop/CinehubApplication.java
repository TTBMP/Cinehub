package com.ttbmp.cinehub.app.client.desktop;

import com.ttbmp.cinehub.app.client.desktop.di.AppContainer;
import com.ttbmp.cinehub.app.client.desktop.ui.manageshift.ManageShiftActivity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import javafx.application.Application;
import javafx.stage.Stage;


public class CinehubApplication extends Application {

    public static final AppContainer APP_CONTAINER = new AppContainer();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        NavController navController = new NavController(primaryStage);
        navController.navigate(new NavActivityDestination(new ManageShiftActivity()));

        primaryStage.setTitle("Cinehub");
        primaryStage.show();
    }

}
