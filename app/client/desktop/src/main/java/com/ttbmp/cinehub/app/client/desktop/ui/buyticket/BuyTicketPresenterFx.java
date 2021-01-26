package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;


import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.core.usecase.buyticket.request.*;
import com.ttbmp.cinehub.core.usecase.buyticket.response.*;
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
    public void presentMovieApiList(GetListMovieResponse response) {
        viewModel.getMovieList().clear();
        if (viewModel.selectedDateProperty().getValue().equals(LocalDate.now())) {
            viewModel.getMovieList().addAll(response.getMovieList().subList(0, 5));
        } else {
            viewModel.getMovieList().addAll(response.getMovieList().subList(5, 10));
        }
    }

    @Override
    public void presentCinemaList(GetListCinemaResponse response) {
        viewModel.getCinemaList().clear();
        viewModel.getCinemaList().addAll(response.getCinemaList());
    }

    @Override
    public void presentTimeList(List<String> timeOfProjectionList) {
        viewModel.getTimeOfProjectionList().clear();
        viewModel.getTimeOfProjectionList().addAll(timeOfProjectionList);
    }

    @Override
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        viewModel.getSeatList().clear();
        viewModel.getSeatList().addAll(response.getSeatDtoList());
    }

    @Override
    public void presentProjection(SetProjectionResponse response) {
        viewModel.selectedProjectionProperty().setValue(response.getProjectionDto());
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
        String position = radioSelect.getText().substring(0, 3);
        viewModel.selectedSeatsProperty().setValue(position);
        viewModel.selectedPositionSeatIntegerProperty().setValue(i);


    }


    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        viewModel.selectedTicketProperty().setValue(response.getTicketDto());
        viewModel.selectedSeatPriceProperty().setValue(response.getTicketDto().getPrice().toString());
    }


    @Override
    public void presentInvalidSendEmail(SendEmailRequest request) {
        viewModel.emailErrorProperty().setValue(SendEmailRequest.MISSING_RECIPIENT_ERROR.getMessage());
    }

    @Override
    public void presentSendEmailNullRequest() {
        viewModel.emailErrorProperty().setValue("Error with operation SendEmail");
    }

    @Override
    public void presentPayNullRequest() {
        viewModel.paymentErrorProperty().setValue("Error with operation Pay");
    }

    @Override
    public void presentInvalidPay(PayRequest request) {
        if (request.getErrorList().contains(PayRequest.MISSING_INDEX_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PayRequest.MISSING_INDEX_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PayRequest.MISSING_TICKET_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PayRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PayRequest.MISSING_PROJECTION_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PayRequest.MISSING_PROJECTION_ERROR.getMessage());
        }

    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        viewModel.seatErrorProperty().setValue("Error with operation GetTicketBySeats");
    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_LIST_SEATS_ERROR)) {
            viewModel.seatErrorProperty().setValue(GetTicketBySeatsRequest.MISSING_LIST_SEATS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_POSITION_ERROR)) {
            viewModel.seatErrorProperty().setValue(GetTicketBySeatsRequest.MISSING_POSITION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_INDEX_ERROR)) {
            viewModel.seatErrorProperty().setValue(GetTicketBySeatsRequest.MISSING_INDEX_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetListCinemaNullRequest() {
        viewModel.cinemaErrorProperty().setValue("Error with operation GetListCinema");
    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {
        viewModel.cinemaErrorProperty().setValue(GetListCinemaRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        viewModel.cinemaErrorProperty().setValue("Error with operation GetTimeOfProjection");

    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetTimeOfProjecitonRequest request) {
        if (request.getErrorList().contains(GetTimeOfProjecitonRequest.MISSING_MOVIE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(GetTimeOfProjecitonRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTimeOfProjecitonRequest.MISSING_CINEMA_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(GetTimeOfProjecitonRequest.MISSING_CINEMA_ERROR.getMessage());
        }
    }

    @Override
    public void presentSetProjectionNullRequest() {
        viewModel.movieErrorProperty().setValue("Error with operation SetProjection");

    }

    @Override
    public void presentInvalidSetProjection(SetProjectionRequest request) {
        if (request.getErrorList().contains(SetProjectionRequest.MISSING_CINEMA_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(SetProjectionRequest.MISSING_CINEMA_ERROR.getMessage());
        }
        if (request.getErrorList().contains(SetProjectionRequest.MISSING_MOVIE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(SetProjectionRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(SetProjectionRequest.MISSING_TIME_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(SetProjectionRequest.MISSING_TIME_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        viewModel.seatErrorProperty().setValue("Error with operation GetNumberOfSeats");
    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {
        viewModel.seatErrorProperty().setValue(GetNumberOfSeatsRequest.MISSING_PROJECTION_ERROR.getMessage());
    }

    @Override
    public void presentGetListMovie() {
        viewModel.movieErrorProperty().setValue("Unable to recover movies by service");
    }

    @Override
    public void presentErrorByStripe(String error) {
        viewModel.paymentErrorProperty().setValue(error);
    }


    @Override
    public void confirmSeatsSpecific() {
        String position = viewModel.getGroup().getSelectedToggle().toString().substring(
                viewModel.getGroup().getSelectedToggle().toString().length() - 6,
                viewModel.getGroup().getSelectedToggle().toString().length() - 4);
        int i = 0;
        int j = 0;
        while (j == 0) {
            String text = ((RadioButton) viewModel.getGroup().getToggles().get(i)).getText().substring(0, 2);
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
