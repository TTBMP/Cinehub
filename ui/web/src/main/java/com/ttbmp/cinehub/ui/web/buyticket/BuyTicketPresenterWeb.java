package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterWeb implements BuyTicketPresenter {


    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterWeb(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentMovieApiList(GetListMovieResponse response) {
        viewModel.setMovieDtoList(response.getMovieList());
    }

    @Override
    public void presentCinemaList(GetListCinemaResponse response) {
        viewModel.setCinemaDtoList(response.getCinemaList());
    }


    @Override
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        viewModel.setSeatDtoList(response.getSeatDtoList());
    }

    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        viewModel.setSelectedTicket(response.getTicketDto());
    }


    @Override
    public void presentPayNullRequest() {
        viewModel.setPaymentError("Error with operation Pay");
    }

    @Override
    public void presentInvalidPay(PayRequest request) {
        if (request.getErrorList().contains(PayRequest.MISSING_INDEX_ERROR)) {
            viewModel.setPaymentError(PayRequest.MISSING_INDEX_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PayRequest.MISSING_TICKET_ERROR)) {
            viewModel.setPaymentError(PayRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PayRequest.MISSING_PROJECTION_ERROR)) {
            viewModel.setPaymentError(PayRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        viewModel.setSeatError("Error with operation GetTicketBySeats");

    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_LIST_SEATS_ERROR)) {
            viewModel.setSeatError(GetTicketBySeatsRequest.MISSING_LIST_SEATS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_POSITION_ERROR)) {
            viewModel.setSeatError(GetTicketBySeatsRequest.MISSING_POSITION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_INDEX_ERROR)) {
            viewModel.setSeatError(GetTicketBySeatsRequest.MISSING_INDEX_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetListCinemaNullRequest() {
        viewModel.setCinemaError("Error with operation GetListCinema");
    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {
        viewModel.setCinemaError(GetListCinemaRequest.MISSING_MOVIE_ERROR.getMessage());

    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        viewModel.setCinemaError("Error with operation GetTimeOfProjection");

    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetProjectionRequest request) {
        if (request.getErrorList().contains(GetProjectionRequest.MISSING_MOVIE_ERROR)) {
            viewModel.setCinemaError((GetProjectionRequest.MISSING_MOVIE_ERROR.getMessage()));
        }
        if (request.getErrorList().contains(GetProjectionRequest.MISSING_DATE_ERROR)) {
            viewModel.setCinemaError((GetProjectionRequest.MISSING_DATE_ERROR.getMessage()));
        }
    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        viewModel.setSeatError("Error with operation GetNumberOfSeats");

    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {
        viewModel.setSeatError(GetNumberOfSeatsRequest.MISSING_PROJECTION_ERROR.getMessage());
    }

    @Override
    public void presentGetListMovieError() {
        viewModel.setMovieError("Unable to recover movies by service");
    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        viewModel.setPaymentError(error.getMessage());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse projectionTimeList) {
        viewModel.setProjectionList(projectionTimeList.getProjectionDto());
    }

    @Override
    public void presentGetListMovieNullRequest() {
        viewModel.setMovieError("Unable to recover movies by service");

    }

    @Override
    public void presentInvalidGetListMovie(GetListMovieRequest request) {
        viewModel.setMovieError(GetListCinemaRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentAutenticationError() {
        viewModel.setMovieError("");
    }

}
