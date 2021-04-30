package com.ttbmp.cinehub.ui.desktop.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie.ChooseMovieView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

import java.io.IOException;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketActivity extends Activity {

    public BuyTicketActivity() throws IOException {
        super(new ChooseMovieView());
        var viewModel = new BuyTicketViewModel();
        BuyTicketPresenter presenter = new BuyTicketPresenterFx(viewModel);
        viewModelStore.put(BuyTicketViewModel.class, viewModel);
        useCaseFactory.put(BuyTicketUseCase.class, () -> new BuyTicketHandler(presenter));
    }
}
