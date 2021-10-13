package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import xyz.haff.aspektoj.annotations.Logged;

public aspect Log {

    pointcut annotated(Logged logged): call(@xyz.haff.aspektoj.annotations.Logged * *.*(..)) && @annotation(logged);

    Object around(Logged logged): annotated(logged) {
        var logger = LogManager.getLogger(logged.logger());

        logger.info("Calling " + thisJoinPoint.getSignature());
        for (Object arg : thisJoinPoint.getArgs()) {
            logger.info("Input: " + arg.toString());
        }
        var result = proceed(logged);

        logger.info("Output: " + result.toString());
        return result;
    }
}
