package com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat;


import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.SeatListRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema.ChooseCinemaView;
import com.ttbmp.cinehub.ui.desktop.buyticket.payment.PaymentView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public class ChooseSeatViewController extends ViewController {

    private final ToggleGroup toggleGroup = new ToggleGroup();

    private BuyTicketViewModel viewModel;

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

    @FXML
    private Button returnButton;

    @FXML
    private Label errorLabel;

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
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        activity.getUseCase(BuyTicketUseCase.class).getSeatList(
                new SeatListRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.selectedProjectionProperty().getValue().getId()
                ));
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
        toggleGroup.selectedToggleProperty().addListener(l ->
                viewModel.selectedSeatProperty().setValue((SeatDto) toggleGroup.getSelectedToggle().getUserData())
        );
    }


    private void bind() {
        errorLabel.textProperty().bind(viewModel.errorMessageProperty());
        seatsTotalText.textProperty().bind(viewModel.totalSeatsProperty());
        seatsFreeText.textProperty().bind(viewModel.freeSeatsProperty());
        seatsBuysText.textProperty().bind(viewModel.buysSeatsProperty());
        viewModel.openBarOptionProperty().bind(foldingArmchairRadioButton.selectedProperty());
        viewModel.magicBoxOptionProperty().bind(heatedArmchairRadioButton.selectedProperty());
        viewModel.skipLineOptionProperty().bind(skipLineRadioButton.selectedProperty());
    }

    private void changeLayoutSpecific() throws IOException {
        viewModel.counterForToggleProperty().setValue(0);
        toggleGroup.getToggles().forEach(toggle -> findChosenPlace());
        navController.navigate(new NavDestination(new PaymentView()));

    }

    private void setAllElementsOfTheToggleToNull() {
        viewModel.seatStateProperty().add(viewModel.falseBooleanProperty());
        viewModel.counterForToggleProperty().setValue(viewModel.counterForToggleProperty().getValue() + 1);
    }


    private void findChosenPlace() {
        int i = viewModel.counterForToggleProperty().getValue();
        if (toggleGroup.getToggles().get(i).isSelected()) {
            viewModel.seatStateProperty().remove(i);
            viewModel.seatStateProperty().add(i, viewModel.trueBooleanProperty());
            viewModel.indexToggleSelectedProperty().setValue(i);
            viewModel.selectedSeatsNameProperty().setValue(
                    toggleGroup.getToggles().get(i).toString().substring(
                            toggleGroup.getToggles().get(i).toString().length() - 6,
                            toggleGroup.getToggles().get(i).toString().length() - 4
                    )
            );
            viewModel.seatSelectedPositionProperty().setValue(viewModel.indexToggleSelectedProperty().getValue());
        }
        viewModel.counterForToggleProperty().setValue(viewModel.counterForToggleProperty().getValue() + 1);

    }
}



