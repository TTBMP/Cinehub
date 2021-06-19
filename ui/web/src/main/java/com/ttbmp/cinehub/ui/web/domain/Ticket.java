package com.ttbmp.cinehub.ui.web.domain;

import lombok.Data;

@Data
public class Ticket {

    private Boolean option1;
    private Boolean option2;
    private Boolean option3;
    private String position;
    private int number;
    private int cinemaId;
    private int movieId;
    private int hallId;
    private String date;

}
