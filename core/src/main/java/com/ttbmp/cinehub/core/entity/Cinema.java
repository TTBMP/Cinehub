package com.ttbmp.cinehub.core.entity;

import java.util.List;

public class Cinema {

    private String name;
    private String address;
    private String city;
    private List<Hall> hallList;


    public Cinema(){}

    public Cinema(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;

    }

    public void addHall(Hall hall) {
        this.hallList.add(hall);
    }


    public List<Hall> getHallList() {
        return hallList;
    }

    public void setHallList(List<Hall> hallList) {
        this.hallList = hallList;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
