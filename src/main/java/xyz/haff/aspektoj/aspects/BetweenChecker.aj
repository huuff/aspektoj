package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Between;
import xyz.haff.aspektoj.util.AnnotatedArgument;

public aspect BetweenChecker {
    public pointcut annotated(): call(* *.*(.., @Between (*), ..));

    before(): annotated() {
        var annotatedArgument = new AnnotatedArgument<>(thisJoinPoint, Between.class);
        var argument = annotatedArgument.getArgument();
        var annotation = annotatedArgument.getAnnotation();

        if (!(argument instanceof Number))
            throw new IllegalArgumentException("The argument annotated with @Between: " + argument + " is not a number");

        var intValue = ((Number) argument).intValue();

        if (intValue < annotation.min() || intValue > annotation.max()) {
            throw new IllegalArgumentException("The argument " + argument + " is not between " + annotation.min() + " and " + annotation.max());
        }
    }

}
