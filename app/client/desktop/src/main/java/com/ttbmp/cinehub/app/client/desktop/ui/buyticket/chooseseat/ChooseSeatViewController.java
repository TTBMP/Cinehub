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
import javafx.scene.control.ToggleGroup;
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
    ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        activity.getUseCase(BuyTicketUseCase.class).getListOfSeatsByProjection(new GetNumberOfSeatsRequest(viewModel.selectedProjectionProperty().getValue()));
        SeatsMatrixView seatsMatrixView;
        try {
            seatsMatrixView = new SeatsMatrixView(toggleGroup);
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
        toggleGroup.getToggles().forEach(toggle->function());
        errorSectionLabel.textProperty().bind(viewModel.seatErrorProperty());
        confirmSeatButton.disableProperty().bind(viewModel.getToggleState().iterator().next());
        seatsTotalText.textProperty().bind(viewModel.totalSeatsProperty());
        seatsFreeText.textProperty().bind(viewModel.freeSeatsProperty());
        seatsBuysText.textProperty().bind(viewModel.buysSeatsProperty());
        viewModel.foldingArmchairOptionProperty().bind(foldingArmchairRadioButton.selectedProperty());
        viewModel.heatedArmchairOptionProperty().bind(heatedArmchairRadioButton.selectedProperty());
        viewModel.skipLineOptionProperty().bind(skipLineRadioButton.selectedProperty());
    }


    private void changeLayoutRandom() throws IOException {
        viewModel.numberOfToggleProperty().setValue(0);
        toggleGroup.getToggles().forEach(toggle->function3());
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

    private void function3() {
        if(!(((RadioButton) toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue())).isDisabled())){
            viewModel.getToggleState().remove(viewModel.numberOfToggleProperty().getValue());
            viewModel.getToggleState().add(viewModel.numberOfToggleProperty().getValue(),viewModel.valueTrueForToggleStateProperty());
            viewModel.positionSelectedToggleProperty().setValue(viewModel.numberOfToggleProperty().getValue());
            viewModel.selectedSeatsProperty().setValue(
                    toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).toString().substring(
                            toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).toString().length() - 6,
                            toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).toString().length() - 4));
            viewModel.selectedPositionSeatIntegerProperty().setValue(viewModel.positionSelectedToggleProperty().getValue());
        }
        viewModel.numberOfToggleProperty().setValue(viewModel.numberOfToggleProperty().getValue()+1);

    }

    private void changeLayoutSpecific() throws IOException {
        viewModel.numberOfToggleProperty().setValue(0);
        toggleGroup.getToggles().forEach(toggle->function2());
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

    private void function() {
        viewModel.getToggleState().add(viewModel.valueFalseForToggleStateProperty());
        viewModel.numberOfToggleProperty().setValue(viewModel.numberOfToggleProperty().getValue()+1);
    }


    private void function2() {
        if(toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).isSelected()){
            viewModel.getToggleState().remove(viewModel.numberOfToggleProperty().getValue());
            viewModel.getToggleState().add(viewModel.numberOfToggleProperty().getValue(),viewModel.valueTrueForToggleStateProperty());
            viewModel.positionSelectedToggleProperty().setValue(viewModel.numberOfToggleProperty().getValue());
            viewModel.selectedSeatsProperty().setValue(
                    toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).toString().substring(
                            toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).toString().length() - 6,
                            toggleGroup.getToggles().get(viewModel.numberOfToggleProperty().getValue()).toString().length() - 4));
            viewModel.selectedPositionSeatIntegerProperty().setValue(viewModel.positionSelectedToggleProperty().getValue());
        }
        viewModel.numberOfToggleProperty().setValue(viewModel.numberOfToggleProperty().getValue()+1);


    }
}



