package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Ticket;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Insert;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
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

    @Insert
    void insert(@NotNull Ticket ticket) throws DaoMethodException;

}
