package com.ttbmp.cinehub.app.di;

import com.ttbmp.cinehub.core.repository.RequestRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;
import com.ttbmp.cinehub.data.mock.MockRequestRepository;
import com.ttbmp.cinehub.data.mock.MockShiftRepository;
import com.ttbmp.cinehub.services.authentication.MockAuthenticationService;

public class MockAppContainer extends AppContainer {

    @Override
    protected void addDependenciesFactories() {
        factoryMap.put(RequestRepository.class, MockRequestRepository::new);
        factoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        factoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
    }

}
