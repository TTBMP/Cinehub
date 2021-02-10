package com.ttbmp.cinehub.app.dto;

import java.util.List;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public class CinemaDto {

    private final int id;
    private final String name;
    private final String address;
    private final String city;
    private final List<HallDto> halList;

    public CinemaDto(int id, String name, String address, String city, List<HallDto> halList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.halList = halList;
    }



    public int getId() {
        return id;
    }

    public List<HallDto> getHalList() {
        return halList;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        CinemaDto other = (CinemaDto) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
