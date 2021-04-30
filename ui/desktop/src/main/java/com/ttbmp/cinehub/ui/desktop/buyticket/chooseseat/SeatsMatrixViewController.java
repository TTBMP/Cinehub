package com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat;


import com.ttbmp.cinehub.app.datamapper.SeatDataMapper;
import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

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
        List<SeatDto> seatListDto = viewModel.getSeatList();
        createMatrix(viewModel, seatListDto, toggleGroup);

    }

    private void createMatrix(BuyTicketViewModel viewModel, List<SeatDto> seatDtoList, ToggleGroup toggleGroup) {
        var size = SeatDataMapper.mapToEntityList(viewModel.getSeatList()).size();
        var rows = 10;
        var columns = (size / rows);
        var rest = size % rows;
        var count = 0;
        var buy = 0;
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < columns; j++) {
                var radioButton = new RadioButton();
                radioButton.setPadding(new Insets(5, 5, 5, 5));
                radioButton.setText(seatDtoList.get(count).getPosition() + "\n" + seatDtoList.get(count).getPrice() + "\u20ac");
                radioButton.setToggleGroup(toggleGroup);
                var imageView = new ImageView(new Image(String.valueOf(this.getClass().getResource("/drawables/office-chair.png"))));
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                radioButton.setGraphic(imageView);

                if (!viewModel.selectedProjectionProperty().getValue().getListTicket().isEmpty()) {
                    var finalCount = count;
                    viewModel.selectedProjectionProperty().getValue().getListTicket().forEach(x -> {
                        var val = seatDtoList.get(finalCount).getPosition();
                        if (x.getSeatDto().getPosition().equals(val)) {
                            radioButton.setDisable(true);
                        }
                    });
                }
                if (radioButton.isDisabled()) {
                    buy++;
                }
                gridSeats.add(radioButton, i, j);
                count++;
            }
        }
        addOtherButton(seatDtoList, columns, rest, count, buy, viewModel);
        updateValue(viewModel, size, buy);
    }

    private void addOtherButton(List<SeatDto> seatDtoList, int columns, int rest, int count, int buy, BuyTicketViewModel viewModel) {
        for (var k = 0; k < rest; k++) {
            var radioButton = new RadioButton();
            radioButton.setText(seatDtoList.get(count).getPosition() + "\n" + seatDtoList.get(count).getPrice() + "\u20ac");
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setPadding(new Insets(5, 5, 5, 5));
            if (!viewModel.selectedProjectionProperty().getValue().getListTicket().isEmpty()) {

                var finalCount = count;
                viewModel.selectedProjectionProperty().getValue().getListTicket().forEach(x -> {
                    var val = seatDtoList.get(finalCount).getPosition();
                    if (x.getSeatDto().getPosition().equals(val)) {
                        radioButton.setDisable(true);
                    }
                });
            }
            if (radioButton.isDisabled()) {
                buy++;
            }
            var imageView = new ImageView(new Image(String.valueOf(this.getClass().getResource("/drawables/office-chair.png"))));
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            radioButton.setGraphic(imageView);

            gridSeats.add(radioButton, k, columns);
            count++;
        }
    }

    private void updateValue(BuyTicketViewModel viewModel, int size, int buy) {
        viewModel.totalSeatsProperty().setValue("Places available: " + size);
        viewModel.buysSeatsProperty().setValue("Occupied seats: " + buy);
        viewModel.freeSeatsProperty().setValue("Free seats: " + (size - buy));
    }


}
