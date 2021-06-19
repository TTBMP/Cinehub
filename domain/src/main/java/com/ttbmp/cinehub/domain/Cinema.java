package com.ttbmp.cinehub.domain;

import lombok.*;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Cinema {

    private int id;
    private String name;
    private String city;
    private String address;
    private List<Hall> hallList;

}
