package com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method in a {@link Dao} annotated class as a delete method.
 * <p>
 * The implementation of the method will delete its parameters from the database.
 * <p>
 * All of the parameters of the Delete method must either be classes annotated with {@link Entity}
 * or collections/array of it.
 *
 * @see Insert
 * @see Update
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete {

}
