package com.ttbmp.cinehub.ui.desktop.buyticket.payment;


import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.PaymentRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.buyticket.CustomDateCell;
import com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat.ChooseSeatView;
import com.ttbmp.cinehub.ui.desktop.buyticket.confirmemail.ConfirmEmailView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @author Ivan Palmieri
 */
public class PaymentViewController extends ViewController {

    private BuyTicketViewModel viewModel;

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private Button returnButton;

    @FXML
    private TextField numberOfCreditCardTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField cvvTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private DatePicker fieldExpirationDatePicker;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        bind();
        confirmButton.setOnAction(this::startPayment);
        returnButton.setOnAction(a -> navController.openView(ChooseSeatView.class));
    }


    private void bind() {
        confirmButton.disableProperty().bind(
                viewModel.emailUserProperty().isNull().
                        or(viewModel.cvvProperty().isNull().
                                or(viewModel.numberCardProperty().isNull().
                                        or(viewModel.expirationDateProperty().isNull())
                                )
                        )
        );
        errorLabel.visibleProperty().bind(viewModel.errorMessageProperty().isNotEmpty());
        emailTextField.textProperty().bindBidirectional(viewModel.emailUserProperty());
        numberOfCreditCardTextField.textProperty().bindBidirectional(viewModel.numberCardProperty());
        cvvTextField.textProperty().bindBidirectional(viewModel.cvvProperty());
        fieldExpirationDatePicker.valueProperty().bindBidirectional(viewModel.expirationDateProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.errorMessageProperty());
        fieldExpirationDatePicker.setDayCellFactory(CustomDateCell::new);
    }


    private void startPayment(ActionEvent actionEvent) {
        viewModel.errorMessageProperty().setValue(null);
        activity.getUseCase(BuyTicketUseCase.class).pay(new PaymentRequest(
                CinehubApplication.getSessionToken(),
                viewModel.selectedProjectionProperty().getValue().getId(),
                viewModel.selectedSeatProperty().getValue().getId(),
                viewModel.emailUserProperty().getValue(),
                viewModel.numberCardProperty().getValue(),
                viewModel.cvvProperty().getValue(),
                String.valueOf(fieldExpirationDatePicker.getValue()),
                viewModel.openBarOptionProperty().getValue(),
                viewModel.magicBoxOptionProperty().getValue(),
                viewModel.skipLineOptionProperty().getValue()
        ));
        if (viewModel.errorMessageProperty().getValue() == null) {
            navController.openView(ConfirmEmailView.class);
        } else {
            navController.openView(PaymentView.class);
        }
    }
}