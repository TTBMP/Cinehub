package com.ttbmp.cinehub.ui.desktop.login;


import com.ttbmp.cinehub.app.usecase.login.LoginRequest;
import com.ttbmp.cinehub.app.usecase.login.LoginUseCase;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class LoginViewController extends ViewController {

    private LoginViewModel viewModel;

    private LoginUseCase useCase;

    @FXML
    private Label errorSectionLabel;

    @FXML
    private Button loginButton;

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private TextField usernameLabel;

    @FXML
    private PasswordField passwordLabel;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(LoginViewModel.class);
        useCase = activity.getUseCase(LoginUseCase.class);
        viewModel.passwordUserProperty().bind(usernameLabel.textProperty());
        viewModel.usernameUserProperty().bind(passwordLabel.textProperty());
        errorSectionLabel.textProperty().bind(viewModel.accessErrorProperty());
        loginButton.disableProperty().bind(
                viewModel.passwordUserProperty().isEmpty()
                        .or(viewModel.usernameUserProperty().isEmpty())
        );
        loginButton.setOnAction(a -> {
                    useCase.login(new LoginRequest(
                            viewModel.passwordUserProperty().getValue(),
                            viewModel.usernameUserProperty().getValue()
                    ));
                    if (viewModel.accessErrorProperty().getValue().isEmpty()) {
                        try {
                            Activity prevActivity = navController.getPreviousDestinationActivityClass().getConstructor().newInstance();
                            navController.open(new NavActivityDestination(prevActivity));
                        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

}