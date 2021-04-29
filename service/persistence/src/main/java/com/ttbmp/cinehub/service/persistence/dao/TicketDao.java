package com.ttbmp.cinehub.service.persistence.dao;


import com.ttbmp.cinehub.service.persistence.entity.Cinema;
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
    List<Ticket> getTicketList(
            @Parameter(name = "projectionId") @NotNull Integer projectionId
    )throws DaoMethodException;


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
