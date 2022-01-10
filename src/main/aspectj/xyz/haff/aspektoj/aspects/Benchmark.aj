package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import xyz.haff.aspektoj.annotations.Benchmarked;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public aspect Benchmark {
    private static final DateTimeFormatter MILLIS_FORMATTER = DateTimeFormatter.ofPattern("SSS'ms'");
    private static final DateTimeFormatter SECONDS_FORMATTER = DateTimeFormatter.ofPattern("ss's' SSS'ms'");
    private static final DateTimeFormatter MINUTES_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

    pointcut annotated(Benchmarked benchmarked): call(@xyz.haff.aspektoj.annotations.Benchmarked * *.*(..)) && @annotation(benchmarked);

    Object around(Benchmarked benchmarked): annotated(benchmarked) {
        var start = System.nanoTime();
        var result = proceed(benchmarked);
        var end = System.nanoTime();

        var elapsedNanos = end - start;
        var duration = Duration.ofNanos(end - start);

        // HACK to use `DateTimeFormatter`
        var fromMidnight = LocalTime.of(0, 0).plus(duration);

        var elapsedMillis = elapsedNanos / 1000000;
        DateTimeFormatter formatter = null;
        if (elapsedMillis < 1000)
            formatter = MILLIS_FORMATTER;
        else if (elapsedMillis < 60000)
            formatter = SECONDS_FORMATTER;
        else
            formatter = MINUTES_FORMATTER;

        var logger = LogManager.getLogger(benchmarked.logger());
        logger.info(thisJoinPoint.getSignature().toShortString() + " took " + fromMidnight.format(formatter));

        return result;
    }
}
