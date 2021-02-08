package com.ttbmp.cinehub.ui.desktop.di;

import com.ttbmp.cinehub.domain.di.DependencyContainer;
import com.ttbmp.cinehub.domain.repository.*;
import com.ttbmp.cinehub.domain.repository.mock.*;
import com.ttbmp.cinehub.domain.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.domain.service.authentication.mock.MockAuthenticationService;
import com.ttbmp.cinehub.domain.service.email.EmailService;
import com.ttbmp.cinehub.domain.service.email.mock.MockEmailService;
import com.ttbmp.cinehub.domain.service.movie.MovieApiService;
import com.ttbmp.cinehub.domain.service.payment.PaymentService;
import com.ttbmp.cinehub.domain.service.payment.mock.MockPaymentService;
import com.ttbmp.cinehub.service.movieinformation.movieapi.TheMovieDbMovieApiService;

/**
 * @author Fabio Buracchi
 */
public class AppContainer extends DependencyContainer {

    @Override
    protected void addDependenciesFactories() {
        dependencyFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        dependencyFactoryMap.put(UserRepository.class, MockUserRepository::new);
        dependencyFactoryMap.put(EmployeeRepository.class, MockEmployeeRepository::new);
        dependencyFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        dependencyFactoryMap.put(HallRepository.class, MockHallRepository::new);
        dependencyFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
        dependencyFactoryMap.put(EmailService.class, MockEmailService::new);
        dependencyFactoryMap.put(PaymentService.class, MockPaymentService::new);
        dependencyFactoryMap.put(MovieApiService.class, TheMovieDbMovieApiService::new);
        dependencyFactoryMap.put(MovieRepository.class, () -> new MockMovieRepository(dependencyFactoryMap.get(MovieApiService.class).get()));
        dependencyFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        dependencyFactoryMap.put(ProjectionRepository.class, MockProjectionRepository::new);
    }

}