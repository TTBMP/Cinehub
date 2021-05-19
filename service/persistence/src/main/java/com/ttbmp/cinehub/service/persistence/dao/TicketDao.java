package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Ticket;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
@Dao
public interface TicketDao {

    @Query("SELECT * FROM biglietto WHERE biglietto.id_proiezione = :projectionId")
    List<Ticket> getTicketList(@Parameter(name = "projectionId") int projectionId) throws DaoMethodException;

    @Query("SELECT * FROM biglietto WHERE biglietto.id_utente = :customerId")
    List<Ticket> getTicketList(@Parameter(name = "customerId") @NotNull String customerId) throws DaoMethodException;

    @Query("SELECT * FROM biglietto WHERE biglietto.id_posto = :idSeat AND biglietto.id_proiezione = :idProjection")
    Ticket getTicketById(@Parameter(name = "idSeat") @NotNull int idSeat,
                         @Parameter(name = "idProjection") @NotNull int idProjection) throws DaoMethodException;



    @Insert
    void insert(@NotNull Ticket ticket) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Ticket> ticket) throws DaoMethodException;

    @Update
    void update(@NotNull Ticket ticket) throws DaoMethodException;

    @Update
    void update(@NotNull List<Ticket> ticket) throws DaoMethodException;

    @Delete
    void delete(@NotNull Ticket ticket) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Ticket> ticket) throws DaoMethodException;

}
