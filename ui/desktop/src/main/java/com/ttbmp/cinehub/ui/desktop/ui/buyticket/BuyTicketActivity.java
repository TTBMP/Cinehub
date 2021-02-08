package com.ttbmp.cinehub.ui.desktop.ui.buyticket;

import com.ttbmp.cinehub.app.repository.CinemaRepository;
import com.ttbmp.cinehub.app.repository.MovieRepository;
import com.ttbmp.cinehub.app.repository.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.UserRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketController;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.ui.desktop.CinehubApplication;
import com.ttbmp.cinehub.ui.desktop.ui.buyticket.choosemovie.ChooseMovieView;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketActivity extends Activity {
    private final CinemaRepository cinemaRepository = CinehubApplication.APP_CONTAINER.getFactory(CinemaRepository.class).get();
    private final MovieRepository movieRepository = CinehubApplication.APP_CONTAINER.getFactory(MovieRepository.class).get();
    private final ProjectionRepository projectionRepository = CinehubApplication.APP_CONTAINER.getFactory(ProjectionRepository.class).get();
    private final PaymentService paymentService = CinehubApplication.APP_CONTAINER.getFactory(PaymentService.class).get();
    private final EmailService emailService = CinehubApplication.APP_CONTAINER.getFactory(EmailService.class).get();
    private final AuthenticationService authenticationService = CinehubApplication.APP_CONTAINER.getFactory(AuthenticationService.class).get();
    private final UserRepository userRepository = CinehubApplication.APP_CONTAINER.getFactory(UserRepository.class).get();


    public BuyTicketActivity() throws IOException {
        super(new ChooseMovieView());
        viewModelStore.put(BuyTicketViewModel.class, new BuyTicketViewModel());
        useCaseFactory.put(BuyTicketUseCase.class,
                () -> new BuyTicketController(
                        paymentService,
                        emailService,
                        new BuyTicketPresenterFx(getViewModel(BuyTicketViewModel.class)),
                        movieRepository,
                        cinemaRepository,
                        authenticationService,
                        projectionRepository,
                        userRepository
                ));
    }
}
