package com.ttbmp.cinehub.app.ui.viewpersonalschedule;

import com.ttbmp.cinehub.app.CinehubApplication;
import com.ttbmp.cinehub.app.di.ViewPersonalScheduleContainer;
import com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar.CalendarDay;
import com.ttbmp.cinehub.app.ui.viewpersonalschedule.calendar.CalendarTableCell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Fabio Buracchi
 */
public class PersonalScheduleController {

    private final ViewPersonalScheduleContainer container = CinehubApplication.APP_CONTAINER.getContainer(
            ViewPersonalScheduleContainer.class
    );

    private final ViewPersonalScheduleViewModel viewModel = container.getViewModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button todayButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Map<DayOfWeek, CalendarDay>> calendarTableView;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @FXML
    void initialize() {
        assertProperInjection();
        datePicker.valueProperty().bindBidirectional(viewModel.dateProperty());
        datePicker.setOnAction(a -> container.getUseCase().getShiftList(viewModel.getCalendarPageFirstDate(), viewModel.getCalendarPageLastDate()));
        todayButton.setOnAction(a -> datePicker.setValue(LocalDate.now()));
        previousButton.setOnAction(a -> datePicker.setValue(datePicker.getValue().minusMonths(1)));
        nextButton.setOnAction(a -> datePicker.setValue(datePicker.getValue().plusMonths(1)));
        calendarTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        calendarTableView.getFocusModel().getFocusedCell();
        calendarTableView.setItems(viewModel.getShiftWeekList());
        calendarTableView.setSelectionModel(null);
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            TableColumn<Map<DayOfWeek, CalendarDay>, CalendarDay> dayTableColumn;
            dayTableColumn = (TableColumn<Map<DayOfWeek, CalendarDay>, CalendarDay>)
                    calendarTableView.getColumns().get(dayOfWeek.getValue() - 1);
            dayTableColumn.setCellValueFactory(new MapValueFactory(dayOfWeek));
            dayTableColumn.setCellFactory(CalendarTableCell::new);
            dayTableColumn.setReorderable(false);
            dayTableColumn.setResizable(false);
        }
        onLoad();
    }

    private void onLoad() {
        container.getUseCase().getShiftList(viewModel.getCalendarPageFirstDate(), viewModel.getCalendarPageLastDate());
    }

    private void assertProperInjection() {
        assert previousButton != null : "fx:id=\"previousButton\" was not injected: check your FXML file 'personal_schedule.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'personal_schedule.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'personal_schedule.fxml'.";
        assert calendarTableView != null : "fx:id=\"calendarTableView\" was not injected: check your FXML file 'personal_schedule.fxml'.";
    }

}
