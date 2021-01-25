package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.chooseseat;


import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.core.datamapper.SeatDataMapper;
import com.ttbmp.cinehub.core.entity.Seat;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

import java.util.List;

public class SeatsMatrixViewController extends ViewController {
    private GridPane gridSeats;

    public SeatsMatrixViewController() {
    }

    public SeatsMatrixViewController(GridPane gridSeats) {
        this.gridSeats = gridSeats;
    }

    @Override
    protected void onLoad() {
        BuyTicketViewModel viewModel = activity.getViewModel(BuyTicketViewModel.class);
        List<Seat> seatList = SeatDataMapper.mapToEntityList(viewModel.getSeatList());
        createMatrix(viewModel, seatList);

    }

    private void createMatrix(BuyTicketViewModel viewModel, List<Seat> seatList) {
        int size = SeatDataMapper.mapToEntityList(viewModel.getSeatList()).size();
        int rows = 10;
        int colums = (size / rows);
        int rest = size % rows;
        int count = 0;
        int buy = 0;
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'L', 'M'};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                RadioButton radioButton = new RadioButton();
                if (i == 0) {
                    radioButton.setPadding(new Insets(5, 5, 5, 40));
                } else {
                    radioButton.setPadding(new Insets(5, 5, 5, 5));
                }
                radioButton.setText("" + a[j] + i + "\n" + seatList.get(count).getPrice() + "\u20ac");
                radioButton.setToggleGroup(viewModel.getGroup());

                if (seatList.get(count).getState().equals(Boolean.FALSE)) {
                    radioButton.setDisable(true);
                    buy++;
                }
                gridSeats.add(radioButton, i, j);
                count++;
            }
        }
        addRestantButton(seatList, viewModel, colums, rest, count, buy, a);
        updateValue(viewModel, size, buy);
    }

    private void addRestantButton(List<Seat> seatList, BuyTicketViewModel viewModel, int colums, int rest, int count, int buy, char[] a) {
        for (int k = 0; k < rest; k++) {
            RadioButton radioButton = new RadioButton();
            radioButton.setText("" + a[colums + 1] + k + "\n" + seatList.get(count).getPrice() + "\u20ac");
            radioButton.setToggleGroup(viewModel.getGroup());
            if (k == 0) {
                radioButton.setPadding(new Insets(5, 5, 5, 40));
            } else {
                radioButton.setPadding(new Insets(5, 5, 5, 5));
            }
            if (seatList.get(count).getState().equals(Boolean.FALSE)) {
                radioButton.setDisable(true);
                buy++;
            }

            gridSeats.add(radioButton, k, colums);
            count++;
        }
    }

    private void updateValue(BuyTicketViewModel viewModel, int size, int buy) {
        viewModel.totalSeatsProperty().setValue("Places available: " + size);
        viewModel.buysSeatsProperty().setValue("Occupied seats: " + buy);
        viewModel.freeSeatsProperty().setValue("Free seats: " + (size - buy));
    }


}
