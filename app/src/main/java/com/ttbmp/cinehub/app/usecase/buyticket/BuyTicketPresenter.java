package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.reply.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter {

    void presentMovieList(MovieListReply reply);

    void presentCinemaList(CinemaListReply reply);

    void presentProjectionList(ProjectionListReply reply);

    void presentSeatList(SeatListReply reply);

    void presentTicket(TicketReply reply);

    void presentInvalidRequest(Request request);

    void presentNullRequest();

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException requestException);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException requestException);

    void presentRepositoryError(RepositoryException exception);

    void presentSeatAlreadyBookedError(SeatErrorReply reply);

    void presentPaymentServiceException(PaymentServiceException exception);

}
