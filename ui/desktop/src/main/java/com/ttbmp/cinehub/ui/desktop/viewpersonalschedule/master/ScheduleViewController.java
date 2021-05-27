package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ShiftListRequest;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleUseCase;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.ViewPersonalScheduleViewModel;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.CalendarDay;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.calendar.tablecell.CalendarTableCell;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class ScheduleViewController extends ViewController {

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

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
    protected void onLoad() {
        appBarController.load(activity, navController);
        var viewModel = activity.getViewModel(ViewPersonalScheduleViewModel.class);
        datePicker.valueProperty().bindBidirectional(viewModel.dateProperty());
        datePicker.setOnAction(a -> activity.getUseCase(ViewPersonalScheduleUseCase.class).getShiftList(
                new ShiftListRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.getCalendarPageFirstDate(),
                        viewModel.getCalendarPageLastDate())
                )
        );
        todayButton.setOnAction(a -> datePicker.setValue(LocalDate.now()));
        previousButton.setOnAction(a -> datePicker.setValue(datePicker.getValue().minusMonths(1)));
        nextButton.setOnAction(a -> datePicker.setValue(datePicker.getValue().plusMonths(1)));
        calendarTableView.minHeightProperty().bind(calendarTableView.widthProperty());
        calendarTableView.addEventFilter(ScrollEvent.ANY, Event::consume);
        calendarTableView.getFocusModel().getFocusedCell();
        calendarTableView.setSelectionModel(null);
        calendarTableView.itemsProperty().addListener(l -> calendarTableView.refresh());
        calendarTableView.setItems(viewModel.getShiftWeekList());
        for (var dayOfWeek : DayOfWeek.values()) {
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
                new ShiftListRequest(
                        CinehubApplication.getSessionToken(),
                        viewModel.getCalendarPageFirstDate(),
                        viewModel.getCalendarPageLastDate())
        );
        viewModel.errorMessageProperty().addObserver(message -> navController.openErrorDialog(message, false));
    }

}
