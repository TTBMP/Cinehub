package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter {

    void presentMovieList(MovieListResponse response);

    void presentMovieListNullRequest();

    void presentMovieListInvalidRequest(MovieListRequest request);

    void presentMovieListRepositoryException(String message);

    void presentCinemaList(CinemaListResponse response);

    void presentCinemaListNullRequest();

    void presentCinemaListInvalidRequest(CinemaListRequest request);

    void presentCinemaListRepositoryException(String message);

    void presentProjectionList(ProjectionListResponse projectionTimeList);

    void presentProjectionListNullRequest();

    void presentProjectionListInvalidRequest(ProjectionListRequest request);

    void presentProjectionListRepositoryException(String message);

    void presentSeatList(NumberOfSeatsResponse response);

    void presentSeatListNullRequest();

    void presentSeatListInvalidRequest(SeatListRequest request);


    void presentPayNullRequest();

    void presentPayInvalidRequest(PaymentRequest request);

    void presentPayRepositoryException(String message);

    void presentErrorByStripe(PaymentServiceException error);

    void presentAuthenticationError();

    void presentTicket(TicketDto ticketDto);
}
