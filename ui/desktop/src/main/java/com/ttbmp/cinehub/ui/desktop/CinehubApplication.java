package com.ttbmp.cinehub.ui.desktop;

import com.ttbmp.cinehub.ui.desktop.di.AppContainer;
import com.ttbmp.cinehub.ui.desktop.ui.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
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
        navController.navigate(new NavActivityDestination(new BuyTicketActivity()));
        primaryStage.setTitle("Cinehub");
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

}
