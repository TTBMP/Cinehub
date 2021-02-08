package com.ttbmp.cinehub.ui.desktop.di;

import com.ttbmp.cinehub.app.repository.ShiftRepository;
import com.ttbmp.cinehub.app.repository.mock.MockShiftRepository;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.mock.MockAuthenticationService;

public class MockAppContainer extends AppContainer {

    @Override
    protected void addDependenciesFactories() {
        dependencyFactoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        dependencyFactoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
    }

}
