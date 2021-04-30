package com.ttbmp.cinehub.domain;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class Cinema {

    private int id;
    private String name;
    private String city;
    private String address;
    private List<Hall> hallList;

    public Cinema(int id, String name, String city, String address, List<Hall> hallList) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.hallList = hallList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Hall> getHallList() {
        return hallList;
    }

    public void setHallList(List<Hall> hallList) {
        this.hallList = hallList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (Cinema) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
