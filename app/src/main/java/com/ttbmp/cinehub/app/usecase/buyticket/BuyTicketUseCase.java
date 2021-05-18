package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.usecase.UseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketUseCase extends UseCase {

    void getMovieList(MovieListRequest request);

    void getCinemaList(CinemaListRequest request);

    void getProjectionList(ProjectionListRequest request);

    void getSeatList(SeatListRequest request);

    void createTicket(TicketRequest request);

    void pay(PaymentRequest request);

}
