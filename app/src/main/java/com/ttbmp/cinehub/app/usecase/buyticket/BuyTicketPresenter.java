package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter {

    void presentMovieList(MovieListResponse response);

    void presentMovieListNullRequest();

    void presentInvalidMovieListRequest(MovieListRequest request);

    void presentRepositoryError(RepositoryException exception);

    void presentCinemaList(CinemaListResponse response);

    void presentCinemaListNullRequest();

    void presentInvalidCinemaListRequest(CinemaListRequest request);

    void presentProjectionList(ProjectionListResponse response);

    void presentProjectionListNullRequest();

    void presentInvalidProjectionListRequest(ProjectionListRequest request);

    void presentSeatList(NumberOfSeatsResponse response);

    void presentSeatListNullRequest();

    void presentInvalidSeatListRequest(SeatListRequest request);

    void presentPayNullRequest();

    void presentInvalidPayRequest(PaymentRequest request);

    void presentPayPaymentServiceException(PaymentServiceException exception);

    void presentTicket(TicketResponse response);

    void presentUnauthenticatedError(AuthenticatedRequest.UnauthenticatedRequestException requestException);

    void presentUnauthorizedError(AuthenticatedRequest.UnauthorizedRequestException requestException);


    void presentSeatAlreadyOccupiedException(String exception);
}
