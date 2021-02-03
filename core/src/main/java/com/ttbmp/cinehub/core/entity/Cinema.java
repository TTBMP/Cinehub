package com.ttbmp.cinehub.core.entity;

import java.util.List;

/**
 * @author Palmieri Ivan
 */

public class Cinema {
    private int id;
    private String name;
    private String address;
    private String city;
    private List<Hall> hallList;

    public Cinema() {
    }

    public Cinema(int id, String name, String address, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Cinema(int id, String name, String address, String city, List<Hall> hallList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.hallList = hallList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Cinema other = (Cinema) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
