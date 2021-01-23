package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;


import com.ttbmp.cinehub.core.datamapper.*;
import com.ttbmp.cinehub.core.entity.*;

import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketPresenter;
import javafx.scene.control.RadioButton;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterFx implements BuyTicketPresenter {


    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentMovieApiList(List<Movie> listMovieName) {
        viewModel.getMovieList().clear();
        if (viewModel.selectedDateProperty().getValue().equals(LocalDate.now())) {
            viewModel.getMovieList().addAll(MovieDataMapper.mapToDtoList(listMovieName.subList(0, 5)));
        } else {
            viewModel.getMovieList().addAll(MovieDataMapper.mapToDtoList(listMovieName.subList(5, 10)));
        }
    }

    @Override
    public void presentCinemaList(List<Cinema> cinemaByMovie) {
        viewModel.getCinemaList().clear();
        viewModel.getCinemaList().addAll(CinemaDataMapper.mapToDtoList(cinemaByMovie));
    }

    @Override
    public void presentTimeList(List<String> timeOfProjectionList) {
        viewModel.getTimeOfProjectionList().clear();
        viewModel.getTimeOfProjectionList().addAll(timeOfProjectionList);
    }

    @Override
    public void presentSeatList(List<Seat> listSeat) {
        viewModel.getSeatList().clear();
        viewModel.getSeatList().addAll(SeatDataMapper.mapToDtoList(listSeat));
    }

    @Override
    public void confirmSeatsRandom() {
        int i = 0;
        int j = 0;
        while (j == 0) {
            if (((RadioButton) viewModel.getGroup().getToggles().get(i)).isDisabled()) {
                i++;
            } else {
                j = 1;
            }
        }
        RadioButton radioSelect = (RadioButton) viewModel.getGroup().getToggles().get(i);
        String position = radioSelect.getText();
        viewModel.selectedSeatsProperty().setValue(position);
        viewModel.selectedPositionSeatIntegerProperty().setValue(i);


    }

    @Override
    public void setSelectedTicket(Ticket ticket) {
        viewModel.selectedTicketProperty().setValue(
                TicketDataMapper.mapToDto(ticket)
        );
    }

    @Override
    public void presentProjection(Projection projection) {
        viewModel.selectedProjectionProperty().setValue(ProjectionDataMapper.mapToDto(projection));
    }


    @Override
    public void confirmSeatsSpecific() {
        //Reperisco la stringa associata al radioButton
        String position = viewModel.getGroup().getSelectedToggle()
                .toString()
                .substring(viewModel.getGroup().getSelectedToggle()
                        .toString().length() - 3, viewModel.getGroup()
                        .getSelectedToggle()
                        .toString()
                        .length() - 1);
        int i = 0;
        int j = 0;
        while (j == 0) {
            String text = ((RadioButton) viewModel.getGroup().getToggles().get(i)).getText();
            if (text.equals(position)) {
                j = 1;
            } else {
                i++;
            }
        }
        viewModel.selectedSeatsProperty().setValue(position);
        viewModel.selectedPositionSeatIntegerProperty().setValue(i);


    }


}
