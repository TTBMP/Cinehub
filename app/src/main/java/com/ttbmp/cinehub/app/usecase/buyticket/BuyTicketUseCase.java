package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketUseCase extends com.ttbmp.cinehub.app.usecase.UseCase {

    void pay(PaymentRequest request);

    void getListMovie(GetListMovieRequest request);

    void createTicket(GetTicketBySeatsRequest request);

    void getListCinema(GetListCinemaRequest request);

    void getProjectionList(GetProjectionRequest request);

    void getListOfSeat(GetNumberOfSeatsRequest request);
}
