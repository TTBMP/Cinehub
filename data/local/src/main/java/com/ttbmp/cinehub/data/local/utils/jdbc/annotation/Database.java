package com.ttbmp.cinehub.data.local.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a Database.
 * <p>
 * Instead of running queries on the database directly, you are highly recommended to create
 * {@link Dao} classes. Using Dao classes will allow you to abstract the database communication in
 * a more logical layer which will be much easier to mock in tests (compared to running direct
 * SQL queries). You don't need to deal with lower level database APIs for most of your data
 * access.
 *
 * @see Dao
 * @see Entity
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Database {
    /**
     * The url of the database.
     *
     * @return The database url.
     */
    String url();

    /**
     * The username which will login to the database.
     *
     * @return the username which will login to the database.
     */
    String user();

    /**
     * The password to login to the database.
     *
     * @return the password to login to the database.
     */
    String password();

    /**
     * The database timezone.
     *
     * @return the database timezone.
     */
    String timezone() default "";

    /**
     * The driver class name.
     *
     * @return the driver class name.
     */
    String driverClassName();

    /**
     * The database version.
     *
     * @return The database version.
     */
    int version();

    /**
     * The list of entities included in the database. Each entity turns into a table in the
     * database.
     *
     * @return The list of entities in the database.
     */
    Class<?>[] entities();

}
