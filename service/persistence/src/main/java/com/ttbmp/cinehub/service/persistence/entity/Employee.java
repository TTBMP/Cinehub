package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Palmieri
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "dipendente")
public class Employee {

    @ColumnInfo(name = "id_utente")
    private String idUser;

    @ColumnInfo(name = "id_cinema")
    private int cinemaId;

}
