package com.ttbmp.cinehub.ui.web.domain;

import lombok.Data;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
@Data
public class Projection {

    private int id;
    private String date;
    private String startTime;
    private int hallId;
    private int movieId;
    private long basePrice;

}
