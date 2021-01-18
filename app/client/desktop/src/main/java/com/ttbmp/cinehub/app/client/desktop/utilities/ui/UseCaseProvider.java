package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

import com.ttbmp.cinehub.core.usecase.UseCase;

public interface UseCaseProvider {

    <T extends UseCase> T getUseCase(Class<T> useCaseClass);

}
