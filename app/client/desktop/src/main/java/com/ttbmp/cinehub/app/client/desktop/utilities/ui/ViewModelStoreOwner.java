package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

/**
 * @author Fabio Buracchi
 */
public interface ViewModelStoreOwner {

    <T extends ViewModel> T getViewModel(Class<T> viewModelClass);

}
