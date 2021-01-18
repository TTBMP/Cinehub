package com.ttbmp.cinehub.app.client.desktop.di;

import com.ttbmp.cinehub.app.client.desktop.utilities.FactoryMap;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;
import com.ttbmp.cinehub.data.local.mock.MockShiftRepository;
import com.ttbmp.cinehub.service.authentication.MockAuthenticationService;

import java.util.function.Supplier;

public class AppContainer {

    protected final FactoryMap<Object> dependencyFactoryMap = new FactoryMap<>();

    public AppContainer() {
        addDependenciesFactories();
    }

    protected void addDependenciesFactories() {
        dependencyFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        dependencyFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
    }

    public <T> Supplier<T> getFactory(Class<T> factoryClass) {
        return dependencyFactoryMap.get(factoryClass);
    }

}
