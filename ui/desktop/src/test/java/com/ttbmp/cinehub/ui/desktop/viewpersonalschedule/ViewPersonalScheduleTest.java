package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule;

import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketActivity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
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

@ExtendWith({ApplicationExtension.class})
class ViewPersonalScheduleTest {

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
    void viewPersonalScheduleTabOnUnauthenticatedSession_isNotClickable(FxRobot robot) {
        Assertions.assertThrows(FxRobotException.class, () -> robot.clickOn("#viewPersonalScheduleTab"));
    }

    @Test
    void viewPersonalScheduleTabOnProjectionistSession_isVisible(FxRobot robot) {
        logInAsProjectionist(robot);
        FxAssert.verifyThat("#viewPersonalScheduleTab", Node::isVisible);
    }

    @Test
    void viewPersonalScheduleDefaultDate_isTodayDate(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#viewPersonalScheduleTab");
        FxAssert.verifyThat("#datePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now()));
    }

    @Test
    void viewPersonalScheduleOnNextButtonClick_dateIsNextMonthDate(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#viewPersonalScheduleTab");
        robot.clickOn("#nextButton");
        FxAssert.verifyThat("#datePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now().plusMonths(1)));
    }

    @Test
    void viewPersonalScheduleOnPreviousButtonClick_dateIsPreviousMonthDate(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#viewPersonalScheduleTab");
        robot.clickOn("#previousButton");
        FxAssert.verifyThat("#datePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now().minusMonths(1)));
    }

    private void logInAsProjectionist(FxRobot robot) {
        robot.clickOn("#loginTab");
        robot.clickOn("#emailTextField");
        robot.write("fb@cinehub.com");
        robot.clickOn("#passwordField");
        robot.write("asdfghjkl");
        robot.clickOn("#loginButton");
    }

}
