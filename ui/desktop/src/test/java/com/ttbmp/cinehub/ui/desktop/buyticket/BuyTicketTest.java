package com.ttbmp.cinehub.ui.desktop.buyticket;

import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ApplicationExtension.class})
class BuyTicketTest {

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
    void buyTicketOnNextButtonClick_dateIsNextDayDate(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#nextButton");
        FxAssert.verifyThat("#dateOfProjectionDatePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now().plusDays(1)));
    }

    @Test
    void buyTicketOnPreviousButtonClick_dateIsPreviousDayDate(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#nextButton");
        robot.clickOn("#previousButton");
        FxAssert.verifyThat("#dateOfProjectionDatePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now()));
    }

    @Test
    void buyTicketOnTodayButtonClick_dateIsToday(FxRobot robot) {
        logInAsProjectionist(robot);
        for(int i=0; i<3;i++) {
            robot.clickOn("#nextButton");
        }
        robot.clickOn("#todayButton");
        FxAssert.verifyThat("#dateOfProjectionDatePicker", (DatePicker datePicker) -> datePicker.getValue().equals(LocalDate.now()));
    }

    @Test
    void buyTicketOnContinueEnabledButtonClick_buttonIsEnabled(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#movieListView");
        FxAssert.verifyThat("#confirmMovieButton", (Button button) -> !button.isDisable());
    }

    @Test
    void buyTicketOnContinueDisabledButtonClick_buttonIsDisabled(FxRobot robot) {
        logInAsProjectionist(robot);
        FxAssert.verifyThat("#confirmMovieButton", (Predicate<Button>) Node::isDisable);
    }

    @Test
    void buyTicketOnTimeListView_loadCorrectList(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#movieListView");
        robot.clickOn("#confirmMovieButton");
        robot.clickOn("#cinemaListView");
        FxAssert.verifyThat("#timeOfProjectionListView", (ListView listView) -> !listView.getItems().isEmpty());
    }

    @Test
    void buyTicketOnTimeListView_loadIncorrectList(FxRobot robot) {
        logInAsProjectionist(robot);
        robot.clickOn("#movieListView");
        robot.clickOn("#confirmMovieButton");
        FxAssert.verifyThat("#timeOfProjectionListView", (ListView listView) -> listView.getItems().isEmpty());
    }

    private void logInAsProjectionist(FxRobot robot) {
        robot.clickOn("#loginTab");
        robot.clickOn("#emailTextField");
        robot.write("ip@cinehub.com");
        robot.clickOn("#passwordField");
        robot.write("a");
        robot.clickOn("#loginButton");
    }

}