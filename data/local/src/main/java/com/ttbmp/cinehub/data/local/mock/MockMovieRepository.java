package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Movie;
import com.ttbmp.cinehub.core.entity.Projection;
import com.ttbmp.cinehub.core.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockMovieRepository implements MovieRepository {


    @Override
    public List<Movie> getMovieList(List<Projection> projectionList, List<Movie> movieList) {
        List<Movie> result = new ArrayList<>();
        for (Projection projection : projectionList) {
            for (Movie movie : movieList) {
                if (projection.getMovie().getName().equals(movie.getName())) {
                    result.add(movie);
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                if (result.get(i).getName().equals(result.get(j).getName())) {
                    result.remove(j);
                    break;
                }
            }
        }
        return result;
    }


}
