package com.ttbmp.cinehub.ui.web.di;


import com.ttbmp.cinehub.app.repository.*;
import com.ttbmp.cinehub.app.repository.mock.*;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.mock.MockAuthenticationService;
import com.ttbmp.cinehub.app.service.mock.MockEmailService;
import com.ttbmp.cinehub.app.service.movie.MovieApiService;
import com.ttbmp.cinehub.app.service.payment.PaymentService;
import com.ttbmp.cinehub.app.utilities.FactoryMap;

import java.util.function.Supplier;

public class AppContainer {

    protected final FactoryMap<Object> dependencyFactoryMap = new FactoryMap<>();

    public AppContainer() {
        addDependenciesFactories();
    }

    protected void addDependenciesFactories() {
        dependencyFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        dependencyFactoryMap.put(UserRepository.class, MockUserRepository::new);
        dependencyFactoryMap.put(EmployeeRepository.class, MockEmployeeRepository::new);
        dependencyFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
        dependencyFactoryMap.put(EmailService.class, MockEmailService::new);
        dependencyFactoryMap.put(HallRepository.class, MockHallRepository::new);
/*        dependencyFactoryMap.put(PaymentService.class, StripeServicePayment::new);
        dependencyFactoryMap.put(MovieApiService.class, MovieApi::new);
        dependencyFactoryMap.put(MovieRepository.class, () -> new MockMovieRepository(dependencyFactoryMap.get(MovieApiService.class).get()));*/
        dependencyFactoryMap.put(CinemaRepository.class, MockCinemaRepository::new);
        dependencyFactoryMap.put(ProjectionRepository.class, MockProjectionRepository::new);

    }

    public <T> Supplier<T> getFactory(Class<T> factoryClass) {
        return dependencyFactoryMap.get(factoryClass);
    }
}
