package com.ttbmp.cinehub.core.usecase.buyticket;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketPresenter {


    void presentMovieApiList(GetListMovieResponse response);

    void presentCinemaList(GetListCinemaResponse response);

    void presentTimeList(List<String> timeOfProjectionList);

    void presentSeatList(GetNumberOfSeatsResponse response);

    void confirmSeatsSpecific();

    void confirmSeatsRandom();

    void setSelectedTicket(GetTicketBySeatsResponse response);


    void presentProjection(SetProjectionResponse reponse);

    void presentInvalidSendEmail(SendEmailRequest request);

    void presentSendEmailNullRequest();

    void presentPayNullRequest();

    void presentInvalidPay(PayRequest request);

    void presentGetTicketBySeatsNullRequest();

    void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request);

    void presentGetListCinemaNullRequest();

    void presentInvalidGetListCinema(GetListCinemaRequest request);

    void presentGetTimeOfProjectionNullRequest();

    void presentInvalidGetTimeOfProjection(GetTimeOfProjecitonRequest request);

    void presentSetProjectionNullRequest();

    void presentInvalidSetProjection(SetProjectionRequest request);

    void presentGetNumberOfSeatsNullRequest();

    void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request);
}
