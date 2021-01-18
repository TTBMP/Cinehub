package com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule;

import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarController;
import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.calendar.CalendarDay;
import com.ttbmp.cinehub.app.client.desktop.ui.viewpersonalschedule.calendar.CalendarTableCell;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Controller;
import com.ttbmp.cinehub.core.usecase.ViewPersonalScheduleUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class PersonalScheduleController extends Controller {

    @FXML
    private VBox appBar;

    @FXML
    private AppBarController appBarController;

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

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void onLoad() {
        appBarController.initialize(activity, navController);
        ViewPersonalScheduleViewModel viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        datePicker.valueProperty().bindBidirectional(viewModel.dateProperty());
        datePicker.setOnAction(a -> activity.getUseCase(ViewPersonalScheduleUseCase.class).getShiftList(
                viewModel.getCalendarPageFirstDate(),
                viewModel.getCalendarPageLastDate())
        );
        todayButton.setOnAction(a -> datePicker.setValue(LocalDate.now()));
        previousButton.setOnAction(a -> datePicker.setValue(datePicker.getValue().minusMonths(1)));
        nextButton.setOnAction(a -> datePicker.setValue(datePicker.getValue().plusMonths(1)));
        calendarTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        calendarTableView.getFocusModel().getFocusedCell();
        calendarTableView.setItems(viewModel.getShiftWeekList());
        calendarTableView.setSelectionModel(null);
        calendarTableView.minHeightProperty().bind(calendarTableView.widthProperty());
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            TableColumn<Map<DayOfWeek, CalendarDay>, CalendarDay> dayTableColumn;
            dayTableColumn = (TableColumn<Map<DayOfWeek, CalendarDay>, CalendarDay>)
                    calendarTableView.getColumns().get(dayOfWeek.getValue() - 1);
            dayTableColumn.setCellValueFactory(new MapValueFactory(dayOfWeek));
            dayTableColumn.setCellFactory(tableColumn -> new CalendarTableCell(tableColumn, activity, navController));
            dayTableColumn.setReorderable(false);
            dayTableColumn.setResizable(false);
            dayTableColumn.prefWidthProperty().bind(calendarTableView.widthProperty().divide(
                    calendarTableView.getColumns().size())
            );
        }
        activity.getUseCase(ViewPersonalScheduleUseCase.class).getShiftList(
                viewModel.getCalendarPageFirstDate(),
                viewModel.getCalendarPageLastDate()
        );
    }

}
