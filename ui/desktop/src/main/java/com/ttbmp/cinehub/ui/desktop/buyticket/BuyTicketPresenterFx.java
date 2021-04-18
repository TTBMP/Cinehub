package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketPresenterFx implements BuyTicketPresenter {


    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentMovieApiList(GetListMovieResponse response) {
        viewModel.getMovieList().clear();
        viewModel.getMovieList().addAll(response.getMovieList());
    }

    @Override
    public void presentCinemaList(GetListCinemaResponse response) {
        viewModel.getCinemaList().setAll(response.getCinemaList());
    }

    @Override
    public void presentCinema(GetCinemaResponse response) {
        viewModel.selectedCinemaNameProperty();
    }


    @Override
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        viewModel.getSeatList().setAll(response.getSeatDtoList());
    }


    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        viewModel.selectedTicketProperty().setValue(response.getTicketDto());
        viewModel.selectedSeatPriceProperty().setValue(response.getTicketDto().getPrice().toString());
    }


    @Override
    public void presentPayNullRequest() {
        viewModel.paymentErrorProperty().setValue("Error with operation Pay");
    }

    @Override
    public void presentInvalidPay(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.MISSING_TICKET_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_PROJECTION_ERROR.getMessage());
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
    public void presentInvalidGetTimeOfProjection(GetProjectionListRequest request) {
        if (request.getErrorList().contains(GetProjectionListRequest.MISSING_MOVIE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(GetProjectionListRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetProjectionListRequest.MISSING_DATE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(GetProjectionListRequest.MISSING_DATE_ERROR.getMessage());
        }
    }

    @Override
    public void presentProjection(GetProjectionResponse request) {
        viewModel.seatErrorProperty().setValue("Error with projection presenting");
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
    public void presentGetListMovieError() {
        viewModel.movieErrorProperty().setValue("Unable to recover movies by service");
    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        viewModel.paymentErrorProperty().setValue(error.getMessage());
    }

    @Override
    public void presentProjectionList(GetProjectionListResponse response) {
        viewModel.getProjectionOfProjectionTimeList().setAll(response.getProjectionDto());
    }

    @Override
    public void presentGetListMovieNullRequest() {
        viewModel.movieErrorProperty().setValue("Unable to recover movies by service");

    }

    @Override
    public void presentInvalidGetListMovie(GetListMovieRequest request) {
        viewModel.movieErrorProperty().setValue(GetListCinemaRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentAutenticationError() {
        viewModel.autenticationErrorProperty().setValue("Problems with accessing the server ");

    }

    @Override
    public void presentInvalidGetCinema(GetCinemaRequest request) {
        viewModel.cinemaErrorProperty().setValue("Problems with retrive the cinema ");
    }


}
