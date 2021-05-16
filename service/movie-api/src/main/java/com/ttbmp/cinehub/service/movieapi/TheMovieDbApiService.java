package com.ttbmp.cinehub.service.movieapi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public class TheMovieDbApiService {

    private static final String URL_START = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "?api_key=ee1cbb18100612b10051a3a6ab051d6f";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w300";

    public Movie getMovie(int movieId) throws TheMovieDbApiException {
        Movie result;
        try {
            var reader = getJsonReader(new URL(URL_START + movieId + API_KEY));
            result = new Gson().fromJson(reader, Movie.class);
            result.setImageUrl(IMAGE_URL + result.getImageUrl());
        } catch (Exception e) {
            throw new TheMovieDbApiException(e.getMessage());
        }
        return result;
    }

    private Reader getJsonReader(URL url) throws TheMovieDbApiException {
        Reader result;
        try {
            var connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            result = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        } catch (Exception e) {
            throw new TheMovieDbApiException(e.getMessage());
        }
        return result;
    }

}


