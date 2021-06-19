package com.ttbmp.cinehub.ui.desktop.utilities.ui;

import com.ttbmp.cinehub.app.utilities.usecase.UseCase;

/**
 * @author Fabio Buracchi
 */
public interface UseCaseProvider {

    <T extends UseCase> T getUseCase(Class<T> useCaseClass);

}
