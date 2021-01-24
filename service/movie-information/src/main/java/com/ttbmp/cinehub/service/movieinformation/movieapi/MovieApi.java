package com.ttbmp.cinehub.service.movieinformation.movieapi;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.service.movie.MovieApiService;
import com.ttbmp.cinehub.service.movieinformation.datamapper.MovieApiDataMapper;
import com.ttbmp.cinehub.service.movieinformation.dto.MovieApiDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

    public List<Movie> returnListMovie() {
        return MovieApiDataMapper.mapToEntityList(listMovie);
    }

    @Override
    public void retriveAllMovie() {

        while (i <= numberOfMovie) {
            URL url = null;
            try {
                url = new URL(urlStart + i + apiKey);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            assert url != null;
            retriveMovie(url);
        }

    }

    @Override
    public void retriveMovieById(Integer id) {
        URL url = null;
        try {
            url = new URL(urlStart + id + apiKey);
        } catch (MalformedURLException e) {
            System.err.println("Non esiste un film con quell'id!");
        }
        assert url != null;
        retriveMovie(url);

    }


    @Override
    public void retriveMovie(URL url) {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert con != null;
        con.setDoOutput(true);
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        } catch (IOException e) {
            i++;
            retriveAllMovie();
        }
        if (i <= numberOfMovie) {
            String output = null;
            while (true) {
                try {
                    assert br != null;
                    if ((output = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                printSpecificAttribute(output);
                i++;
            }
        }

    }

    @Override
    public void printSpecificAttribute(String output) {
        JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
        String title = String.valueOf(jsonObject.get("title")).substring(1,String.valueOf(jsonObject.get("title")).length()-1);
        MovieApiDto element = new MovieApiDto(title);
        element.setImageUrl(imageUrl + String.valueOf(jsonObject.get("poster_path")).substring(1,String.valueOf(jsonObject.get("poster_path")).length()-1));
        element.setVote(String.valueOf(jsonObject.get("vote_average")));
        element.setOverview(String.valueOf(jsonObject.get("overview")));
        element.setRelases(String.valueOf(jsonObject.get("release_date")));
        listMovie.add(element);

    }

}


