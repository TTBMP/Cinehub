package com.ttbmp.cinehub.app.client.desktop.utilities;

import com.ttbmp.cinehub.app.client.desktop.CinehubApplication;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class Utilities {

    private Utilities() {

    }

    public static Stage getStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    public static void createPopup(View view, String title, Stage primaryStage) {
        Objects.requireNonNull(view);
        Objects.requireNonNull(title);
        Objects.requireNonNull(primaryStage);
        Parent root = view.getRoot();
        Objects.requireNonNull(root);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(CinehubApplication.class.getResource("/styles/styles.css").toExternalForm());
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setTitle(title);
        dialogStage.setScene(scene);
        dialogStage.show();
    }
}
