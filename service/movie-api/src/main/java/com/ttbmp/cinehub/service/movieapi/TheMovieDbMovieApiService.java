package com.ttbmp.cinehub.service.movieapi;


import com.google.gson.Gson;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.service.movieapi.datamapper.MovieApiDataMapper;
import com.ttbmp.cinehub.service.movieapi.dto.MovieApiDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public class TheMovieDbMovieApiService implements MovieApiService {

    private static final String URL_START = "http://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "?api_key=ee1cbb18100612b10051a3a6ab051d6f";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w300";

    @Override
    public MovieDto getMovie(int movieId) throws IOException {
        Reader reader = getJsonReader(new URL(URL_START + movieId + API_KEY));;
        MovieApiDto movieApiDto = new Gson().fromJson(reader, MovieApiDto.class);
        movieApiDto.setMovieImageUrl(IMAGE_URL + movieApiDto.getMovieImageUrl());
        return MovieApiDataMapper.mapToDto(movieApiDto);
    }


    private Reader getJsonReader(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        return new BufferedReader(new InputStreamReader((connection.getInputStream())));
    }

}


