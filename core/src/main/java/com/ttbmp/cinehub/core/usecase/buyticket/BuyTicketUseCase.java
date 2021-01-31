package com.ttbmp.cinehub.core.usecase.buyticket;

import com.ttbmp.cinehub.core.usecase.UseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.request.*;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketUseCase extends UseCase {

    boolean pay(PayRequest request);

    void getListMovie(String value);

    void createTicket(GetTicketBySeatsRequest request);

    void getListCinema(GetListCinemaRequest request);

    void getAvailableTimesOfScreenings(GetTimeOfProjectionRequest request);


    void getProjection(SetSelectedProjectionRequest request);

    void getListOfSeat(GetNumberOfSeatsRequest request);
}
