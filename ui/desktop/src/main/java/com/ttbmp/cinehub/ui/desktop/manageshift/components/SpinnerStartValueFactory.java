package com.ttbmp.cinehub.ui.desktop.manageshift.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalTime;
import java.time.format.FormatStyle;

/**
 * @author Massimo Mazzetti
 */
public class SpinnerStartValueFactory extends SpinnerValueFactory<LocalTime> {

    private final ObjectProperty<LocalTime> end = new SimpleObjectProperty<>();

    public SpinnerStartValueFactory(ObjectProperty<LocalTime> start, ObjectProperty<LocalTime> end) {
        setConverter(new LocalTimeStringConverter(FormatStyle.SHORT));
        this.valueProperty().bindBidirectional(start);
        this.end.bind(end);
    }

    @Override
    public void decrement(int steps) {
        if (getValue() == null)
            setValue(LocalTime.NOON.minusNanos(0));
        else {
            LocalTime time = getValue();
            if (time.isAfter(LocalTime.MIN))
                setValue(time.minusMinutes(5));
        }
    }

    @Override
    public void increment(int steps) {
        if (getValue() == null)
            setValue(LocalTime.NOON.minusNanos(0));
        else {
            LocalTime time = getValue();
            if (time.equals(LocalTime.MIN) || time.isAfter(LocalTime.MIN) &&
                    (time.isBefore(end.getValue().minusMinutes(5))))
                setValue(time.plusMinutes(5));
        }
    }

}
