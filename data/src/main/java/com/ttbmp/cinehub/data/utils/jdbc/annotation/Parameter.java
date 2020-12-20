package com.ttbmp.cinehub.data.utils.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

    /**
     * The parameter name of a prepare statement.
     *
     * @return the parameter name.
     */
    String name();

}
