package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.usecase.UseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.request.*;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketUseCase extends UseCase {

    void sendEmail(SendEmailRequest sendEmailRequest);

    boolean pay(PayRequest request);

    void getListMovie();

    void getTicketBySeats(GetTicketBySeatsRequest request);

    void getListCinema(GetListCinemaRequest request);

    void getTimeOfProjection(GetTimeOfProjecitonRequest request);

    void confirmSeatsRandom();

    void confirmSeatsSpecific();

    void setProjection(SetProjectionRequest request);

    void getNumberOfSeats(GetNumberOfSeatsRequest request);
}
