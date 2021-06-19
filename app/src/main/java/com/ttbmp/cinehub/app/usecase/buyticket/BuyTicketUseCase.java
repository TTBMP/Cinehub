package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.utilities.usecase.UseCase;

/**
 * @author Ivan Palmieri
 */
public interface BuyTicketUseCase extends UseCase {

    void getMovieList(MovieListRequest request);

    void getCinemaList(CinemaListRequest request);

    void getProjectionList(ProjectionListRequest request);

    void getSeatList(SeatListRequest request);

    void pay(PaymentRequest request);

}
