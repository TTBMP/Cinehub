package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.usecase.login.LoginRequest;
import com.ttbmp.cinehub.app.usecase.login.LoginUseCase;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginViewController extends ViewController {

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        var viewModel = activity.getViewModel(LoginViewModel.class);
        var useCase = activity.getUseCase(LoginUseCase.class);
        viewModel.emailProperty().bind(emailTextField.textProperty());
        viewModel.passwordProperty().bind(passwordField.textProperty());
        loginButton.disableProperty().bind(viewModel.isLoginButtonDisabledProperty());
        loginButton.setOnAction(a -> useCase.login(new LoginRequest(viewModel.getEmail(), viewModel.getPassword())));
        viewModel.isLoggedProperty().addListener(l -> navController.replaceWithPreviousActivity());
        viewModel.errorMessageProperty().addObserver(message -> navController.openErrorDialog(message, false));
    }

}
