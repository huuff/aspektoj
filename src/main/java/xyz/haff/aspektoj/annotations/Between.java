package xyz.haff.aspektoj.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a number parameter with the minimum and maximum expected integer values.
 * If they are not met, throws IllegalArgumentException
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Between {
    /**
     * @return Minimum acceptable value for the annotated parameter
     */
    int min();

    /**
     * @return Maximum acceptable value for the annotated parameter
     */
    int max();
}
