package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import xyz.haff.aspektoj.annotations.Traced;


public aspect Trace {
    pointcut annotated(Traced traced): call(@xyz.haff.aspektoj.annotations.Traced * *.*(..)) && @annotation(traced);
    
    pointcut traced(Traced traced): call(* *.*(..))
                        && (cflow(annotated(traced)))
                        && !within(Trace)
                        ;

    before(Traced traced): traced(traced) {
        Logger logger = LogManager.getLogger(traced.logger());
        logger.trace("Calling " + thisJoinPoint.getSignature() + "...");
        ThreadContext.push("\t");
    }

    after(Traced traced): traced(traced) {
        ThreadContext.pop();
    }
}
