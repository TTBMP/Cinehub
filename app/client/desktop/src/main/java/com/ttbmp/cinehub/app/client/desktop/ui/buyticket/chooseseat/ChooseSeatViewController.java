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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */


public class ChooseSeatViewController extends ViewController {


    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private VBox appBar;
    @FXML
    private AppBarViewController appBarController;
    @FXML
    private CheckBox foldingArmchairRadioButton;
    @FXML
    private CheckBox heatedArmchairRadioButton;
    @FXML
    private CheckBox skipLineRadioButton;
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
        activity.getUseCase(BuyTicketUseCase.class).getListOfSeat(new GetNumberOfSeatsRequest(viewModel.selectedProjectionProperty().getValue()));
        confirmSeatButton.setDisable(true);
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
        toggleGroup.getToggles().forEach(toggle ->
                setAllElementsOfTheToggleToNull()
        );
        toggleGroup.selectedToggleProperty().addListener(l -> confirmSeatButton.setDisable(false));
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
        seatsTotalText.textProperty().bind(viewModel.totalSeatsProperty());
        seatsFreeText.textProperty().bind(viewModel.freeSeatsProperty());
        seatsBuysText.textProperty().bind(viewModel.buysSeatsProperty());
        viewModel.foldingArmchairOptionProperty().bind(foldingArmchairRadioButton.selectedProperty());
        viewModel.heatedArmchairOptionProperty().bind(heatedArmchairRadioButton.selectedProperty());
        viewModel.skipLineOptionProperty().bind(skipLineRadioButton.selectedProperty());
    }


    private void changeLayoutRandom() throws IOException {
        viewModel.counterForToggle().setValue(0);
        toggleGroup.getToggles().forEach(toggle -> findRandomPlace());
        activity.getUseCase(BuyTicketUseCase.class).createTicket(
                new GetTicketBySeatsRequest(
                        viewModel.getSeatList(),
                        viewModel.selectedSeatsProperty().getValue(),
                        viewModel.seatSelectedPosition().getValue(),
                        viewModel.foldingArmchairOptionProperty().getValue(),
                        viewModel.heatedArmchairOptionProperty().getValue(),
                        viewModel.skipLineOptionProperty().getValue()
                )
        );
        navController.navigate(new NavDestination(new PaymentView()));
    }

    private void changeLayoutSpecific() throws IOException {
        viewModel.counterForToggle().setValue(0);
        toggleGroup.getToggles().forEach(toggle -> findChosenPlace());
        activity.getUseCase(BuyTicketUseCase.class).createTicket(
                new GetTicketBySeatsRequest(
                        viewModel.getSeatList(),
                        viewModel.selectedSeatsProperty().getValue(),
                        viewModel.seatSelectedPosition().getValue(),
                        viewModel.foldingArmchairOptionProperty().getValue(),
                        viewModel.heatedArmchairOptionProperty().getValue(),
                        viewModel.skipLineOptionProperty().getValue()
                )
        );
        navController.navigate(new NavDestination(new PaymentView()));

    }

    private void setAllElementsOfTheToggleToNull() {
        viewModel.getSeatsState().add(viewModel.falseBooleanProperty());
        viewModel.counterForToggle().setValue(viewModel.counterForToggle().getValue() + 1);
    }

    private void findRandomPlace() {
        int j = viewModel.counterForToggle().getValue();
        if (!(((RadioButton) toggleGroup.getToggles().get(j)).isDisabled())) {
            viewModel.getSeatsState().remove(j);
            viewModel.getSeatsState().add(j, viewModel.trueBooleanProperty());
            viewModel.indexToggleSelected().setValue(j);
            viewModel.selectedSeatsProperty().setValue(
                    toggleGroup.getToggles().get(j).toString().substring(
                            toggleGroup.getToggles().get(j).toString().length() - 6,
                            toggleGroup.getToggles().get(j).toString().length() - 4));
            viewModel.seatSelectedPosition().setValue(viewModel.indexToggleSelected().getValue());
        }
        viewModel.counterForToggle().setValue(viewModel.counterForToggle().getValue() + 1);

    }


    private void findChosenPlace() {
        int i = viewModel.counterForToggle().getValue();
        if (toggleGroup.getToggles().get(i).isSelected()) {
            viewModel.getSeatsState().remove(i);
            viewModel.getSeatsState().add(i, viewModel.trueBooleanProperty());
            viewModel.indexToggleSelected().setValue(i);
            viewModel.selectedSeatsProperty().setValue(
                    toggleGroup.getToggles().get(i).toString().substring(
                            toggleGroup.getToggles().get(i).toString().length() - 6,
                            toggleGroup.getToggles().get(i).toString().length() - 4
                    )
            );
            viewModel.seatSelectedPosition().setValue(viewModel.indexToggleSelected().getValue());
        }
        viewModel.counterForToggle().setValue(viewModel.counterForToggle().getValue() + 1);

    }
}



