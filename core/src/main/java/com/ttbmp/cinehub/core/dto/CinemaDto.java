package com.ttbmp.cinehub.core.dto;


/**
 * @author Palmieri Ivan
 */
public class CinemaDto {
    private String name;
    private final String address;
    private final String city;

    public CinemaDto(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
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

    public String getCity() {
        return city;
    }


}
