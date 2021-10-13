package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Indicates that a method is to be retried if any of the exceptions in the `exceptions` parameter is thrown.
 * The `times` parameter defines the number of times to retry
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retryable {
    Class<? extends Throwable>[] exceptions();
    int times() default 1;
}
