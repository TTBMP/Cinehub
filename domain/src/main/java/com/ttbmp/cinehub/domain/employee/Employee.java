package com.ttbmp.cinehub.domain.employee;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Employee extends User {

    private Cinema cinema;
    private List<Shift> shiftList;

    protected Employee(String id, String name, String surname, String email, Cinema cinema) {
        super(id, name, surname, email);
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public List<Shift> getShiftListBetween(LocalDate start, LocalDate end) {
        return shiftList.stream()
                .filter(shift -> LocalDate.parse(shift.getDate()).isAfter(start))
                .filter(shift -> LocalDate.parse(shift.getEnd()).isBefore(end))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
