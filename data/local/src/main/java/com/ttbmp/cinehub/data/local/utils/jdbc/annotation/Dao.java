package com.ttbmp.cinehub.data.local.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks the class as a Data Access Object.
 * <p>
 * Data Access Objects are the main classes where you define your database interactions. They can
 * include a variety of query methods.
 * <p>
 * It is recommended to have multiple {@code Dao} classes in your codebase depending on the tables
 * they touch.
 *
 * @see Query
 * @see Delete
 * @see Insert
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dao {
}
