package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.Request;

/**
 * @author Palmieri Ivan
 */
public class GetListCinemaRequest extends Request {
    public static final Request.Error MISSING_MOVIE_ERROR = new Request.Error("Cinema can't be null");
    public static final Request.Error MISSING_DATE_ERROR = new Request.Error("Date can't be null");

    private MovieDto movieDto;
    private String data;


    public GetListCinemaRequest(MovieDto movieDto, String data) {
        this.movieDto = movieDto;
        this.data = data;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }


    @Override
    public void onValidate() {
        if (movieDto == null) {
            addError(MISSING_MOVIE_ERROR);
        }
        if (data == null) {
            addError(MISSING_DATE_ERROR);
        }

    }
}
