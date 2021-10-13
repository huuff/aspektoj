package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Indicates that a method is to be retried if some exception occurs during its execution
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retryable {
    /**
     * @return Array of exceptions that indicate that the method can be retried
     */
    Class<? extends Throwable>[] exceptions();

    /**
     * @return Maximum number of retries to do, anything above this and the exception is just propagated
     */
    int times() default 1;
}
