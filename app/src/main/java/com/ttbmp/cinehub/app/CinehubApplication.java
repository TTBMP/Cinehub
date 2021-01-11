package com.ttbmp.cinehub.app;

import com.ttbmp.cinehub.app.di.AppContainer;
import com.ttbmp.cinehub.app.ui.viewpersonalschedule.PersonalScheduleView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CinehubApplication extends Application {

    public static final AppContainer APP_CONTAINER = new AppContainer();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new PersonalScheduleView().getRoot());
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        primaryStage.setTitle("Cinehub");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
