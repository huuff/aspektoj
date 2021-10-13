package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Logs all calls to a method and all calls to methods made during its execution.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Traced {
    /**
     * @return The logger to which the trace info will be logged
     */
    String logger() default "stdout";
}
