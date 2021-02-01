package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;

import com.ttbmp.cinehub.app.client.desktop.CinehubApplication;
import com.ttbmp.cinehub.app.client.desktop.ui.buyticket.choosemovie.ChooseMovieView;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.core.repository.*;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.core.usecase.buyticket.BuyTicketController;

import java.io.IOException;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketActivity extends Activity {
    private final CinemaRepository cinemaRepository = CinehubApplication.APP_CONTAINER.getFactory(CinemaRepository.class).get();
    private final HallRepository hallRepository = CinehubApplication.APP_CONTAINER.getFactory(HallRepository.class).get();
    private final MovieRepository movieRepository = CinehubApplication.APP_CONTAINER.getFactory(MovieRepository.class).get();
    private final ProjectionRepository projectionRepository = CinehubApplication.APP_CONTAINER.getFactory(ProjectionRepository.class).get();
    private final SeatRepository seatRepository = CinehubApplication.APP_CONTAINER.getFactory(SeatRepository.class).get();
    private final TimeRepository timeRepository = CinehubApplication.APP_CONTAINER.getFactory(TimeRepository.class).get();
    private final PaymentService paymentService = CinehubApplication.APP_CONTAINER.getFactory(PaymentService.class).get();
    private final EmailService emailService = CinehubApplication.APP_CONTAINER.getFactory(EmailService.class).get();
    private final MovieApiService movieApiService = CinehubApplication.APP_CONTAINER.getFactory(MovieApiService.class).get();
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
                        movieApiService,
                        movieRepository,
                        cinemaRepository,
                        timeRepository,
                        seatRepository,
                        authenticationService,
                        hallRepository,
                        projectionRepository,
                        userRepository
                ));
    }
}
