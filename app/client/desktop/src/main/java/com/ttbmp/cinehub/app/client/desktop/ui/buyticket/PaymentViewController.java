package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;


import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;

import com.ttbmp.cinehub.core.datamapper.ProjectionDataMapper;
import com.ttbmp.cinehub.core.datamapper.TicketDataMapper;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class PaymentViewController extends ViewController {


    private BuyTicketViewModel viewModel;
    @FXML
    private Button returnButton;
    @FXML
    private TextField numberOfCreditCardTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField cvvTextField;

    @FXML
    private Text resultPaymentText;


    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        bind();
    }



    private void bind() {
        confirmButton.disableProperty().bind(viewModel.emailUserProperty().isNull());
        emailTextField.textProperty().bindBidirectional(viewModel.emailUserProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.nameUserProperty());
        surnameTextField.textProperty().bindBidirectional(viewModel.surnameUserProperty());
        numberOfCreditCardTextField.textProperty().bindBidirectional(viewModel.numberOfCardUserProperty());
        cvvTextField.textProperty().bindBidirectional(viewModel.txtCvvProperty());
        confirmButton.setOnAction(this::startPayment);
        returnButton.setOnAction(a -> {
            try {
                navController.navigate(new NavDestination(new ChooseSeatView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void startPayment(ActionEvent actionEvent) {
        if (activity.getUseCase(BuyTicketUseCase.class).pay(
                TicketDataMapper.mapToEntity(viewModel.selectedTicketProperty().getValue()),
                ProjectionDataMapper.mapToEntity(viewModel.selectedProjectionProperty().getValue()),
                viewModel.selectedPositionSeatIntegerProperty().getValue())) {
            try {
                navController.navigate(new NavDestination(new ConfirmEmailView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resultPaymentText.setText("Pagamento non riuscito, prova a cambiare i dati");
        }
    }



}

