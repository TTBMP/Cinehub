package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Role;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

@Dao
public interface RoleDao {

    @Query("SELECT ruolo.* FROM ruolo, ruolo_utente WHERE ruolo.id = ruolo_utente.id_ruolo AND ruolo_utente.id_utente = :id")
    List<Role> getRoleList(@Parameter(name = "id") String userId) throws DaoMethodException;

}
