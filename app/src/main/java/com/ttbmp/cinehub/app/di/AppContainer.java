package com.ttbmp.cinehub.app.di;

import com.ttbmp.cinehub.app.utilities.FactoryMap;
import com.ttbmp.cinehub.app.utilities.InstanceMap;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.services.AuthenticationService;
import com.ttbmp.cinehub.data.mock.MockShiftRepository;
import com.ttbmp.cinehub.services.authentication.MockAuthenticationService;

/**
 * @author Fabio Buracchi
 */
public class AppContainer {

    protected final FactoryMap factoryMap = new FactoryMap();
    private final InstanceMap containerMap = new InstanceMap();

    public AppContainer() {
        addDependenciesFactories();
        addContainersFactories();
    }

    public <T> T getContainer(Class<T> containerClass) {
        if (containerMap.get(containerClass) == null) {
            containerMap.put(containerClass, factoryMap.get(containerClass).get());
        }
        return containerMap.get(containerClass);
    }

    public void destroyContainer(Class<?> containerClass) {
        containerMap.put(containerClass, null);
    }

    protected void addDependenciesFactories() {
        factoryMap.put(ShiftRepository.class, MockShiftRepository::new);
        factoryMap.put(AuthenticationService.class, MockAuthenticationService::new);
    }

    private void addContainersFactories() {
        factoryMap.put(
                ViewPersonalScheduleContainer.class,
                () -> new ViewPersonalScheduleContainer(
                        factoryMap.get(ShiftRepository.class).get(),
                        factoryMap.get(AuthenticationService.class).get()
                )
        );
    }

}
