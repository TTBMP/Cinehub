package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Seat;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Dao
public interface SeatDao {

    @Query("SELECT * FROM posto WHERE posto.id = :id")
    Seat getSeat(@Parameter(name = "id") int id) throws DaoMethodException;

    @Query("SELECT * FROM posto WHERE posto.id IN " +
            "(SELECT biglietto.id_posto FROM biglietto WHERE biglietto.id = :id)")
    Seat getSeatByTicketId(@Parameter(name = "id") int idTicket) throws DaoMethodException;

    @Query("SELECT * FROM posto WHERE id_sala = :hallId ")
    List<Seat> getSeatList(@Parameter(name = "hallId") int hallId) throws DaoMethodException;

}
