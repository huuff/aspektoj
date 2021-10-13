package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ensures that the method is cached, where the key is a parameter annotated with @CacheKey and the value is the result
 * of the method.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {
    /**
     * @return A duration in ISO-8601 format that indicated the amount of time before this cache is invalidated.
     */
    String value();

    /**
     * @return The type of cache invalidation strategy to use, whether to invalidate on expired duration after last insertion
     * or on expiration after last access
     */
    Type type() default Type.LAST_INSERTED;

    enum Type {
        LAST_INSERTED, LAST_ACCESSED
    }
}
