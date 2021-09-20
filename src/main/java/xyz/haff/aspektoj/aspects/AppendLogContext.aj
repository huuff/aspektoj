package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.reflect.MethodSignature;
import xyz.haff.aspektoj.annotations.LogContext;
import xyz.haff.aspektoj.annotations.ContextVar;

/**
 * Appends the parameter defined in the annotation to the log context and then removes it
 */
public aspect AppendLogContext {
    public pointcut logContext(): call(@LogContext * *.*(@ContextVar (*), ..));

    Object around(): logContext() {
        var contextVar = thisJoinPoint.getArgs()[0];
        var contextVarAnnotation = (ContextVar) ((MethodSignature) thisJoinPoint.getSignature()).getMethod().getParameterAnnotations()[0][0];

        ThreadContext.put(contextVarAnnotation.value(), contextVar.toString());
        var result = proceed();
        ThreadContext.remove(contextVarAnnotation.value());

        return result;
    }
}
