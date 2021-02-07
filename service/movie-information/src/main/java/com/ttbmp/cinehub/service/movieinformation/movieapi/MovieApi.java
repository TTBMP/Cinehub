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
    private final List<MovieApiDto> listMovie = new ArrayList<>();
    private static final String URL_START = "http://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "?api_key=ee1cbb18100612b10051a3a6ab051d6f";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w300";


    @Override
    public List<MovieDto> getAllMovie() throws IOException {
        return MovieApiDataMapper.mapToDtoList(listMovie);
    }

    @Override
    public MovieDto getMovie(int movieId) throws IOException {
        HttpURLConnection connection = getConnection(new URL(URL_START + movieId + API_KEY));
        return MovieApiDataMapper.mapToDto(getMovie(connection));
    }


    private HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        assert con != null;
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        return con;
    }

    private MovieApiDto getMovie(HttpURLConnection con) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output;
        MovieApiDto element = new MovieApiDto();
        assert br != null;
        if ((output = br.readLine()) != null) {
            JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
            element.setMovieName(String.valueOf(jsonObject.get("title")).substring(1, String.valueOf(jsonObject.get("title")).length() - 1));
            element.setId(jsonObject.get("id").getAsInt());
            element.setMovieImageUrl(IMAGE_URL + String.valueOf(jsonObject.get("poster_path")).substring(1, String.valueOf(jsonObject.get("poster_path")).length() - 1));
            element.setMovieVote(String.valueOf(jsonObject.get("vote_average")));
            element.setMovieOverview(String.valueOf(jsonObject.get("overview")));
            element.setMovieReleases(String.valueOf(jsonObject.get("release_date")));
        }
        return element;
    }


}


