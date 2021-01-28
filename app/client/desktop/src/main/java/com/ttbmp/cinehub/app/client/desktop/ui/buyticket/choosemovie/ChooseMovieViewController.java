package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosemovie;


import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarViewController;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.CustomDateCell;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema.ChooseCinemaView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.request.GetListCinemaRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDate;


/**
 * @author Palmieri Ivan
 */
public class ChooseMovieViewController extends ViewController {

    private BuyTicketViewModel viewModel;


    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private ImageView theMovieDbLogoImageView;
    @FXML
    private Button todayButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;
    @FXML
    private Label errorSectionLabel;
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
        movieListView.setItems(viewModel.getMovieList());
        bind();
        activity.getUseCase(BuyTicketUseCase.class).getListMovie();
        dateOfProjectionDatePicker.setValue(LocalDate.now());
        movieListView.setCellFactory(movieList -> new ChooseMovieListCell(activity, navController));
        dateOfProjectionDatePicker.setOnAction(a -> activity.getUseCase(BuyTicketUseCase.class).getListMovie());
        dateOfProjectionDatePicker.setDayCellFactory(CustomDateCell::new);
        confirmMovieButton.setOnAction(a -> {
            try {
                activity.getUseCase(BuyTicketUseCase.class).getListCinema(new GetListCinemaRequest(viewModel.selectedMovieProperty().getValue(), viewModel.selectedDateProperty().getValue().toString()));
                navController.navigate(new NavDestination(new ChooseCinemaView()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        todayButton.setOnAction(a -> dateOfProjectionDatePicker.setValue(LocalDate.now()));
        previousButton.setOnAction(a -> dateOfProjectionDatePicker.setValue(dateOfProjectionDatePicker.getValue().minusDays(1)));
        nextButton.setOnAction(a -> dateOfProjectionDatePicker.setValue(dateOfProjectionDatePicker.getValue().plusDays(1)));

    }

    private void bind() {
        errorSectionLabel.textProperty().bind(viewModel.movieErrorProperty());
        confirmMovieButton.disableProperty().bind(viewModel.selectedDateProperty().isNull().or(viewModel.selectedMovieProperty().isNull()));
        viewModel.selectedMovieProperty().bind(movieListView.getSelectionModel().selectedItemProperty());
        dateOfProjectionDatePicker.valueProperty().bindBidirectional(viewModel.selectedDateProperty());

    }


}
