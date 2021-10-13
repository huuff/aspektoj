package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ensures that the method is cached, where the key is a parameter annotated with @CacheKey and the value is the result
 * of the method.
 * Takes a value, a string that represents the duration in ISO-8601 format.
 * Takes a Cached.Type enum, that designates whether to invalidate cache on duration elapsed since insertion or since
 * last access.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {
    String value();
    Type type() default Type.LAST_INSERTED;

    enum Type {
        LAST_INSERTED, LAST_ACCESSED
    }
}
