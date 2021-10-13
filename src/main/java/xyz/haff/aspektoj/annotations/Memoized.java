package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a method without arguments (Presumably non-deterministic) to make it return the memoized value of a previous
 * execution instead of recalculating it.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Memoized {
    /**
     * @return An ISO-8601 duration for which to memoize the result of the method
     */
    String value();
}
