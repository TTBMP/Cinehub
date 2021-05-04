package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketPresenter {


    void presentMovieApiList(GetListMovieResponse response);

    void presentCinemaList(GetListCinemaResponse response);

    void presentCinema(GetCinemaResponse response);

    void presentSeatList(GetNumberOfSeatsResponse response);

    void setSelectedTicket(GetTicketBySeatsResponse response);

    void presentPayNullRequest();

    void presentInvalidPay(PaymentRequest request);

    void presentGetTicketBySeatsNullRequest();

    void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request);

    void presentGetListCinemaNullRequest();

    void presentInvalidGetListCinema(GetListCinemaRequest request);

    void presentGetTimeOfProjectionNullRequest();

    void presentInvalidGetTimeOfProjection(GetProjectionListRequest request);

    void presentProjection(GetProjectionResponse request);

    void presentGetNumberOfSeatsNullRequest();

    void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request);

    void presentGetListMovieError();

    void presentErrorByStripe(PaymentServiceException error);

    void presentProjectionList(GetProjectionListResponse projectionTimeList);

    void presentGetListMovieNullRequest();

    void presentInvalidGetListMovie(GetListMovieRequest request);

    void presentAuthenticationError();


    void presentInvalidGetCinema(GetCinemaRequest request);

    void presentPayRepositoryException(String message);

    void presentGetListMovieRepositoryException(String message);

    void presentGetCinemaListRepositoryException(String message);

    void presentCreateTicketRepositoryException(String message);

    void presentGetProjectionListRepositoryException(String message);

    void presentGetProjectionRepositoryException(String message);

    void presentGetCinemaRepositoryException(String message);
}
