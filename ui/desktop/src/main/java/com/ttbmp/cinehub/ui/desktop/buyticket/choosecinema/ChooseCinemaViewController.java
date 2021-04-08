package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie.ChooseMovieView;
import com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat.ChooseSeatView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Ivan Palmieri
 */
public class ChooseCinemaViewController extends ViewController {

    private BuyTicketViewModel viewModel;

    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private Label errorSectionLabel;

    @FXML
    private ListView<CinemaDto> cinemaListView;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmCinemaButton;

    @FXML
    private ListView<ProjectionDto> timeOfProjectionListView;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        viewModel.selectedProjectionProperty().setValue(null);
        confirmCinemaButton.disableProperty().bind(viewModel.selectedProjectionProperty().isNull());
        timeOfProjectionListView.getSelectionModel().selectedItemProperty().addListener(l ->
                onTimeSelected());
        cinemaListView.getSelectionModel().selectedItemProperty().addListener(l -> onCinemaItemClick());
        errorSectionLabel.textProperty().bind(viewModel.cinemaErrorProperty());
        viewModel.selectedCinemaProperty().bind(cinemaListView.getSelectionModel().selectedItemProperty());
        cinemaListView.setItems(viewModel.getCinemaList());
        cinemaListView.setCellFactory(listCinemaDto -> new ChooseCinemaListCell(activity, navController));//Cell Factory
        timeOfProjectionListView.setCellFactory(l -> new ChooseProjectionListCell(activity, navController));
        cancelButton.setOnAction(a -> {
            try {
                timeOfProjectionListView.getItems().clear();
                navController.navigate(new NavDestination(new ChooseMovieView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        confirmCinemaButton.setOnAction(a -> {
            viewModel.cinemaErrorProperty().setValue(null);
            try {
                navController.navigate(new NavDestination(new ChooseSeatView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void onTimeSelected() {
        if (timeOfProjectionListView.getSelectionModel().selectedItemProperty().getValue() != null) {
            viewModel.selectedProjectionProperty().setValue(timeOfProjectionListView.getSelectionModel().selectedItemProperty().getValue());
        }
    }

    private void onCinemaItemClick() {
        if (viewModel.selectedCinemaProperty().getValue() != null) {
            viewModel.getTimeOfProjectionList().clear();
            activity.getUseCase(BuyTicketUseCase.class).getProjectionList(new GetProjectionRequest(
                            viewModel.selectedMovieProperty().getValue().getId(),
                            viewModel.selectedCinemaProperty().getValue().getId(),
                            viewModel.selectedDateProperty().getValue()
                    )
            );

            timeOfProjectionListView.setItems(viewModel.getProjectionOfProjectionTimeList());
        }
    }

}

