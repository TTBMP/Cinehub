package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.usecase.UseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketUseCase extends UseCase {

    void getListMovie(MovieListRequest request);

    void getListCinema(CinemaListRequest request);

    void getCinema(CinemaInformationRequest request);

    void getProjectionList(ProjectionListRequest request);

    void getProjection(ProjectionRequest request);

    void getListOfSeat(CinemaInformationRequest request);

    void createTicket(TicketRequest request);

    void pay(PaymentRequest request);

}
