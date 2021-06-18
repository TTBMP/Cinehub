package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.User;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM utente WHERE utente.id = :id")
    User getUserById(@Parameter(name = "id") @NotNull String name) throws DaoMethodException;

    @Query("SELECT * FROM utente WHERE utente.id IN " +
            "(SELECT biglietto.id_utente FROM biglietto WHERE biglietto.id = :idTicket)")
    User getUserByTicket(@Parameter(name = "idTicket") int idTicket) throws DaoMethodException;

    @Query("SELECT * FROM utente")
    List<User> getAllUser() throws DaoMethodException;

}
