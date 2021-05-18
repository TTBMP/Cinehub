package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Fabio Buracchi
 */
public class BuyTicketHandler implements BuyTicketUseCase {

    private final BuyTicketController buyTicketController;

    public BuyTicketHandler(BuyTicketPresenter presenter) {
        buyTicketController = new BuyTicketController(new ServiceLocator(), presenter);
    }

    @Override
    public void getMovieList(MovieListRequest request) {
        buyTicketController.getMovieList(request);
    }

    @Override
    public void getCinemaList(CinemaListRequest request) {
        buyTicketController.getCinemaList(request);
    }

    @Override
    public void getProjectionList(ProjectionListRequest request) {
        buyTicketController.getProjectionList(request);
    }

    @Override
    public void getSeatList(SeatListRequest request) {
        buyTicketController.getSeatList(request);
    }

    @Override
    public void createTicket(TicketRequest request) {
        buyTicketController.createTicket(request);
    }

    @Override
    public void pay(PaymentRequest request) {
        buyTicketController.pay(request);
    }

}
