package com.ttbmp.cinehub.ui.web.domain;

import lombok.Data;

@Data
public class Movie {

    private int id;
    private String name;
    private String overview;
    private String movieUrl;
    private String vote;
    private String releases;

}
