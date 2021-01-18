package com.ttbmp.cinehub.app.client.desktop.utilities.ui;

public interface ViewModelStoreOwner {

    <T extends ViewModel> T getViewModel(Class<T> viewModelClass);

}
