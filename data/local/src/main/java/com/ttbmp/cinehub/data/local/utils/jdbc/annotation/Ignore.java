package com.ttbmp.cinehub.data.local.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ignores the marked element from processing logic.
 * <p>
 * This annotation can be used in multiple places where database processor runs. For instance, you can
 * add it to a field of an {@link Entity} and that field will not be persisted.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
}
