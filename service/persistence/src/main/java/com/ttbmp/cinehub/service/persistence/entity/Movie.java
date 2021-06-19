package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fabio Buracchi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "film")
public class Movie {

    @ColumnInfo(name = "id")
    private int id;

}
