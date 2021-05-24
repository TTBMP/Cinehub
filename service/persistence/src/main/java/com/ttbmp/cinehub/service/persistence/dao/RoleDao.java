package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Role;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface RoleDao {

    @Query("SELECT ruolo.* FROM ruolo, ruolo_utente WHERE ruolo.id = ruolo_utente.id_ruolo AND ruolo_utente.id_utente = :id")
    List<Role> getRoleList(@Parameter(name = "id") String userId) throws DaoMethodException;

    @Insert
    void insert(@NotNull Role role) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<Role> role) throws DaoMethodException;

    @Update
    void update(@NotNull Role role) throws DaoMethodException;

    @Update
    void update(@NotNull List<Role> role) throws DaoMethodException;

    @Delete
    void delete(@NotNull Role role) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<Role> role) throws DaoMethodException;

}
