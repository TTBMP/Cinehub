package com.ttbmp.cinehub.domain.usecase.buyticket;

import com.ttbmp.cinehub.domain.usecase.UseCase;
import com.ttbmp.cinehub.domain.usecase.buyticket.request.*;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketUseCase extends UseCase {

    boolean pay(PayRequest request);

    void getListMovie(GetListMovieRequest request);

    void createTicket(GetTicketBySeatsRequest request);

    void getListCinema(GetListCinemaRequest request);

    void getProjectionList(GetTimeOfProjectionRequest request);

    void getListOfSeat(GetNumberOfSeatsRequest request);
}
