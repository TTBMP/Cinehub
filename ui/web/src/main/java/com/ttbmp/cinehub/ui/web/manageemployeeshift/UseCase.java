package com.ttbmp.cinehub.ui.web.manageemployeeshift;


import com.ttbmp.cinehub.app.repository.CinemaRepository;
import com.ttbmp.cinehub.app.repository.HallRepository;
import com.ttbmp.cinehub.app.repository.ShiftRepository;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.usecase.manageemployeesshift.ManageEmployeesShiftController;
import com.ttbmp.cinehub.ui.web.CinehubApplication;

public class UseCase {

    private static final CinemaRepository cinemaRepository = CinehubApplication.APP_CONTAINER.getFactory(CinemaRepository.class).get();
    private static final ShiftRepository shiftRepository = CinehubApplication.APP_CONTAINER.getFactory(ShiftRepository.class).get();
    private static final HallRepository hallRepository = CinehubApplication.APP_CONTAINER.getFactory(HallRepository.class).get();
    private static final EmailService emailService = CinehubApplication.APP_CONTAINER.getFactory(EmailService.class).get();
    private static ManageEmployeeShiftViewModel viewModel;

    private UseCase() {
    }

    public static final ManageEmployeesShiftController manageEmployeeUseCase =
            new ManageEmployeesShiftController(
                    new ManageEmployeeShiftPresenterWeb(getViewModel()),
                    shiftRepository,
                    cinemaRepository,
                    hallRepository,
                    emailService);

    public static ManageEmployeeShiftViewModel getViewModel(){
        if(viewModel==null){
            viewModel = new ManageEmployeeShiftViewModel();
        }
        return viewModel;
    }
}
