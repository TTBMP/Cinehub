package com.ttbmp.cinehub.core.entity;

public class Employee extends User {

    private Cinema cinema;
    private int weeklyWorkingHours;

    public Employee() {
        setName("");
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getWeeklyWorkingHours() {
        return weeklyWorkingHours;
    }

    public void setWeeklyWorkingHours(int weeklyWorkingHours) {
        this.weeklyWorkingHours = weeklyWorkingHours;
    }

}
