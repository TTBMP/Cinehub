package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.usecase.buyticket.request.*;
import com.ttbmp.cinehub.core.usecase.buyticket.response.*;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketPresenter {


    void presentMovieApiList(GetListMovieResponse response);

    void presentCinemaList(GetListCinemaResponse response);

    void presentTimeList(List<String> timeOfProjectionList);

    void presentSeatList(GetNumberOfSeatsResponse response);


    void setSelectedTicket(GetTicketBySeatsResponse response);


    void presentProjection(SetProjectionResponse reponse);

    void presentPayNullRequest();

    void presentInvalidPay(PayRequest request);

    void presentGetTicketBySeatsNullRequest();

    void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request);

    void presentGetListCinemaNullRequest();

    void presentInvalidGetListCinema(GetListCinemaRequest request);

    void presentGetTimeOfProjectionNullRequest();

    void presentInvalidGetTimeOfProjection(GetTimeOfProjectionRequest request);

    void presentSetProjectionNullRequest();

    void presentInvalidSetProjection(SetSelectedProjectionRequest request);

    void presentGetNumberOfSeatsNullRequest();

    void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request);

    void presentGetListMovie();

    void presentErrorByStripe(String error);

    void presentProjectionList(ProjectionListResponse projectionTimeList);

    void presentGetListMovieNullRequest();

    void presentInvalidGetListMovie(GetListMovieRequest request);
}
