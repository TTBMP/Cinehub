package com.ttbmp.cinehub.ui.desktop.manageshift;

import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotException;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import java.time.LocalDate;
import java.util.function.Predicate;


@ExtendWith({ApplicationExtension.class})
class ManageEmployeeShiftTest {

    @Start
    void onStart(Stage stage) {
        new NavController(stage).navigate(new NavActivityDestination(new BuyTicketActivity()));
        stage.show();
    }

    @AfterEach
    void cleanState() {
        CinehubApplication.setSessionToken(null);
    }

    @Test
    void manageEmployeeShiftTabOnUnauthenticatedSession_isNotClickable(FxRobot robot) {
        Assertions.assertThrows(FxRobotException.class, () -> robot.clickOn("#manageShiftTab"));
    }

    @Test
    void manageEmployeeShiftTabOnProjectionistSession_isVisible(FxRobot robot) {
        logInAsManager(robot);
        FxAssert.verifyThat("#manageShiftTab", Node::isVisible);
    }

    @Test
    void manageEmployeeShiftDefaultDate_isTodayDate(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        FxAssert.verifyThat("#periodDatePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now()));
    }

    @Test
    void manageEmployeeShiftOnNextButtonClick_dateIsNextMonthDate(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#nextButton");
        FxAssert.verifyThat("#periodDatePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now().plusWeeks(1)));
    }

    @Test
    void manageEmployeeShiftOnPreviousButtonClick_dateIsPreviousMonthDate(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#previousButton");
        FxAssert.verifyThat("#periodDatePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now().minusWeeks(1)));
    }

    @Test
    void assignShiftDuplicated_showErrorMessage(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#nextButton");
        robot.clickOn("#addButton");
        robot.clickOn("#confirmButton");
        FxAssert.verifyThat("#errorLabel", (Label string) -> string.textProperty().getValue().equals("Shift Already Exist"));
        robot.push(KeyCode.ALT, KeyCode.F4);
        robot.clickOn("#cancelButton");
    }

    @Test
    void showShift_ModifyButtonIsDisabled(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#previousButton");
        robot.clickOn("#shiftHBox");
        FxAssert.verifyThat("#modifyShiftButton", (Predicate<Button>) Node::isDisable);
        robot.push(KeyCode.ALT, KeyCode.F4);
    }

    @Test
    void showShift_ModifyButtonIsEnabled(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#nextButton");
        robot.clickOn("#shiftHBox");
        FxAssert.verifyThat("#modifyShiftButton", (Button button)-> !button.isDisable());
        robot.push(KeyCode.ALT, KeyCode.F4);
    }

    @Test
    void showShift_DeleteButtonIsDisabled(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#previousButton");
        robot.clickOn("#shiftHBox");
        FxAssert.verifyThat("#deleteShiftButton", (Predicate<Button>) Node::isDisable);
        robot.push(KeyCode.ALT, KeyCode.F4);
    }

    @Test
    void showShift_DeleteButtonIsEnabled(FxRobot robot) {
        logInAsManager(robot);
        robot.clickOn("#manageShiftTab");
        robot.clickOn("#nextButton");
        robot.clickOn("#shiftHBox");
        FxAssert.verifyThat("#deleteShiftButton", (Button button)-> !button.isDisable());
        robot.push(KeyCode.ALT, KeyCode.F4);
    }


    private void logInAsManager(FxRobot robot) {
        robot.clickOn("#loginTab");
        robot.clickOn("#emailTextField");
        robot.write("mz@cinehub.com");
        robot.clickOn("#passwordField");
        robot.write("1234");
        robot.clickOn("#loginButton");
    }

}