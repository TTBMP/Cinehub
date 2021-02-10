package com.ttbmp.cinehub.app.di;

import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.movie.MockMovieRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.authentication.MockAuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.MockEmailService;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.app.service.movieapi.TheMovieDbApiServiceAdapter;
import com.ttbmp.cinehub.app.service.payment.MockPaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.utilities.FactoryMap;

public class ServiceLocator {

    protected final FactoryMap<Object> serviceFactoryMap = new FactoryMap<>();

    public ServiceLocator() {
        addServiceFactory();
    }

    protected void addServiceFactory() {
        serviceFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        serviceFactoryMap.put(UserRepository.class, MockUserRepository::new);
        serviceFactoryMap.put(EmployeeRepository.class, MockEmployeeRepository::new);
        serviceFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        serviceFactoryMap.put(HallRepository.class, MockHallRepository::new);
        serviceFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
        serviceFactoryMap.put(EmailService.class, MockEmailService::new);
        serviceFactoryMap.put(PaymentService.class, MockPaymentService::new);
        serviceFactoryMap.put(MovieApiService.class, TheMovieDbApiServiceAdapter::new);
        serviceFactoryMap.put(MovieRepository.class, MockMovieRepository::new);
        serviceFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        serviceFactoryMap.put(ProjectionRepository.class, MockProjectionRepository::new);
        serviceFactoryMap.put(TicketRepository.class, MockTicketRepository::new);
    }

    public <T> T getService(Class<T> factoryClass) {
        return serviceFactoryMap.get(factoryClass).get();
    }

}
