package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fabio Buracchi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "cinema")
public class Cinema {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nome")
    private String name;

    @ColumnInfo(name = "indirizzo")
    private String address;

    @ColumnInfo(name = "citta")
    private String city;

}
