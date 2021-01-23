package com.ttbmp.cinehub.app.client.desktop.ui.manageshift;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalTime;
import java.time.format.FormatStyle;

public class SpinnerEndValueFactory extends SpinnerValueFactory<LocalTime> {
    private final ObjectProperty<LocalTime> start=new SimpleObjectProperty<>();

    public SpinnerEndValueFactory(ObjectProperty<LocalTime> start, ObjectProperty<LocalTime> end) {
        setConverter(new LocalTimeStringConverter(FormatStyle.SHORT));
        this.valueProperty().bindBidirectional(end);
        this.start.bind(start);
    }


    @Override
    public void decrement(int steps) {
        if (start != null && getValue() == null)
            setValue(start.getValue().plusHours(1));
        else if (start == null)
            setValue(LocalTime.NOON.minusNanos(0));
        else {
            LocalTime time = getValue();
            if (time.isAfter(start.getValue().plusMinutes(5)) || time.equals(LocalTime.MIN))
                setValue(time.minusMinutes(5));
        }
    }

    @Override
    public void increment(int steps) {
        if (start != null && getValue() == null)
            setValue(start.getValue().plusHours(1));
        else if (start == null)
            setValue(LocalTime.NOON.minusNanos(0));
        else {
            LocalTime time = getValue();
            if (time.isAfter(start.getValue()) && time.isBefore(LocalTime.MAX.minusMinutes(5)))
                setValue(time.plusMinutes(5));
        }
    }

}
