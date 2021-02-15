package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;

/**
 * @author Fabio Buracchi
 */
public class BuyTicketHandler implements BuyTicketUseCase {

    private final BuyTicketController controller;

    public BuyTicketHandler(BuyTicketPresenter presenter) {
        controller = new BuyTicketController(new ServiceLocator(), presenter);
    }

    @Override
    public void pay(PayRequest request) {
        controller.pay(request);
    }

    @Override
    public void getListMovie(GetListMovieRequest request) {
        controller.getListMovie(request);
    }

    @Override
    public void createTicket(GetTicketBySeatsRequest request) {
        controller.createTicket(request);
    }

    @Override
    public void getListCinema(GetListCinemaRequest request) {
        controller.getListCinema(request);
    }

    @Override
    public void getProjectionList(GetProjectionRequest request) {
        controller.getProjectionList(request);
    }

    @Override
    public void getListOfSeat(GetNumberOfSeatsRequest request) {
        controller.getListOfSeat(request);
    }

}
