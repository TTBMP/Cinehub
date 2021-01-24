package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.payment;


import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.chooseseat.ChooseSeatView;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.confirmemail.ConfirmEmailView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.PayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private DatePicker fieldExpirationDatePicker;

    @FXML
    private Text resultPaymentText;


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

        emailTextField.textProperty().bindBidirectional(viewModel.emailUserProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.nameUserProperty());
        surnameTextField.textProperty().bindBidirectional(viewModel.surnameUserProperty());
        numberOfCreditCardTextField.textProperty().bindBidirectional(viewModel.numberOfCardUserProperty());
        cvvTextField.textProperty().bindBidirectional(viewModel.txtCvvProperty());
        fieldExpirationDatePicker.valueProperty().bindBidirectional(viewModel.fieldExpirationDatePickerProperty());
    }


    private void startPayment(ActionEvent actionEvent) {
        if (activity.getUseCase(BuyTicketUseCase.class).pay(new PayRequest(
                viewModel.selectedTicketProperty().getValue(),
                viewModel.selectedProjectionProperty().getValue(),
                viewModel.selectedPositionSeatIntegerProperty().getValue()))) {
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
