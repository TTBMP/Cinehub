package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.repository.CinemaRepository;
import com.ttbmp.cinehub.app.repository.MovieRepository;
import com.ttbmp.cinehub.app.repository.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.UserRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketController;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.ui.web.CinehubApplication;


public class UseCase  {
    private static final CinemaRepository cinemaRepository = CinehubApplication.APP_CONTAINER.getFactory(CinemaRepository.class).get();
    private static final MovieRepository movieRepository = CinehubApplication.APP_CONTAINER.getFactory(MovieRepository.class).get();
    private static final ProjectionRepository projectionRepository = CinehubApplication.APP_CONTAINER.getFactory(ProjectionRepository.class).get();
    private static final PaymentService paymentService = CinehubApplication.APP_CONTAINER.getFactory(PaymentService.class).get();
    private static final EmailService emailService = CinehubApplication.APP_CONTAINER.getFactory(EmailService.class).get();
    private static final AuthenticationService authenticationService = CinehubApplication.APP_CONTAINER.getFactory(AuthenticationService.class).get();
    private static final UserRepository userRepository = CinehubApplication.APP_CONTAINER.getFactory(UserRepository.class).get();
    private static BuyTicketViewModel buyTicketViewModel;


    private UseCase() {
    }

    public static final BuyTicketUseCase buyTicketUseCase=
            new BuyTicketController(
                    paymentService,
                    emailService,
                    new BuyTicketPresenterFx(getViewModel()),
                    movieRepository,
                    cinemaRepository,
                    authenticationService,
                    projectionRepository,
                    userRepository

            );

    public static BuyTicketViewModel getViewModel(){
        if(buyTicketViewModel==null){
            buyTicketViewModel = new BuyTicketViewModel();
        }
        return buyTicketViewModel;
    }
}
