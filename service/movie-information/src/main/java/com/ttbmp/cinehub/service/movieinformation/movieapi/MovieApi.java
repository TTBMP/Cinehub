package com.ttbmp.cinehub.service.movieinformation.movieapi;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ttbmp.cinehub.core.dto.MovieDto;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.service.movieinformation.datamapper.MovieApiDataMapper;
import com.ttbmp.cinehub.service.movieinformation.dto.MovieApiDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MovieApi implements MovieApiService {
    Integer numberOfMovie = 30;
    Integer i = 10;
    List<MovieApiDto> listMovie = new ArrayList<>();
    String urlStart = "http://api.themoviedb.org/3/movie/";
    String apiKey = "?api_key=ee1cbb18100612b10051a3a6ab051d6f";
    String imageUrl = "https://image.tmdb.org/t/p/w300";

    public MovieApi() {
        //Empty section
    }

    public List<MovieDto> returnListMovie() {
        return MovieApiDataMapper.mapToDtoList(listMovie);
    }

    @Override
    public void retrieveAllMovie() throws IOException {

        while (i <= numberOfMovie) {
            retrieveMovie(new URL(urlStart + i + apiKey));
        }

    }

    @Override
    public void retrieveMovieById(Integer id) throws IOException {
        retrieveMovie(new URL(urlStart + id + apiKey));
    }


    @Override
    public void retrieveMovie(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        assert con != null;
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        } catch (IOException e) {
            i++;
            retrieveAllMovie();
        }
        if (i <= numberOfMovie) {
            String output;
            while (true) {
                assert br != null;
                if ((output = br.readLine()) == null) break;
                printSpecificAttribute(output);
                i++;
            }
        }

    }

    @Override
    public void printSpecificAttribute(String output) {
        JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
        String title = String.valueOf(jsonObject.get("title")).substring(1, String.valueOf(jsonObject.get("title")).length() - 1);
        MovieApiDto element = new MovieApiDto(title);
        element.setMovieImageUrl(imageUrl + String.valueOf(jsonObject.get("poster_path")).substring(1, String.valueOf(jsonObject.get("poster_path")).length() - 1));
        element.setMovieVote(String.valueOf(jsonObject.get("vote_average")));
        element.setMovieOverview(String.valueOf(jsonObject.get("overview")));
        element.setMovieReleases(String.valueOf(jsonObject.get("release_date")));
        listMovie.add(element);

    }

}


