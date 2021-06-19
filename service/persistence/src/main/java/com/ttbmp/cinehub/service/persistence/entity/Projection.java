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
@Entity(tableName = "proiezione")
public class Projection {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_sala")
    private int hallId;

    @ColumnInfo(name = "id_film")
    private int movieId;

    @ColumnInfo(name = "data")
    private String date;

    @ColumnInfo(name = "inizio")
    private String startTime;

    @ColumnInfo(name = "prezzo_base")
    private double basePrice;

}
