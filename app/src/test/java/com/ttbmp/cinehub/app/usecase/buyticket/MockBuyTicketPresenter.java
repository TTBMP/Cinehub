package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.util.stream.Collectors;

class MockBuyTicketPresenter implements BuyTicketPresenter {

    private final MockBuyTicketViewModel viewModel;

    public MockBuyTicketPresenter(MockBuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentMovieList(MovieListReply reply) {
        viewModel.setMovieList(reply.getMovieList());

    }

    @Override
    public void presentCinemaList(CinemaListReply reply) {
        viewModel.setCinemaList(reply.getCinemaList());
    }

    @Override
    public void presentSeatList(SeatListReply reply) {
        viewModel.setSeatList(reply.getSeatDtoList());
    }

    @Override
    public void presentProjectionList(ProjectionListReply reply) {
        viewModel.setProjectionList(reply.getProjectionDtoList());
    }


    @Override
    public void presentTicket(TicketReply reply) {
        viewModel.setTicket(reply.getTicketDto());
    }


    @Override
    public void presentNullRequest() {
        viewModel.setErrorMessage("Request can't be null");
    }


    @Override
    public void presentInvalidRequest(Request request) {
        viewModel.setErrorMessage(request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining())
        );
    }


    @Override
    public void presentPaymentServiceException(PaymentServiceException e) {
        viewModel.setErrorMessage(e.getMessage());
    }


    @Override
    public void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());

    }

    @Override
    public void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException e) {
        viewModel.setErrorMessage(e.getMessage());

    }

    @Override
    public void presentSeatAlreadyBookedError(SeatErrorReply reply) {
        viewModel.setErrorMessage(reply.getError());
    }

    @Override
    public void presentRepositoryError(RepositoryException e) {
        viewModel.setErrorMessage(e.getMessage());

    }

}