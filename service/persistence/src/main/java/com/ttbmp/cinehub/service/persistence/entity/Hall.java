package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "sala")
public class Hall {

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_cinema")
    private int cinemaId;

    @ColumnInfo(name = "numero")
    private String number;

}
