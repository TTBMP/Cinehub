package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class ShiftDto {

    private int id;
    private String employeeId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    protected ShiftDto(Shift shift) {
        this.id = shift.getId();
        this.employeeId = shift.getEmployee().getId();
        this.date = LocalDate.parse(shift.getDate());
        this.start = LocalTime.parse(shift.getStart());
        this.end = LocalTime.parse(shift.getEnd());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (ShiftDto) obj;
        return employeeId.equals(other.employeeId)
                && date.equals(other.date)
                && start.equals(other.start)
                && end.equals(other.end);
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode() + date.hashCode() + start.hashCode() + end.hashCode();
    }

}
