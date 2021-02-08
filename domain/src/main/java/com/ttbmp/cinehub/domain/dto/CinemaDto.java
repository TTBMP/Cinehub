package com.ttbmp.cinehub.domain.dto;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class CinemaDto {
    private final String address;
    private final String city;
    private final List<HallDto> halList;
    private int id;
    private String name;

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

    public void setId(int id) {
        this.id = id;
    }

    public List<HallDto> getHalList() {
        return halList;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CinemaDto other = (CinemaDto) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }

}
