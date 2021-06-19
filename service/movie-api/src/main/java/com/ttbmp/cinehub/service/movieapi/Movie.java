package com.ttbmp.cinehub.service.movieapi;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
@Data
public class Movie {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String name;

    @SerializedName("vote_average")
    private String vote;

    @SerializedName("poster_path")
    private String imageUrl;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releases;

    @SerializedName("runtime")
    private String duration;

}

