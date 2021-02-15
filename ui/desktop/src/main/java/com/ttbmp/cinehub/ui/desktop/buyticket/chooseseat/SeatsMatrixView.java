package com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.View;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * @author Ivan Palmieri
 */
public class SeatsMatrixView extends View {

    private final SeatsMatrixViewController seatsMatrixController;
    private final Parent root;


    public SeatsMatrixView(ToggleGroup toggleGroup) throws IOException {
        super();
        GridPane gridSeats = new GridPane();
        seatsMatrixController = new SeatsMatrixViewController(gridSeats, toggleGroup);
        this.root = gridSeats;
    }

    @Override
    public void load() throws IOException {
        //empty
    }

    public Parent getRoot() {

        return root;
    }

    @Override
    public SeatsMatrixViewController getController() {
        return seatsMatrixController;
    }
}
