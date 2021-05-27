package com.ttbmp.cinehub.ui.desktop.error;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ErrorViewController extends ViewController {

    private final VBox vBox;
    private final String errorMessage;
    private final NavController.NavFunction onClose;

    public ErrorViewController(VBox vBox, String errorMessage, NavController.NavFunction onClose) {
        this.vBox = vBox;
        this.errorMessage = errorMessage;
        this.onClose = onClose;
    }

    @Override
    protected void onLoad() {
        var label = new Label(errorMessage);
        var button = new Button("OK");
        button.setOnAction(a -> onClose.execute(navController));
        button.setPrefWidth(64);
        vBox.getChildren().add(label);
        vBox.getChildren().add(button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);
        vBox.setPadding(new Insets(32, 92, 32, 92));
    }

}
