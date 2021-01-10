package com.ttbmp.cinehub.app.dto;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi
 */
public class ShiftDto {

    private final StringProperty employeeName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> start = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> end = new SimpleObjectProperty<>();

    public ShiftDto(String employeeName, LocalDate date, LocalTime start, LocalTime end) {
        this.employeeName.setValue(employeeName);
        this.date.setValue(date);
        this.start.setValue(start);
        this.end.setValue(end);
    }

    public String getEmployeeName() {
        return employeeName.get();
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }

    public StringProperty employeeNameProperty() {
        return employeeName;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalTime getStart() {
        return start.get();
    }

    public void setStart(LocalTime start) {
        this.start.set(start);
    }

    public ObjectProperty<LocalTime> startProperty() {
        return start;
    }

    public LocalTime getEnd() {
        return end.get();
    }

    public void setEnd(LocalTime end) {
        this.end.set(end);
    }

    public ObjectProperty<LocalTime> endProperty() {
        return end;
    }
}
