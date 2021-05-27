package com.ttbmp.cinehub.ui.desktop.buyticket.confirmemail;


import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author Ivan Palmieri
 */
public class ConfirmEmailViewController extends ViewController {

    private BuyTicketViewModel viewModel;

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private Text ticketText;

    @FXML
    private Text cinemaText;


    @FXML
    private Text movieText;

    @FXML
    private Text numberCardText;

    @FXML
    private Text emailText;

    @FXML
    private Button confirmButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Text timeText;

    @FXML
    private Text priceText;

    @FXML
    private Label testLabel;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        confirmButton.setOnAction(a -> navController.navigate(new NavActivityDestination(new BuyTicketActivity())));
        bind();
        testLabel.setText(viewModel.selectedDateProperty().getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        priceText.setText((viewModel.selectedTicketPriceProperty().getValue()) + "\u20ac");
    }

    private void bind() {
        errorLabel.textProperty().bind(viewModel.errorMessageProperty());
        emailText.textProperty().bind(viewModel.emailUserProperty());
        numberCardText.textProperty().bind(viewModel.numberCardProperty());
        ticketText.textProperty().bind((viewModel.selectedSeatPositionProperty()));
        movieText.textProperty().bind(viewModel.selectedMovieNameProperty());
        cinemaText.textProperty().bind(viewModel.selectedCinemaNameProperty());
        timeText.textProperty().bind(ObjectBindings.map(viewModel.selectedProjectionProperty(), ProjectionDto::getStartTime));


    }

}
