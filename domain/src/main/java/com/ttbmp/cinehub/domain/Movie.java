package com.ttbmp.cinehub.domain;

import lombok.*;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Movie {

    private int id;
    private String name;
    private String overview;
    private int duration;
    private String imageUrl;
    private String rating;
    private String releaseDate;

}

