package com.ttbmp.cinehub.service.movieapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TheMovieDbApiServiceTest {

    @Test
    void getMovie_withAValidInteger_notGenerateThrows() {
        TheMovieDbApiService service = new TheMovieDbApiService();
        Assertions.assertDoesNotThrow(() -> service.getMovie(3));
    }


    @Test
    void getMovie_withInvalidInteger_generateThrows() {
        TheMovieDbApiService service = new TheMovieDbApiService();
        Assertions.assertThrows(TheMovieDbApiException.class, () -> service.getMovie(7800));
    }
}