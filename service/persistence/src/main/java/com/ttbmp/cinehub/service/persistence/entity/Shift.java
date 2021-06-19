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
@Entity(tableName = "turno")
public class Shift {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "inizio")
    private String start;

    @ColumnInfo(name = "fine")
    private String end;

    @ColumnInfo(name = "id_dipendente")
    private String userId;

    @ColumnInfo(name = "data")
    private String date;

}
