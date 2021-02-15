package com.ttbmp.cinehub.app.service.movieapi;

import com.ttbmp.cinehub.domain.Movie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockMovieApiService implements MovieApiService {

    private final Map<Integer, Movie> movieMap = new HashMap<>();

    public MockMovieApiService() {
        initialize();
    }

    private void initialize() {
        movieMap.put(3, new Movie(
                3,
                "Shadows in Paradise",
                "An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more.",
                74,
                "https://image.tmdb.org/t/p/w300/nj01hspawPof0mJmlgfjuLyJuRN.jpg",
                "7.2",
                "1986-10-17"
        ));
        movieMap.put(5, new Movie(
                5,
                "Four Rooms",
                "It's Ted the Bellhop's first night on the job...and the hotel's very unusual guests are about to place him in some outrageous predicaments. It seems that this evening's room service is serving up one unbelievable happening after another.",
                98,
                "https://image.tmdb.org/t/p/w300/75aHn1NOYXh4M7L5shoeQ6NGykP.jpg",
                "5.7",
                "1995-12-09"
        ));
        movieMap.put(6, new Movie(
                6,
                "Judgment Night",
                "While racing to a boxing match, Frank, Mike, John and Rey get more than they bargained for. A wrong turn lands them directly in the path of Fallon, a vicious, wise-cracking drug lord. After accidentally witnessing Fallon murder a disloyal henchman, the four become his unwilling prey in a savage game of cat & mouse as they are mercilessly stalked through the urban jungle in this taut suspense drama",
                110,
                "https://image.tmdb.org/t/p/w300/rYFAvSPlQUCebayLcxyK79yvtvV.jpg",
                "6.5",
                "1993-10-15"
        ));
        movieMap.put(8, new Movie(
                8,
                "Life in Loops (A Megacities RMX)",
                "Timo Novotny labels his new project an experimental music documentary film, in a remix of the celebrated film Megacities (1997), a visually refined essay on the hidden faces of several world \\\"megacities\\\" by leading Austrian documentarist Michael Glawogger. Novotny complements 30 % of material taken straight from the film (and re-edited) with 70 % as yet unseen footage in which he blends original shots unused by Glawogger with his own sequences (shot by Megacities cameraman Wolfgang Thaler) from Tokyo. Alongside the Japanese metropolis, Life in Loops takes us right into the atmosphere of Mexico City, New York, Moscow and Bombay. This electrifying combination of fascinating film images and an equally compelling soundtrack from Sofa Surfers sets us off on a stunning audiovisual adventure across the continents. The film also makes an original contribution to the discussion on new trends in documentary filmmaking. Written by KARLOVY VARY IFF 2006",
                80,
                "https://image.tmdb.org/t/p/w300/x7Sz339F2oC8mBf0DHCQpKizXaL.jpg",
                "7.3",
                "2006-01-01"
        ));
        movieMap.put(11, new Movie(
                11,
                "Star Wars",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                121,
                "https://image.tmdb.org/t/p/w300/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",
                "8.2",
                "1977-05-25"
        ));
        movieMap.put(15, new Movie(
                15,
                "Citizen Kane",
                "Newspaper magnate, Charles Foster Kane is taken from his mother as a boy and made the ward of a rich industrialist. As a result, every well-meaning, tyrannical or self-destructive move he makes for the rest of his life appears in some way to be a reaction to that deeply wounding event.",
                119,
                "https://image.tmdb.org/t/p/w300/sav0jxhqiH0bPr2vZFU0Kjt2nZL.jpg",
                "8.1",
                "1941-04-17"
        ));
    }

    @Override
    public Movie getMovie(int movieId) throws MovieApiServiceException {
        return movieMap.get(movieId);
    }

}
