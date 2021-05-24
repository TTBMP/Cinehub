package com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * @author Ivan Palmieri
 */
public class SeatsMatrixViewController extends ViewController {

    private final GridPane gridSeats;
    private final ToggleGroup toggleGroup;

    public SeatsMatrixViewController(GridPane gridSeats, ToggleGroup toggleGroup) {
        this.gridSeats = gridSeats;
        this.toggleGroup = toggleGroup;
    }

    @Override
    protected void onLoad() {
        var viewModel = activity.getViewModel(BuyTicketViewModel.class);
        var seatNumber = viewModel.seatListProperty().size();
        var bookedNumber = viewModel.seatListProperty().stream().filter(SeatDto::isBooked).count();
        viewModel.totalSeatsProperty().setValue("Places available: " + seatNumber);
        viewModel.buysSeatsProperty().setValue("Occupied seats: " + bookedNumber);
        viewModel.freeSeatsProperty().setValue("Free seats: " + (seatNumber - bookedNumber));
        createMatrix(viewModel);
    }

    private void createMatrix(BuyTicketViewModel viewModel) {
        var price = viewModel.selectedProjectionProperty().getValue().getBasePrice();
        for (var seat : viewModel.seatListProperty()) {
            var radioButton = createRadioButton();
            radioButton.setUserData(seat);
            radioButton.setText(seat.getPosition() + "\n" + price + "\u20ac");
            radioButton.setDisable(seat.isBooked());
            var row = seat.getPosition().charAt(0) - 'A';
            var col = Integer.parseInt(seat.getPosition().substring(1));
            gridSeats.add(radioButton, col, row);
        }
    }

    private RadioButton createRadioButton() {
        var radioButton = new RadioButton();
        radioButton.setPadding(new Insets(5, 5, 5, 5));
        radioButton.setToggleGroup(toggleGroup);
        var imageView = new ImageView(new Image(String.valueOf(this.getClass().getResource("/drawables/office-chair.png"))));
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        radioButton.setGraphic(imageView);
        return radioButton;
    }

}
