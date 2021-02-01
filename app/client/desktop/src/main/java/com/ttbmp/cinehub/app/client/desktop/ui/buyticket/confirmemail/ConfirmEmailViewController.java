package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.confirmemail;


import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.core.dto.ProjectionDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author Palmieri Ivan
 */


public class ConfirmEmailViewController extends ViewController {

    private BuyTicketViewModel viewModel;

    @FXML
    private Text surnameText;
    @FXML
    private Text ticketText;
    @FXML
    private Text cinemaText;
    @FXML
    private Text nameText;
    @FXML
    private Text movieText;
    @FXML
    private Text numberCardText;
    @FXML
    private Text emailText;
    @FXML
    private Button confirmButton;
    @FXML
    private Label errorSectionLabel;
    @FXML
    private Text timeText;
    @FXML
    private Text priceText;
    @FXML
    private Label testLabel;

    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        confirmButton.setOnAction(a -> {
            try {
                navController.navigate(new NavActivityDestination(new BuyTicketActivity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bind();
        testLabel.setText(viewModel.selectedDateProperty().getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        priceText.setText((viewModel.selectedTicketPriceProperty().getValue()) + "\u20ac");
    }


    private void bind() {
        errorSectionLabel.textProperty().bind(viewModel.emailErrorProperty());
        emailText.textProperty().bind(viewModel.emailUserProperty());
        nameText.textProperty().bind(viewModel.nameUserProperty());
        surnameText.textProperty().bind(viewModel.surnameUserProperty());
        numberCardText.textProperty().bind(viewModel.numberOfCardUserProperty());
        ticketText.textProperty().bind((viewModel.selectedTicketPositionProperty()));
        movieText.textProperty().bind(viewModel.selectedMovieNameProperty());
        cinemaText.textProperty().bind(viewModel.selectedCinemaNameProperty());
        timeText.textProperty().bind(ObjectBindings.map(viewModel.selectedProjectionProperty(), ProjectionDto::getStartTime));


    }


}
