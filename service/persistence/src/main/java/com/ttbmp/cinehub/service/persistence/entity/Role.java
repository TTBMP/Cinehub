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
@Entity(tableName = "ruolo")
public class Role {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nome")
    private String name;

}
