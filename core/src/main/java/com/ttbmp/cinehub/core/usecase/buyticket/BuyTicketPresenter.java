package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.service.payment.PaymentException;
import com.ttbmp.cinehub.core.usecase.buyticket.request.*;
import com.ttbmp.cinehub.core.usecase.buyticket.response.*;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketPresenter {


    void presentMovieApiList(GetListMovieResponse response);

    void presentCinemaList(GetListCinemaResponse response);

    void presentSeatList(GetNumberOfSeatsResponse response);

    void setSelectedTicket(GetTicketBySeatsResponse response);

    void presentPayNullRequest();

    void presentInvalidPay(PayRequest request);

    void presentGetTicketBySeatsNullRequest();

    void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request);

    void presentGetListCinemaNullRequest();

    void presentInvalidGetListCinema(GetListCinemaRequest request);

    void presentGetTimeOfProjectionNullRequest();

    void presentInvalidGetTimeOfProjection(GetTimeOfProjectionRequest request);

    void presentGetNumberOfSeatsNullRequest();

    void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request);

    void presentGetListMovieError();

    void presentErrorByStripe(PaymentException error);

    void presentProjectionList(ProjectionListResponse projectionTimeList);

    void presentGetListMovieNullRequest();

    void presentInvalidGetListMovie(GetListMovieRequest request);
}
