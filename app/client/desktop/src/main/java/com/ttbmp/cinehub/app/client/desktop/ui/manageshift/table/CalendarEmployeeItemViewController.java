package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;

import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavController;
import com.ttbmp.cinehub.core.dto.EmployeeDto;
import com.ttbmp.cinehub.core.dto.ProjectionistDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Massimo Mazzetti
 */
public class CalendarEmployeeItemViewController extends ViewController {

    EmployeeDto employee;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView employeeImageView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label roleLabel;

    public void load(Activity activity, NavController navController, EmployeeDto employee) {
        this.employee = employee;
        load(activity, navController);
    }

    @Override
    protected void onLoad() {
        employeeImageView.setImage(new Image("drawable/employee.jpg"));

        nameLabel.setText(employee.getName());
        surnameLabel.setText(employee.getSurname());
        if (employee instanceof ProjectionistDto) {
            roleLabel.setText("Projectionist");
        } else {
            roleLabel.setText("Usher");
        }
    }


}