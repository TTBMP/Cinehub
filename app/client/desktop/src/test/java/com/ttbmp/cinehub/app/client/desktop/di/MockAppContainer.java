package com.ttbmp.cinehub.app.client.desktop.di;

import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;
import com.ttbmp.cinehub.data.local.mock.MockShiftRepository;
import com.ttbmp.cinehub.service.authentication.MockAuthenticationService;

public class MockAppContainer extends AppContainer {

    @Override
    protected void addDependenciesFactories() {
        factoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        factoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
    }

}
