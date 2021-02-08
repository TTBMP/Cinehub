package com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field in an {@link Entity} as the primary key.
 * <p>
 * If you would like to define a composite primary key, you should use {@link Entity#primaryKeys()}
 * method.
 * <p>
 * Each {@link Entity} must declare a primary key unless one of its super classes declares a
 * primary key. If both an {@link Entity} and its super class defines a {@code PrimaryKey}, the
 * child's {@code PrimaryKey} definition will override the parent's {@code PrimaryKey}.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {

    /**
     * Set to true to autogenerate the unique id.
     * <p>
     * If the field type is {@code long} or {@code int} (or its TypeConverter converts it to a
     * {@code long} or {@code int}), {@link Insert} methods treat {@code 0} as not-set while
     * inserting the item.
     * <p>
     * If the field's type is {@link Integer} or {@link Long} (or its TypeConverter converts it to
     * an {@link Integer} or a {@link Long}), {@link Insert} methods treat {@code null} as
     * not-set while inserting the item.
     *
     * @return Whether the primary key should be auto-generated or not. Defaults to false.
     */
    boolean autoGenerate() default false;
}
