package com.ttbmp.cinehub.app.client.desktop.di;

import com.ttbmp.cinehub.core.repository.*;
import com.ttbmp.cinehub.core.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.utilities.FactoryMap;
import com.ttbmp.cinehub.data.local.mock.*;
import com.ttbmp.cinehub.service.authentication.MockAuthenticationService;
import com.ttbmp.cinehub.service.email.mock.MockEmailService;
import com.ttbmp.cinehub.service.movieinformation.movieapi.MovieApi;
import com.ttbmp.cinehub.service.payment.stripe.StripeServicePayment;

import java.util.function.Supplier;

/**
 * @author Fabio Buracchi
 */
public class AppContainer {

    protected final FactoryMap<Object> dependencyFactoryMap = new FactoryMap<>();

    public AppContainer() {
        addDependenciesFactories();
    }

    protected void addDependenciesFactories() {
        dependencyFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        dependencyFactoryMap.put(UserRepository.class, MockUserRepository::new);
        dependencyFactoryMap.put(EmployeeRepository.class, MockEmployeeRepository::new);
        dependencyFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        dependencyFactoryMap.put(HallRepository.class, MockHallRepository::new);
        dependencyFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
        dependencyFactoryMap.put(EmailService.class, MockEmailService::new);
        dependencyFactoryMap.put(PaymentService.class, StripeServicePayment::new);
        dependencyFactoryMap.put(MovieApiService.class, MovieApi::new);
        dependencyFactoryMap.put(MovieRepository.class, () -> new MockMovieRepository(dependencyFactoryMap.get(MovieApiService.class).get()));
        dependencyFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        dependencyFactoryMap.put(ProjectionRepository.class, MockProjectionRepository::new);


    }

    public <T> Supplier<T> getFactory(Class<T> factoryClass) {
        return dependencyFactoryMap.get(factoryClass);
    }

}