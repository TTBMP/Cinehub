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
public class SpinnerEndValueFactory extends SpinnerValueFactory<LocalTime> {
    private final ObjectProperty<LocalTime> start = new SimpleObjectProperty<>();

    public SpinnerEndValueFactory(ObjectProperty<LocalTime> start, ObjectProperty<LocalTime> end) {
        setConverter(new LocalTimeStringConverter(FormatStyle.SHORT));
        this.valueProperty().bindBidirectional(end);
        this.start.bind(start);
    }


    @Override
    public void decrement(int steps) {
        if (getValue() == null)
            setValue(start.getValue().plusHours(1));
        else {
            var time = getValue();
            if (time.isAfter(start.getValue().plusMinutes(5)) || time.isBefore(LocalTime.MIN))
                setValue(time.minusMinutes(5));
        }
    }

    @Override
    public void increment(int steps) {
        if (getValue() == null)
            setValue(start.getValue().plusHours(1));

        else {
            var time = getValue();
            if (time.isAfter(start.getValue()) && time.isBefore(LocalTime.MAX.minusMinutes(5)))
                setValue(time.plusMinutes(5));
        }
    }

}
