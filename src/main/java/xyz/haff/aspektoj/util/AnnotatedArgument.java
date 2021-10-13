package xyz.haff.aspektoj.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Find the argument with an specified annotation of a method
 * @param <T>
 */

public class AnnotatedArgument<T> {
    private final Object argument;
    private final T annotation;

    public AnnotatedArgument(JoinPoint joinPoint, Class<T> annotationClass) {
        var parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();

        for (int argIndex = 0; argIndex < parameterAnnotations.length; argIndex++) {
            for (var annotation : parameterAnnotations[argIndex])
                if (annotation.annotationType().equals(annotationClass)) {
                    this.argument = joinPoint.getArgs()[argIndex];
                    this.annotation = annotationClass.cast(annotation);
                    return;
                }
        }

        throw new IllegalArgumentException("None of the methods of " + joinPoint.getSignature().toShortString() + " has the annotation " + annotationClass.toString());
    }

    public Object getArgument() {
        return argument;
    }

    public T getAnnotation() {
        return annotation;
    }
}
