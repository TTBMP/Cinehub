package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;


public class UseCase {

    private static BuyTicketViewModel buyTicketViewModel;
    public static final BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterFx(getViewModel()));

    private UseCase() {
    }

    public static BuyTicketViewModel getViewModel() {
        if (buyTicketViewModel == null) {
            buyTicketViewModel = new BuyTicketViewModel();
        }
        return buyTicketViewModel;
    }
}
