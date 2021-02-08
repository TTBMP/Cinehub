package com.ttbmp.cinehub.ui.desktop.di;

import com.ttbmp.cinehub.domain.repository.ShiftRepository;
import com.ttbmp.cinehub.domain.repository.mock.MockShiftRepository;
import com.ttbmp.cinehub.domain.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.domain.service.authentication.mock.MockAuthenticationService;

public class MockAppContainer extends AppContainer {

    @Override
    protected void addDependenciesFactories() {
        dependencyFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        dependencyFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
    }

}
