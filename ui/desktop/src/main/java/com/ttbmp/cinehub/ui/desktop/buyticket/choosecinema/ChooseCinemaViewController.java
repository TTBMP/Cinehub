package com.ttbmp.cinehub.ui.desktop.buyticket.choosecinema;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.ProjectionListRequest;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.appbar.AppBarViewController;
import com.ttbmp.cinehub.ui.desktop.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie.ChooseMovieView;
import com.ttbmp.cinehub.ui.desktop.buyticket.chooseseat.ChooseSeatView;
import com.ttbmp.cinehub.ui.desktop.login.LoginActivity;
import com.ttbmp.cinehub.ui.desktop.login.LoginView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavActivityDestination;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
    private Label errorLabel;

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
        timeOfProjectionListView.getSelectionModel().selectedItemProperty().addListener(l -> onTimeSelected());
        errorLabel.textProperty().bind(viewModel.errorMessageProperty());
        viewModel.selectedCinemaProperty().bind(cinemaListView.getSelectionModel().selectedItemProperty());
        cinemaListView.setItems(viewModel.cinemaListProperty());
        cinemaListView.setCellFactory(listCinemaDto -> new ChooseCinemaListCell(activity, navController));//Cell Factory
        timeOfProjectionListView.setCellFactory(l -> new ChooseProjectionListCell(activity, navController));
        cinemaListView.getSelectionModel().selectedItemProperty().addListener(l -> {
            onCinemaItemClick();
        });
        cancelButton.setOnAction(a -> {
            try {
                timeOfProjectionListView.getItems().clear();
                navController.navigate(new NavDestination(new ChooseMovieView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        confirmCinemaButton.setOnAction(a -> {
            viewModel.errorMessageProperty().setValue(null);
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

    private void onCinemaItemClick(){
        if (viewModel.selectedCinemaProperty().getValue() != null) {
            viewModel.timeOfProjectionListProperty().clear();
            activity.getUseCase(BuyTicketUseCase.class).getProjectionList(new ProjectionListRequest(
                    viewModel.selectedMovieProperty().getValue().getId(),
                            viewModel.selectedCinemaProperty().getValue().getId(),
                            viewModel.selectedDateProperty().getValue()
                    )
            );
            timeOfProjectionListView.setItems(viewModel.projectionTimeListProperty());
        }
    }

}

