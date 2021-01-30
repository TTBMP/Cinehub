package com.ttbmp.cinehub.service.movieinformation.mock;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.service.movieinformation.datamapper.MovieApiDataMapper;
import com.ttbmp.cinehub.service.movieinformation.dto.MovieApiDto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieApiService implements MovieApiService {

    List<MovieApiDto> listMovie = new ArrayList<>();
    @Override
    public List<MovieDto> getAllMovie() throws IOException {
        connect(new URL(""));
        return MovieApiDataMapper.mapToDtoList(listMovie);
    }


    @Override
    public void connect(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        setMovie(con);
    }

    @Override
    public void setMovie(HttpURLConnection con) {
        String output = null;
        JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
        String title = String.valueOf(jsonObject.get(""));
        MovieApiDto element = new MovieApiDto(title);
        element.setMovieImageUrl("");
        element.setMovieVote("");
        element.setMovieOverview("");
        element.setMovieReleases("");
        listMovie.add(element);
    }

}
