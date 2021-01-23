package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;



import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarViewController;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema.ChooseCinemaView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.datamapper.ProjectionDataMapper;
import com.ttbmp.cinehub.core.datamapper.SeatDataMapper;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */



public class ChooseSeatViewController extends ViewController {


    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    private BuyTicketViewModel viewModel;
    @FXML
    private Button returnButton;
    @FXML
    private Button buyRandomButton;
    @FXML
    private Button confirmSeatButton;
    @FXML
    private Text seatsFreeText;
    @FXML
    private VBox seatsContainerVBox;
    @FXML
    private Text seatsBuysText;
    @FXML
    private Text seatsTotalText;

    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        buyRandomButton.setOnAction(a -> {

            try {
                changeLayoutRandom();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
        confirmSeatButton.setOnAction(a -> {
            try {
                changeLayoutSpecific();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        returnButton.setOnAction(a -> {
            try {
                navController.navigate(new NavDestination(new ChooseCinemaView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        activity.getUseCase(BuyTicketUseCase.class).getNumberOfSeats(ProjectionDataMapper.mapToEntity(viewModel.selectedProjectionProperty().getValue()));
        SeatsMatrixView seatsMatrixView;
        try {
            seatsMatrixView= new SeatsMatrixView();
            seatsMatrixView.load();
            seatsContainerVBox.getChildren().add(seatsMatrixView.getRoot());
            seatsMatrixView.getController().load(activity,navController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bind();
    }


    private void bind() {
        confirmSeatButton.disableProperty().bind(viewModel.getGroup().selectedToggleProperty().isNull());
        seatsTotalText.textProperty().bind(viewModel.totalSeatsProperty());
        seatsFreeText.textProperty().bind(viewModel.freeSeatsProperty());
        seatsBuysText.textProperty().bind(viewModel.buysSeatsProperty());
    }

    private void changeLayoutRandom() throws IOException {
        activity.getUseCase(BuyTicketUseCase.class).confirmSeatsRandom();
        activity.getUseCase(BuyTicketUseCase.class).getTicketBySeats(
                SeatDataMapper.mapToEntityList(
                        viewModel.getSeatList()),
                viewModel.selectedSeatsProperty().getValue(),
                viewModel.selectedPositionSeatIntegerProperty().getValue());
        navController.navigate(new NavDestination(new PaymentView()));
    }

    private void changeLayoutSpecific() throws IOException {
        activity.getUseCase(BuyTicketUseCase.class).confirmSeatsSpecific();
        activity.getUseCase(BuyTicketUseCase.class).getTicketBySeats(SeatDataMapper.mapToEntityList(
                viewModel.getSeatList()),
                viewModel.selectedSeatsProperty().getValue(),
                viewModel.selectedPositionSeatIntegerProperty().getValue());
        navController.navigate(new NavDestination(new PaymentView()));

    }
}



