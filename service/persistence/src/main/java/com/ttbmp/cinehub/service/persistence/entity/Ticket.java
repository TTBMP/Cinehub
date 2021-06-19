package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Palmieri
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "biglietto")
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_posto")
    private int seatId;

    @ColumnInfo(name = "id_proiezione")
    private int projectionId;

    @ColumnInfo(name = "id_utente")
    private String userId;

    @ColumnInfo(name = "prezzo")
    private long price;

}
