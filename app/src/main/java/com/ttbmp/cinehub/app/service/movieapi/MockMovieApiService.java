package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.domain.Movie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public class MockMovieApiService implements MovieApiService {

    private final Map<Integer, Movie> movieMap = new HashMap<>();

    public MockMovieApiService() {
        initialize();
    }

    private void initialize() {
        movieMap.put(15, new Movie(
                22,
                "Citizen Kane",
                "Newspaper magnate, Charles Foster Kane is taken from his mother as a boy and made the ward of a rich industrialist. As a result, every well-meaning, tyrannical or self-destructive move he makes for the rest of his life appears in some way to be a reaction to that deeply wounding event.",
                119,
                "https://image.tmdb.org/t/p/w300/zO5OI25cieQ6ncpvGOD4U72vi1o.jpg",
                "8",
                "1941-04-17"
        ));
        movieMap.put(16, new Movie(
                16,
                "Dancer in the Dark",
                "Selma, a Czech immigrant on the verge of blindness, struggles to make ends meet for herself and her son, who has inherited the same genetic disorder and will suffer the same fate without an expensive operation. When life gets too difficult, Selma learns to cope through her love of musicals, escaping life's troubles - even if just for a moment - by dreaming up little numbers to the rhythmic beats of her surroundings.",
                141,
                "https://image.tmdb.org/t/p/w300/9rsivF4sWfmBzrNr4LPu6TNJhXX.jpg",
                "7.9",
                "2000-05-17"
        ));
        movieMap.put(17, new Movie(
                17,
                "The Dark",
                "Adèle and her daughter Sarah are traveling on the Welsh coastline to see her husband James when Sarah disappears. A different but similar looking girl appears who says she died in a past time. Adèle tries to discover what happened to her daughter as she is tormented by Celtic mythology from the past.",
                87,
                "https://image.tmdb.org/t/p/w300/wZeBHVnCvaS2bwkb8jFQ0PwZwXq.jpg",
                "5.9",
                "2005-09-28"
        ));
        movieMap.put(18, new Movie(
                18,
                "The Fifth Element",
                "In 2257, a taxi driver is unintentionally given the task of saving a young girl who is part of the key that will ensure the survival of humanity.",
                126,
                "https://image.tmdb.org/t/p/w300/fPtlCO1yQtnoLHOwKtWz7db6RGU.jpg",
                "7.5",
                "1997-05-02"
        ));
        movieMap.put(19, new Movie(
                19,
                "Metropolis",
                "In a futuristic city sharply divided between the working class and the city planners, the son of the city's mastermind falls in love with a working class prophet who predicts the coming of a savior to mediate their differences.",
                149,
                "https://image.tmdb.org/t/p/w300/hUK9rewffKGqtXynH5SW3v9hzcu.jpg",
                "8.2",
                "1927-02-06"
        ));
        movieMap.put(20, new Movie(
                20,
                "My Life Without Me",
                "A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness.",
                106,
                "https://image.tmdb.org/t/p/w300/sFSkn5rrQqXJkRNa2rMWqzmEuhR.jpg",
                "5.9",
                "2003-03-07"
        ));
        movieMap.put(21, new Movie(
                21,
                "The Endless Summer",
                "Bruce Brown's The Endless Summer is one of the first and most influential surf movies of all time. The film documents American surfers Mike Hynson and Robert August as they travel the world during California’s winter (which, back in 1965 was off-season for surfing) in search of the perfect wave and ultimately, an endless summer.",
                106,
                "https://image.tmdb.org/t/p/w300/5W5uaqQ7NZTwoDMKO4AtdcahHex.jpg",
                "7.5",
                "1966-06-15"
        ));
        movieMap.put(22, new Movie(
                22,
                "Pirates of the Caribbean: The Curse of the Black Pearl",
                "Jack Sparrow, a freewheeling 18th-century pirate, quarrels with a rival pirate bent on pillaging Port Royal. When the governor's daughter is kidnapped, Sparrow decides to help the girl's love save her.",
                143,
                "https://image.tmdb.org/t/p/w300/z8onk7LV9Mmw6zKz4hT6pzzvmvl.jpg",
                "7.7",
                "2003-07-09"
        ));
    }

    @Override
    public Movie getMovie(int movieId) throws MovieApiServiceException {
        return movieMap.get(movieId);
    }

}
