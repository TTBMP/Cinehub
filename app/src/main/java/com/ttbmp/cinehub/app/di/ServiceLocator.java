package com.ttbmp.cinehub.app.di;

import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.cinema.JdbcCinemaRepository;
import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.creditcard.MockCreditCardRepository;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.JdbcEmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.MockProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.MockUsherRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.hall.JdbcHallRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.movie.JdbcMovieRepository;
import com.ttbmp.cinehub.app.repository.movie.MockMovieRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.projection.JdbcProjectionRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.JdbcSeatRepository;
import com.ttbmp.cinehub.app.repository.seat.MockSeatRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.shift.JdbcShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.usher.MockUsherShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftRepository;
import com.ttbmp.cinehub.app.repository.ticket.JdbcTicketRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.JdbcUserRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.authentication.MockAuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.MockEmailService;
import com.ttbmp.cinehub.app.service.movieapi.MockMovieApiService;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.app.service.payment.MockPaymentService;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.utilities.FactoryMap;

/**
 * @author Fabio Buracchi
 */
public class ServiceLocator {

    protected final FactoryMap<Object> serviceFactoryMap = new FactoryMap<>();

    public ServiceLocator() {
        addServicesFactory();
    }

    protected void addServicesFactory() {
        serviceFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
        serviceFactoryMap.put(EmailService.class, MockEmailService::new);
        serviceFactoryMap.put(MovieApiService.class, MockMovieApiService::new);
        serviceFactoryMap.put(PaymentService.class, MockPaymentService::new);
        serviceFactoryMap.put(CinemaRepository.class, () -> new JdbcCinemaRepository(this));
        serviceFactoryMap.put(CreditCardRepository.class, MockCreditCardRepository::new);
        serviceFactoryMap.put(EmployeeRepository.class, () -> new JdbcEmployeeRepository(this));
        serviceFactoryMap.put(ProjectionistRepository.class, () -> new MockProjectionistRepository(this));
        serviceFactoryMap.put(UsherRepository.class, () -> new MockUsherRepository(this));
        serviceFactoryMap.put(HallRepository.class, () -> new JdbcHallRepository(this));
        serviceFactoryMap.put(MovieRepository.class, () -> new JdbcMovieRepository(this));
        serviceFactoryMap.put(ProjectionRepository.class, () -> new JdbcProjectionRepository(this));
        serviceFactoryMap.put(SeatRepository.class,()-> new  JdbcSeatRepository(this));
        serviceFactoryMap.put(ShiftRepository.class, () -> new JdbcShiftRepository(this));
        serviceFactoryMap.put(ProjectionistShiftRepository.class, () -> new MockProjectionistShiftRepository(this));
        serviceFactoryMap.put(UsherShiftRepository.class, MockUsherShiftRepository::new);
        serviceFactoryMap.put(TicketRepository.class, () -> new JdbcTicketRepository(this));
        serviceFactoryMap.put(UserRepository.class, () -> new JdbcUserRepository(this));
    }

    public <T> T getService(Class<T> serviceClass) {
        return serviceFactoryMap.get(serviceClass).get();
    }

}
