package com.ttbmp.cinehub.ui.desktop.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleHandler;
import com.ttbmp.cinehub.app.usecase.viewpersonalschedule.ViewPersonalScheduleUseCase;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.viewpersonalschedule.master.ScheduleView;

import java.io.IOException;

/**
 * @author Fabio Buracchi
 */
public class ViewPersonalScheduleActivity extends Activity {

    public ViewPersonalScheduleActivity() throws IOException {
        super(new ScheduleView());
        ViewPersonalScheduleViewModel viewModel = new ViewPersonalScheduleViewModel();
        ViewPersonalScheduleFxPresenter presenter = new ViewPersonalScheduleFxPresenter(viewModel);
        viewModelStore.put(ViewPersonalScheduleViewModel.class, viewModel);
        useCaseFactory.put(ViewPersonalScheduleUseCase.class, () -> new ViewPersonalScheduleHandler(presenter));
    }

}
