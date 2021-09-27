package xyz.haff.aspektoj.aspects;


import org.apache.logging.log4j.ThreadContext;
import xyz.haff.aspektoj.annotations.ContextVar;
import xyz.haff.aspektoj.annotations.LogContext;
import xyz.haff.aspektoj.util.FindAnnotatedArgument;

/**
 * Appends the parameter defined in the annotation to the log context and then removes it
 */
public aspect AppendLogContext {
    public pointcut logContext(): call(@LogContext * *.*(@ContextVar (*), ..));

    Object around(): logContext() {
        var contextVar = FindAnnotatedArgument.of(thisJoinPoint, ContextVar.class);

        ThreadContext.put(contextVar.getAnnotation().value(), contextVar.getArgument().toString());
        var result = proceed();
        ThreadContext.remove(contextVar.getAnnotation().value());

        return result;
    }
}
