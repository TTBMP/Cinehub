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
@Entity(tableName = "utente")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "nome")
    private String name;

    @ColumnInfo(name = "cognome")
    private String surname;

    @ColumnInfo(name = "email")
    private String email;

}
