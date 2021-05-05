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
    public void pay(PaymentRequest request) {
        buyTicketController.pay(request);
    }

    @Override
    public void getListMovie(MovieListRequest request) {
        buyTicketController.getListMovie(request);
    }

    @Override
    public void createTicket(TicketRequest request) {
        buyTicketController.createTicket(request);
    }

    @Override
    public void getListCinema(CinemaListRequest request) {
        buyTicketController.getListCinema(request);
    }

    @Override
    public void getCinema(CinemaInformationRequest request) {
        buyTicketController.getCinema(request);
    }

    @Override
    public void getProjectionList(ProjectionListRequest request) {
        buyTicketController.getProjectionList(request);
    }

    @Override
    public void getListOfSeat(CinemaInformationRequest request) {
        buyTicketController.getListOfSeat(request);
    }

    @Override
    public void getProjection(ProjectionRequest request) {
        buyTicketController.getProjection(request);
    }

}
