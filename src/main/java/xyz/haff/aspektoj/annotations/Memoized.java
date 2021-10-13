package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a method without arguments (Presumably non-deterministic) to make it return the memoized value of a previous
 * execution instead of recalculating it.
 * Takes a value, a ISO-8601 description of a Duration.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Memoized {
    String value();
}
