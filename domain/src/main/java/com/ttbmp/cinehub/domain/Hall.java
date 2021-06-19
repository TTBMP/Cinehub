package com.ttbmp.cinehub.domain;

import lombok.*;

import java.util.List;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Hall {

    private int id;
    private List<Seat> seatList;
    private String name;

}
