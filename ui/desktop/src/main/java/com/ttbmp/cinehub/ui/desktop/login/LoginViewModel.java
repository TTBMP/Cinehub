package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.app.utilities.observer.Observable;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Value;

@Value
public class LoginViewModel implements ViewModel {

    StringProperty emailProperty = new SimpleStringProperty();
    StringProperty passwordProperty = new SimpleStringProperty();
    BooleanProperty loginButtonDisableProperty = new SimpleBooleanProperty(false);
    BooleanProperty loggedProperty = new SimpleBooleanProperty(false);

    Observable<String> errorMessageProperty = new Observable<>();

    public LoginViewModel() {
        loginButtonDisableProperty.bind(passwordProperty().isEmpty().or(emailProperty().isEmpty()));
    }

}
