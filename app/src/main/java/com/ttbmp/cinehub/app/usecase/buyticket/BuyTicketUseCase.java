package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.usecase.UseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Palmieri Ivan
 */
public interface BuyTicketUseCase extends UseCase {

    void pay(PayRequest request);

    void getListMovie(GetListMovieRequest request);

    void createTicket(GetTicketBySeatsRequest request);

    void getListCinema(GetListCinemaRequest request);

    void getProjectionList(GetProjectionRequest request);

    void getListOfSeat(GetNumberOfSeatsRequest request);
}
