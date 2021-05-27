package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter {

    void presentMovieList(MovieListResponse response);

    void presentCinemaList(CinemaListResponse response);


    void presentProjectionList(ProjectionListResponse response);


    void presentInvalidRequest(Request request);

    void presentSeatList(SeatListResponse response);

    void presentNullRequest();


    void presentPaymentServiceException(PaymentServiceException exception);

    void presentTicket(TicketResponse response);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException requestException);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException requestException);

    void presentRepositoryError(RepositoryException exception);

    void presentSeatAlreadyBookedError(SeatErrorResponse response);

}
