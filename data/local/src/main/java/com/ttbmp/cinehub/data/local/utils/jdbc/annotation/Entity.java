package com.ttbmp.cinehub.data.local.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as an entity. This class will have a mapping table in the database.
 * <p>
 * Each entity must have at least 1 field annotated with {@link PrimaryKey}.
 * You can also use {@link #primaryKeys()} attribute to define the primary key.
 * <p>
 * Each entity must either have a no-arg constructor or a constructor whose parameters match
 * fields (based on type and name). Constructor does not have to receive all fields as parameters
 * but if a field is not passed into the constructor, it should either be public or have a public
 * setter.
 * <p>
 * When a class is marked as an Entity, all of its fields are persisted. If you would like to
 * exclude some of its fields, you can mark them with {@link Ignore}.
 * <p>
 * If a field is {@code transient}, it is automatically ignored <b>unless</b> it is annotated with
 * {@link ColumnInfo}.
 *
 * @see Dao
 * @see Database
 * @see PrimaryKey
 * @see ColumnInfo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {

    /**
     * The table name in the database.
     *
     * @return The tableName of the Entity.
     */
    String tableName();

}
