package com.ttbmp.cinehub.app.repository.mock;

import com.ttbmp.cinehub.app.repository.MovieRepository;
import com.ttbmp.cinehub.app.repository.ProjectionRepository;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class MockMovieRepository implements MovieRepository {

    private static final Map<Integer, Movie> movieMap = new HashMap<>();

    static {
        movieMap.put(3, new Movie(
                3,
                "Shadows in Paradise",
                "7.2",
                "https://image.tmdb.org/t/p/w300/nj01hspawPof0mJmlgfjuLyJuRN.jpg",
                74,
                "An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more.",
                "1986-10-17"
        ));
        movieMap.put(5, new Movie(
                5,
                "Four Rooms",
                "5.7",
                "https://image.tmdb.org/t/p/w300/75aHn1NOYXh4M7L5shoeQ6NGykP.jpg",
                98,
                "It's Ted the Bellhop's first night on the job...and the hotel's very unusual guests are about to place him in some outrageous predicaments. It seems that this evening's room service is serving up one unbelievable happening after another.",
                "1995-12-09"
        ));
        movieMap.put(6, new Movie(
                6,
                "Judgment Night",
                "6.5",
                "https://image.tmdb.org/t/p/w300/rYFAvSPlQUCebayLcxyK79yvtvV.jpg",
                110,
                "While racing to a boxing match, Frank, Mike, John and Rey get more than they bargained for. A wrong turn lands them directly in the path of Fallon, a vicious, wise-cracking drug lord. After accidentally witnessing Fallon murder a disloyal henchman, the four become his unwilling prey in a savage game of cat & mouse as they are mercilessly stalked through the urban jungle in this taut suspense drama",
                "1993-10-15"
        ));
        movieMap.put(8, new Movie(
                8,
                "Life in Loops (A Megacities RMX)",
                "7.3",
                "https://image.tmdb.org/t/p/w300/x7Sz339F2oC8mBf0DHCQpKizXaL.jpg",
                80,
                "Timo Novotny labels his new project an experimental music documentary film, in a remix of the celebrated film Megacities (1997), a visually refined essay on the hidden faces of several world \\\"megacities\\\" by leading Austrian documentarist Michael Glawogger. Novotny complements 30 % of material taken straight from the film (and re-edited) with 70 % as yet unseen footage in which he blends original shots unused by Glawogger with his own sequences (shot by Megacities cameraman Wolfgang Thaler) from Tokyo. Alongside the Japanese metropolis, Life in Loops takes us right into the atmosphere of Mexico City, New York, Moscow and Bombay. This electrifying combination of fascinating film images and an equally compelling soundtrack from Sofa Surfers sets us off on a stunning audiovisual adventure across the continents. The film also makes an original contribution to the discussion on new trends in documentary filmmaking. Written by KARLOVY VARY IFF 2006",
                "2006-01-01"
        ));
        movieMap.put(11, new Movie(
                11,
                "Star Wars",
                "8.2",
                "https://image.tmdb.org/t/p/w300/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",
                121,
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "1977-05-25"
        ));
        movieMap.put(15, new Movie(
                15,
                "Citizen Kane",
                "8.1",
                "https://image.tmdb.org/t/p/w300/sav0jxhqiH0bPr2vZFU0Kjt2nZL.jpg",
                119,
                "Newspaper magnate, Charles Foster Kane is taken from his mother as a boy and made the ward of a rich industrialist. As a result, every well-meaning, tyrannical or self-destructive move he makes for the rest of his life appears in some way to be a reaction to that deeply wounding event.",
                "1941-04-17"
        ));
    }

    public static List<Movie> getAllMovie() {
        return new ArrayList<>(movieMap.values());
    }

    @Override
    public List<Movie> getMovieList(String localDate) {
        ProjectionRepository projectionRepository = new MockProjectionRepository();
        List<Movie> result = new ArrayList<>();
        List<Projection> projectionList = projectionRepository.getProjectionList(localDate);
        for (Projection projection : projectionList) {
            Movie movie = projection.getMovie();
            if (!result.contains(movie)) {
                result.add(movie);
            }
        }
        return result;
    }

}
