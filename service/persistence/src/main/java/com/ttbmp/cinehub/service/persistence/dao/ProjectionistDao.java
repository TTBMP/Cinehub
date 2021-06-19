package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.Employee;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Parameter;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Query;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

@Dao
public interface ProjectionistDao {

    @Query("SELECT dipendente.* " +
            "FROM cinemadb.dipendente, cinemadb.proiezione, cinemadb.turno_proiezionista, cinemadb.sala, cinemadb.turno " +
            "WHERE " +
            "proiezione.id = :id AND " +
            "proiezione.id_sala = sala.id AND " +
            "sala.id = turno_proiezionista.id_sala AND " +
            "turno_proiezionista.id_turno = turno.id AND " +
            "turno.id_dipendente = dipendente.id_utente AND " +
            "proiezione.inizio BETWEEN turno.inizio AND turno.fine AND " +
            "turno.data = proiezione.data")
    Employee getProjectionistByProjectionId(@Parameter(name = "id") int id) throws DaoMethodException;

}
