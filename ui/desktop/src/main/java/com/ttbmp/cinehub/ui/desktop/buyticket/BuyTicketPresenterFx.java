package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.stream.Collectors;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketPresenterFx implements BuyTicketPresenter {


    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentNullRequest() {
        viewModel.errorMessageProperty().setValue("Request can't be null");
    }

    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.errorMessageProperty().setValue(
                request.getErrorList().stream()
                        .map(Request.Error::getMessage)
                        .collect(Collectors.joining())
        );
    }

    @Override
    public void presentPaymentServiceException(PaymentServiceException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }


    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage() + ", you must log in first");

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentSeatAlreadyBookedError(SeatErrorResponse response) {
        viewModel.errorMessageProperty().setValue(response.getError());
    }

    @Override
    public void presentRepositoryError(RepositoryException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentTicket(TicketResponse response) {
        viewModel.selectedTicketProperty().setValue(response.getTicketDto());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse response) {
        viewModel.projectionTimeListProperty().setAll(response.getProjectionDtoList());
    }

    @Override
    public void presentMovieList(MovieListResponse response) {
        viewModel.movieListProperty().clear();
        viewModel.movieListProperty().addAll(response.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListResponse response) {
        viewModel.cinemaListProperty().setAll(response.getCinemaList());
    }

    @Override
    public void presentSeatList(SeatListResponse response) {
        viewModel.seatListProperty().setAll(response.getSeatDtoList());
    }


}
