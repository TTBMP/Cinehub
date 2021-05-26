package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Cinema;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public class CinemaDto {

    private int id;
    private String name;
    private String address;
    private String city;
    private List<HallDto> halList;

    public CinemaDto(Cinema cinema) {
        this.id = cinema.getId();
        this.name = cinema.getName();
        this.address = cinema.getAddress();
        this.city = cinema.getCity();
        this.halList = cinema.getHallList().stream()
                .map(HallDto::new)
                .collect(Collectors.toList());
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

    public List<HallDto> getHalList() {
        return halList;
    }

    public void setHalList(List<HallDto> halList) {
        this.halList = halList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (CinemaDto) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
