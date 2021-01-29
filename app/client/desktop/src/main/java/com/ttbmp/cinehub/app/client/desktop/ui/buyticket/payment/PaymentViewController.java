package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.payment;


import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.CustomDateCell;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.chooseseat.ChooseSeatView;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.confirmemail.ConfirmEmailView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.request.PayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class PaymentViewController extends ViewController {


    private BuyTicketViewModel viewModel;
    @FXML
    private Button returnButton;
    @FXML
    private ProgressIndicator payProgressIndicator;
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
    private Label errorSectionLabel;

    @FXML
    private DatePicker fieldExpirationDatePicker;


    @Override
    protected void onLoad() {
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        bind();
        confirmButton.setOnAction(this::startPayment);
        returnButton.setOnAction(a -> {
            try {
                navController.navigate(new NavDestination(new ChooseSeatView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void bind() {
        confirmButton.disableProperty().bind(
                viewModel.emailUserProperty().isNull().
                        or(viewModel.nameUserProperty().isNull().
                                or(viewModel.surnameUserProperty().isNull().
                                        or(viewModel.txtCvvProperty().isNull().
                                                or(viewModel.numberOfCardUserProperty().isNull().
                                                        or(viewModel.fieldExpirationDatePickerProperty().isNull()))))));
        errorSectionLabel.textProperty().bind(viewModel.paymentErrorProperty());
        emailTextField.textProperty().bindBidirectional(viewModel.emailUserProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.nameUserProperty());
        surnameTextField.textProperty().bindBidirectional(viewModel.surnameUserProperty());
        numberOfCreditCardTextField.textProperty().bindBidirectional(viewModel.numberOfCardUserProperty());
        cvvTextField.textProperty().bindBidirectional(viewModel.txtCvvProperty());
        fieldExpirationDatePicker.valueProperty().bindBidirectional(viewModel.fieldExpirationDatePickerProperty());
        fieldExpirationDatePicker.setDayCellFactory(CustomDateCell::new);
    }


    private void startPayment(ActionEvent actionEvent) {
        if (activity.getUseCase(BuyTicketUseCase.class).pay(new PayRequest(
                viewModel.selectedTicketProperty().getValue(),
                viewModel.selectedProjectionProperty().getValue(),
                viewModel.seatSelectedPosition().getValue()))) {
            try {
                navController.navigate(new NavDestination(new ConfirmEmailView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

