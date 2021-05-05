package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.usecase.UseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketUseCase extends UseCase {

    void pay(PaymentRequest request);

    void getListMovie(MovieListRequest request);

    void createTicket(TicketRequest request);

    void getListCinema(CinemaListRequest request);

    void getProjectionList(ProjectionListRequest request);

    void getListOfSeat(CinemaInformationRequest request);

    void getProjection(ProjectionRequest request);

    void getCinema(CinemaInformationRequest request);
}
