package com.ttbmp.cinehub.ui.desktop.ui.buyticket.payment;


import javafx.application.Platform;
import javafx.beans.property.*;

import java.util.TimerTask;

public class TimeOut extends TimerTask {
    static IntegerProperty duration = new SimpleIntegerProperty(300);
    static StringProperty durationString = new SimpleStringProperty();
    static BooleanProperty finished = new SimpleBooleanProperty(false);

    public static void format() {
       duration = new SimpleIntegerProperty(300);
       durationString =  new SimpleStringProperty();
        finished = new SimpleBooleanProperty(false);
    }

    @Override
    public void run() {
        Platform.runLater(()-> {
            durationString.setValue("Remaining session time: " +duration.getValue());
            if (duration.getValue() > 0) {
                duration.setValue(duration.getValue() - 1);
            }
            else{
                finished.setValue(true);
            }
        });
    }

}
