package com.ttbmp.cinehub.ui.desktop.login;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {

    private final StringProperty emailUser = new SimpleStringProperty();
    private final StringProperty passwordUser = new SimpleStringProperty();

    private final StringProperty accessError = new SimpleStringProperty();


    public StringProperty accessErrorProperty() {
        return accessError;
    }



    public StringProperty usernameUserProperty() {
        return emailUser;
    }

    public StringProperty passwordUserProperty() {
        return passwordUser;
    }

}
