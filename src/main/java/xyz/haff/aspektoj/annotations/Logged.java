package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Logs automatically all input parameters and the output of a method.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logged {
    /**
     * @return Name of the logger through which this information will be logged
     */
    String logger() default "stdout";
}
