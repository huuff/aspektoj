package xyz.haff.aspektoj.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

// TODO: Nonstatic class?

import java.lang.annotation.Annotation;

public class FindAnnotatedArgument {

    private FindAnnotatedArgument() {}

    public static <T> ArgumentAndAnnotation<T> of(JoinPoint joinPoint, Class<T> annotationClass) {
        var parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();

        for (int argIndex = 0; argIndex < parameterAnnotations.length; argIndex++) {
            for (var annotation : parameterAnnotations[argIndex])
                if (annotation.annotationType().equals(annotationClass))
                    return new ArgumentAndAnnotation<>(joinPoint.getArgs()[argIndex], annotationClass.cast(annotation));
        }

        throw new IllegalArgumentException("None of the methods of " + joinPoint.getSignature().toShortString() + " has the annotation " + annotationClass.toString());
    }

    public static class ArgumentAndAnnotation<T> {
        private final Object argument;
        private final T annotation;

        public ArgumentAndAnnotation(Object argument, T annotation) {
            this.argument = argument;
            this.annotation = annotation;
        }

        public Object getArgument() {
            return argument;
        }

        public T getAnnotation() {
            return annotation;
        }
    }
}
