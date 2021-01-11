package com.ttbmp.cinehub.data.local.utils.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Denotes that the annotated element of integer type, represents
 * a logical type and that its value should be one of the explicitly
 * named constants.
 */
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE})
public @interface IntDef {
    /**
     * Defines the allowed constants for this element
     */
    int[] value() default {};
}
