package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter {


    void presentMovieApiList(MovieListResponse response);

    void presentCinemaList(CinemaListResponse response);

    void presentCinema(CinemaResponse response);

    void presentSeatList(NumberOfSeatsResponse response);

    void setSelectedTicket(TicketResponse response);

    void presentPayNullRequest();

    void presentInvalidPay(PaymentRequest request);

    void presentGetTicketBySeatsNullRequest();

    void presentInvalidGetTicketBySeats(TicketRequest request);

    void presentGetListCinemaNullRequest();

    void presentInvalidGetListCinema(CinemaListRequest request);

    void presentGetTimeOfProjectionNullRequest();

    void presentInvalidGetTimeOfProjection(ProjectionListRequest request);

    void presentProjection(ProjectionResponse request);

    void presentGetNumberOfSeatsNullRequest();

    void presentInvalidGetNumberOfSeats(CinemaInformationRequest request);

    void presentErrorByStripe(PaymentServiceException error);

    void presentProjectionList(ProjectionListResponse projectionTimeList);

    void presentGetListMovieNullRequest();

    void presentInvalidGetListMovie(MovieListRequest request);

    void presentAuthenticationError();


    void presentInvalidGetCinema(CinemaInformationRequest request);

    void presentPayRepositoryException(String message);

    void presentGetListMovieRepositoryException(String message);

    void presentGetCinemaListRepositoryException(String message);

    void presentCreateTicketRepositoryException(String message);

    void presentGetProjectionListRepositoryException(String message);

    void presentGetProjectionRepositoryException(String message);

    void presentGetCinemaRepositoryException(String message);
}
