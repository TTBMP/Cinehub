package com.ttbmp.cinehub.service.persistence;

import com.ttbmp.cinehub.service.persistence.dao.MovieDao;
import com.ttbmp.cinehub.service.persistence.entity.Cinema;
import com.ttbmp.cinehub.service.persistence.dao.CinemaDao;
import com.ttbmp.cinehub.service.persistence.entity.Movie;
import com.ttbmp.cinehub.service.persistence.entity.Projection;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSource;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DataSourceMethodException;

/**
 * @author Fabio Buracchi
 */
@Database(
        version = 1,
        url = "mysql://localhost:3306/cinemadb",
        user = "admin",
        password = "admin",
        timezone = "Europe/Rome",
        driverClassName = "com.mysql.cj.jdbc.Driver",
        entities = {
                Cinema.class,
                Movie.class,
                Projection.class
        }
)
public interface CinemaDatabase extends JdbcDataSource {

    CinemaDao getCinemaDao() throws DataSourceMethodException;

    MovieDao getMovieDao() throws DataSourceMethodException;

}
