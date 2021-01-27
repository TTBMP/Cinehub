package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.chooseseat;


import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarViewController;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema.ChooseCinemaView;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.payment.PaymentView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.core.usecase.buyticket.request.GetTicketBySeatsRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    @FXML
    private Text seatPriceText;

    @FXML
    private RadioButton foldingArmchairRadioButton;
    @FXML
    private RadioButton heatedArmchairRadioButton;
    @FXML
    private RadioButton skipLineRadioButton;

    private BuyTicketViewModel viewModel;
    @FXML
    private Button returnButton;
    @FXML
    private Button buyRandomButton;

    @FXML
    private Label errorSectionLabel;
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
        activity.getUseCase(BuyTicketUseCase.class).getListOfSeatsByProjection(new GetNumberOfSeatsRequest(viewModel.selectedProjectionProperty().getValue()));
        SeatsMatrixView seatsMatrixView;
        try {
            seatsMatrixView = new SeatsMatrixView();
            seatsMatrixView.load();
            seatsContainerVBox.getChildren().add(seatsMatrixView.getRoot());
            seatsMatrixView.getController().load(activity, navController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bind();
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
    }


    private void bind() {
        errorSectionLabel.textProperty().bind(viewModel.seatErrorProperty());

        confirmSeatButton.disableProperty().bind(viewModel.getGroup().selectedToggleProperty().isNull());
        seatsTotalText.textProperty().bind(viewModel.totalSeatsProperty());
        seatsFreeText.textProperty().bind(viewModel.freeSeatsProperty());
        seatsBuysText.textProperty().bind(viewModel.buysSeatsProperty());
        viewModel.foldingArmchairOptionProperty().bind(foldingArmchairRadioButton.selectedProperty());
        viewModel.heatedArmchairOptionProperty().bind(heatedArmchairRadioButton.selectedProperty());
        viewModel.skipLineOptionProperty().bind(skipLineRadioButton.selectedProperty());
    }

    private void changeLayoutRandom() throws IOException {
        activity.getUseCase(BuyTicketUseCase.class).confirmSeatsRandom();
        activity.getUseCase(BuyTicketUseCase.class).createTicket(
                new GetTicketBySeatsRequest(
                        viewModel.getSeatList(),
                        viewModel.selectedSeatsProperty().getValue(),
                        viewModel.selectedPositionSeatIntegerProperty().getValue(),
                        viewModel.foldingArmchairOptionProperty().getValue(),
                        viewModel.heatedArmchairOptionProperty().getValue(),
                        viewModel.skipLineOptionProperty().getValue()
                )
        );
        navController.navigate(new NavDestination(new PaymentView()));
    }

    private void changeLayoutSpecific() throws IOException {
        activity.getUseCase(BuyTicketUseCase.class).confirmSeatsSpecific();
        activity.getUseCase(BuyTicketUseCase.class).createTicket(
                new GetTicketBySeatsRequest(
                        viewModel.getSeatList(),
                        viewModel.selectedSeatsProperty().getValue(),
                        viewModel.selectedPositionSeatIntegerProperty().getValue(),
                        viewModel.foldingArmchairOptionProperty().getValue(),
                        viewModel.heatedArmchairOptionProperty().getValue(),
                        viewModel.skipLineOptionProperty().getValue()
                )
        );
        navController.navigate(new NavDestination(new PaymentView()));

    }
}



