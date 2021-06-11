package com.ttbmp.cinehub.ui.desktop.appbar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("java:S110")
public class LogoView extends HBox {

    public LogoView() {
        var label1 = new Label("Cine");
        var label2 = new Label("hub");
        this.getChildren().add(label1);
        this.getChildren().add(label2);
        this.setFillHeight(true);
        this.setAlignment(Pos.CENTER);
        label1.setStyle("-fx-text-fill: white;");
        label2.setStyle("-fx-text-fill: #212529;" +
                "-fx-border-radius: .25em;" +
                "-fx-background-color: #0d6efd;" +
                "-fx-background-radius: .25em;" +
                "-fx-padding: .25em;"
        );
        this.setStyle("-fx-spacing: .15em;");
    }

}
