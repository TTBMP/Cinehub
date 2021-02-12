package com.ttbmp.cinehub.ui.desktop.about;

import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;

import java.io.IOException;
/**
 * @author Palmieri Ivan
 */
public class AboutActivity extends Activity {
    public AboutActivity() throws IOException {
        super(new AboutView());
    }
}
