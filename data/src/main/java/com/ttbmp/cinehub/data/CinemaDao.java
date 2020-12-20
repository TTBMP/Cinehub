package com.ttbmp.cinehub.data;

import com.ttbmp.cinehub.data.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.data.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface CinemaDao {

    @Query("SELECT * FROM cinema")
    List<CinemaDto> getAllCinema() throws DaoMethodException;

    @Query("SELECT * FROM cinema WHERE nome = :n")
    CinemaDto getCinema(
            @Parameter(name = "n") @NotNull String name
    ) throws DaoMethodException;

    @Insert
    void insert(@NotNull CinemaDto cinema) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<CinemaDto> cinema) throws DaoMethodException;

    @Update
    void update(@NotNull CinemaDto cinema) throws DaoMethodException;

    @Update
    void update(@NotNull List<CinemaDto> cinema) throws DaoMethodException;

    @Delete
    void delete(@NotNull CinemaDto cinema) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<CinemaDto> cinema) throws DaoMethodException;

}
