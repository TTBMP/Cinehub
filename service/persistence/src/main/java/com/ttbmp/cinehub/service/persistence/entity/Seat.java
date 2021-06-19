package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Palmieri Ivan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "posto")
public class Seat {

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_sala")
    private int hallId;

    @ColumnInfo(name = "posizione")
    private String position;

}
