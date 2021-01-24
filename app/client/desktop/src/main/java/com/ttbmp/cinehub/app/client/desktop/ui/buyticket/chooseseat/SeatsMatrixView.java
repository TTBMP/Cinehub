package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.chooseseat;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.View;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class SeatsMatrixView extends View {

    private final SeatsMatrixViewController seatsMatrixController;
    private final Parent root;


    public SeatsMatrixView() throws IOException {
        super();
        GridPane gridSeats = new GridPane();
        seatsMatrixController = new SeatsMatrixViewController(gridSeats);
        this.root = gridSeats;
    }

    @Override
    public void load() throws IOException {
    }

    public Parent getRoot() {

        return root;
    }

    @Override
    public SeatsMatrixViewController getController() {
        return seatsMatrixController;
    }
}
