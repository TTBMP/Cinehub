package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.CinehubException;
import com.ttbmp.cinehub.app.service.email.EmailServiceException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
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
                        .collect(Collectors.joining("\n"))
        );
    }

    @Override
    public void presentPaymentServiceException(PaymentServiceException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentSendEmailServiceException(EmailServiceException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());

    }


    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException exception) {
        viewModel.loginRequestedProperty().setValue(true);
        viewModel.errorMessageProperty().setValue(exception.getMessage() + ", you must log in first");

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentSeatAlreadyBookedError(SeatErrorReply reply) {
        viewModel.errorMessageProperty().setValue(reply.getError());
    }

    @Override
    public void presentApplicationError(CinehubException exception) {
        viewModel.errorMessageProperty().setValue(exception.getMessage());
    }

    @Override
    public void presentTicket(TicketReply reply) {
        viewModel.selectedTicketProperty().setValue(reply.getTicketDto());
    }

    @Override
    public void presentProjectionList(ProjectionListReply reply) {
        viewModel.getProjectionList().setAll(reply.getProjectionDtoList());
    }

    @Override
    public void presentMovieList(MovieListReply reply) {
        viewModel.getMovieList().clear();
        viewModel.getMovieList().addAll(reply.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListReply reply) {
        viewModel.getCinemaList().setAll(reply.getCinemaList());
    }

    @Override
    public void presentSeatList(SeatListReply reply) {
        viewModel.getSeatList().setAll(reply.getSeatDtoList());
    }


}
