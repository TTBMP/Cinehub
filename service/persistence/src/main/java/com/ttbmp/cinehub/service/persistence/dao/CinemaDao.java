package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Cinema;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Dao
public interface CinemaDao {

    @Query("SELECT * FROM cinema")
    List<Cinema> getAllCinema() throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE nome = :n")
    Cinema getCinema(
            @Parameter(name = "n") @NotNull String name
    ) throws DaoMethodException;

    @Insert
    void insert(@NotNull Cinema cinema) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Cinema> cinema) throws DaoMethodException;

    @Update
    void update(@NotNull Cinema cinema) throws DaoMethodException;

    @Update
    void update(@NotNull List<Cinema> cinema) throws DaoMethodException;

    @Delete
    void delete(@NotNull Cinema cinema) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Cinema> cinema) throws DaoMethodException;

}
