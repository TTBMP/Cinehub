package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

import com.ttbmp.cinehub.app.client.desktop.utilities.FactoryMap;
import com.ttbmp.cinehub.app.client.desktop.utilities.InstanceMap;
import com.ttbmp.cinehub.core.usecase.UseCase;

public abstract class Activity implements ViewModelStoreOwner, UseCaseProvider {

    protected final InstanceMap<ViewModel> viewModelStore = new InstanceMap<>();
    protected final FactoryMap<UseCase> useCaseFactory = new FactoryMap<>();
    private final View initialView;

    protected Activity(View initialView) {
        this.initialView = initialView;
    }

    public View getInitialView() {
        return initialView;
    }

    @Override
    public <T extends ViewModel> T getViewModel(Class<T> viewModelClass) {
        return viewModelStore.get(viewModelClass);
    }

    @Override
    public <T extends UseCase> T getUseCase(Class<T> useCaseClass) {
        return useCaseFactory.get(useCaseClass).get();
    }

}
