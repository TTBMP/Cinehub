package com.ttbmp.cinehub.service.persistence.dao;


import com.ttbmp.cinehub.service.persistence.entity.User;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Dao
public interface UserDao {


    @Query("SELECT * FROM utente WHERE utente.id = :id")
    User getUserById(
            @Parameter(name = "id") @NotNull String name
    ) throws DaoMethodException;

    @Query("SELECT * FROM utente where utente.id in (" +
            " SELECT biglietto.id_utente FROM biglietto where biglietto.id = :idTicket)")
    User getUserByTicket(
            @Parameter(name = "idTicket") @NotNull int idTicket
    ) throws DaoMethodException;


    @Insert
    void insert(@NotNull User user) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<User> user) throws DaoMethodException;

    @Update
    void update(@NotNull User user) throws DaoMethodException;

    @Update
    void update(@NotNull List<User> user) throws DaoMethodException;

    @Delete
    void delete(@NotNull User user) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<User> user) throws DaoMethodException;

}
