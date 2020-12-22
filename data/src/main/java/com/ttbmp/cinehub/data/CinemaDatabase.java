package com.ttbmp.cinehub.data;

import com.ttbmp.cinehub.data.utils.jdbc.annotation.Database;
import com.ttbmp.cinehub.data.utils.jdbc.datasource.JdbcDataSource;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DataSourceMethodException;

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
                CinemaDto.class
        }
)
public interface CinemaDatabase extends JdbcDataSource {

    CinemaDao getCinemaDao() throws DataSourceMethodException;

}
