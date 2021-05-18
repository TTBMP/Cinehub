package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Seat;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Dao
public interface SeatDao {

    @Query("SELECT * FROM posto WHERE posto.id = :id")
    Seat getSeat(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM posto WHERE posto.id IN " +
            "(SELECT biglietto.id_posto FROM biglietto WHERE biglietto.id = :idTicket)")
    Seat getSeatByTicketId(@Parameter(name = "idTicket") int idTicket) throws DaoMethodException;

    @Query("SELECT * FROM posto WHERE id_sala = :hallId ")
    List<Seat> getSeatList(@Parameter(name = "hallId") int hallId) throws DaoMethodException;

    @Insert
    void insert(@NotNull Seat seat) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Seat> seat) throws DaoMethodException;

    @Update
    void update(@NotNull Seat seat) throws DaoMethodException;

    @Update
    void update(@NotNull List<Seat> seat) throws DaoMethodException;

    @Delete
    void delete(@NotNull Seat seat) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Seat> seat) throws DaoMethodException;

}
