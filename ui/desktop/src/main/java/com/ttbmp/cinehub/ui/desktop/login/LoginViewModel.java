package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.utilities.observer.Observable;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {

    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final BooleanProperty isLoginButtonDisabled = new SimpleBooleanProperty(false);
    private final BooleanProperty isLogged = new SimpleBooleanProperty(false);

    private final Observable<String> errorMessage = new Observable<>();

    public LoginViewModel() {
        isLoginButtonDisabled.bind(passwordProperty().isEmpty().or(emailProperty().isEmpty()));
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public boolean isIsLoginButtonDisabled() {
        return isLoginButtonDisabled.get();
    }

    public void setIsLoginButtonDisabled(boolean isLoginButtonDisabled) {
        this.isLoginButtonDisabled.set(isLoginButtonDisabled);
    }

    public BooleanProperty isLoginButtonDisabledProperty() {
        return isLoginButtonDisabled;
    }

    public boolean isIsLogged() {
        return isLogged.get();
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged.set(isLogged);
    }

    public BooleanProperty isLoggedProperty() {
        return isLogged;
    }

    public Observable<String> errorMessageProperty() {
        return errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage.getValue();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setValue(errorMessage);
    }

}
