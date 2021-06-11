package com.ttbmp.cinehub.ui.desktop;

import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Fabio Buracchi
 */
public class CinehubApplication extends Application {

    private static String sessionToken;

    public static void main(String[] args) {
        launch(args);
    }

    public static String getSessionToken() {
        return sessionToken;
    }

    public static void setSessionToken(String sessionToken) {
        CinehubApplication.sessionToken = sessionToken;
    }

    @Override
    public void start(Stage primaryStage) {
        var navController = new NavController(primaryStage);
        navController.navigate(new NavActivityDestination(new BuyTicketActivity()));
        primaryStage.setTitle("Cinehub");
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(856);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(856);
        primaryStage.show();
    }

}
