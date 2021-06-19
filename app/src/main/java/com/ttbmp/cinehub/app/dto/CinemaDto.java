package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Cinema;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
@Value
public class CinemaDto {

    int id;
    String name;
    String address;
    String city;
    List<HallDto> halList;

    public CinemaDto(Cinema cinema) {
        this.id = cinema.getId();
        this.name = cinema.getName();
        this.address = cinema.getAddress();
        this.city = cinema.getCity();
        this.halList = cinema.getHallList().stream()
                .map(HallDto::new)
                .collect(Collectors.toList());
    }

}
