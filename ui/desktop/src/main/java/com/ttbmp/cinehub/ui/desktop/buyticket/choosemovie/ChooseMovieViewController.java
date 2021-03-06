package com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.MovieListRequest;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.buyticket.CustomDateCell;
import com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema.ChooseCinemaView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class ChooseMovieViewController extends ViewController {

    private BuyTicketViewModel viewModel;

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private Label errorLabel;

    @FXML
    private Button todayButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private ListView<MovieDto> movieListView;

    @FXML
    private Button confirmMovieButton;

    @FXML
    private DatePicker dateOfProjectionDatePicker;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        activity.getUseCase(BuyTicketUseCase.class).getMovieList(new MovieListRequest(
                viewModel.selectedDateProperty().getValue()
        ));
        movieListView.itemsProperty().addListener(l -> movieListView.refresh());
        movieListView.setItems(viewModel.getMovieList());
        movieListView.setCellFactory(movieList -> new ChooseMovieListCell(activity, navController));
        bind();
        dateOfProjectionDatePicker.setValue(LocalDate.now());
        dateOfProjectionDatePicker.setDayCellFactory(CustomDateCell::new);
        dateOfProjectionDatePicker.setOnAction(l -> onDataChange());
        todayButton.setOnAction(a -> dateOfProjectionDatePicker.setValue(LocalDate.now()));
        previousButton.setOnAction(a -> dateOfProjectionDatePicker.setValue(dateOfProjectionDatePicker.getValue().minusDays(1)));
        nextButton.setOnAction(a -> dateOfProjectionDatePicker.setValue(dateOfProjectionDatePicker.getValue().plusDays(1)));
        previousButton.setDisable(true);
        confirmMovieButton.setOnAction(a -> {
            activity.getUseCase(BuyTicketUseCase.class).getCinemaList(new CinemaListRequest(
                    viewModel.selectedMovieProperty().getValue().getId(),
                    viewModel.selectedDateProperty().getValue().toString()
            ));
            navController.openView(ChooseCinemaView.class);
        });
    }

    private void onDataChange() {
        activity.getUseCase(BuyTicketUseCase.class).getMovieList(new MovieListRequest(
                viewModel.selectedDateProperty().getValue()
        ));
        previousButton.setDisable(viewModel.selectedDateProperty().getValue().equals(LocalDate.now()));
    }

    private void bind() {
        errorLabel.textProperty().bind(viewModel.errorMessageProperty());
        confirmMovieButton.disableProperty().bind(viewModel.selectedDateProperty().isNull().or(viewModel.selectedMovieProperty().isNull()));
        viewModel.selectedMovieProperty().bind(movieListView.getSelectionModel().selectedItemProperty());
        dateOfProjectionDatePicker.valueProperty().bindBidirectional(viewModel.selectedDateProperty());
    }

}
