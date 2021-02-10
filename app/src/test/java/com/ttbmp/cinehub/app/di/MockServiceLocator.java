package com.ttbmp.cinehub.app.di;

import com.ttbmp.cinehub.app.repository.*;
import com.ttbmp.cinehub.app.repository.mock.*;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.authentication.MockAuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.MockEmailService;
import com.ttbmp.cinehub.app.service.movieapi.MockMovieApiService;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.app.service.payment.MockPaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentService;

public class MockServiceLocator extends ServiceLocator {

    @Override
    public void addServiceFactory() {
        serviceFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        serviceFactoryMap.put(UserRepository.class, MockUserRepository::new);
        serviceFactoryMap.put(EmployeeRepository.class, MockEmployeeRepository::new);
        serviceFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        serviceFactoryMap.put(HallRepository.class, MockHallRepository::new);
        serviceFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
        serviceFactoryMap.put(EmailService.class, MockEmailService::new);
        serviceFactoryMap.put(PaymentService.class, MockPaymentService::new);
        serviceFactoryMap.put(MovieApiService.class, MockMovieApiService::new);
        serviceFactoryMap.put(MovieRepository.class, MockMovieRepository::new);
        serviceFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        serviceFactoryMap.put(ProjectionRepository.class, MockProjectionRepository::new);
        serviceFactoryMap.put(TicketRepository.class, MockTicketRepository::new);
    }

}
