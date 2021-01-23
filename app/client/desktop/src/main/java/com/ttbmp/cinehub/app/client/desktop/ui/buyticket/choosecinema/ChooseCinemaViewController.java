package com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosecinema;


import com.ttbmp.cinehub.app.client.desktop.ui.appbar.AppBarViewController;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.ChooseSeatView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.navigation.NavDestination;
import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.MovieDataMapper;
import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class ChooseCinemaViewController extends ViewController {

    private BuyTicketViewModel viewModel;


    @FXML
    private VBox appBar;

    @FXML
    private AppBarViewController appBarController;

    @FXML
    private ListView<CinemaDto> cinemaListView;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmCinemaButton;

    @FXML
    private ListView<String> timeOfProjectionListView;

    @Override
    protected void onLoad() {
        appBarController.load(activity, navController);
        viewModel = activity.getViewModel(BuyTicketViewModel.class);
        cancelButton.setOnAction(a -> {
            try {
                navController.popBackStack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        confirmCinemaButton.setOnAction(a -> {
            activity.getUseCase(BuyTicketUseCase.class).setProjection(//Creazione di più bean
                    CinemaDataMapper.mapToEntity(viewModel.selectedCinemaProperty().getValue()),
                    MovieDataMapper.mapToEntity(viewModel.selectedMovieProperty().getValue()),
                    viewModel.selectedTimeProperty().getValue()
            );
            try {
                navController.navigate(new NavDestination(new ChooseSeatView()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cinemaListView.setItems(viewModel.getCinemaList());
        timeOfProjectionListView.setItems(viewModel.getTimeOfProjectionList());
        viewModel.getTimeOfProjectionList().clear();
        bind();
        onAction();
        cinemaListView.setCellFactory(listCinemaDto->new ChooseCinemaListCell(listCinemaDto,activity,navController));//Cell Factory
    }


    private void onAction() {
        cinemaListView.getSelectionModel().selectedItemProperty().addListener((ov) ->
                activity.getUseCase(BuyTicketUseCase.class).getTimeOfProjection(
                        MovieDataMapper.mapToEntity(
                                viewModel.selectedMovieProperty().getValue()),
                        CinemaDataMapper.mapToEntity(
                                viewModel.selectedCinemaProperty().getValue())
                ));

    }

    private void bind() {
        confirmCinemaButton.disableProperty().bind(viewModel.selectedCinemaProperty().isNull());
        confirmCinemaButton.disableProperty().bind(viewModel.selectedTimeProperty().isNull());
        viewModel.selectedTimeProperty().bind(timeOfProjectionListView.getSelectionModel().selectedItemProperty());
        viewModel.selectedCinemaProperty().bind(cinemaListView.getSelectionModel().selectedItemProperty());
    }

}

