package xyz.haff.aspektoj.aspects;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public aspect Benchmark {
    private static final DateTimeFormatter MILLIS_FORMATTER = DateTimeFormatter.ofPattern("SSS'ms'");
    private static final DateTimeFormatter SECONDS_FORMATTER = DateTimeFormatter.ofPattern("ss's' SSS'ms'");
    private static final DateTimeFormatter MINUTES_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

    Object around(): call(@xyz.haff.aspektoj.annotations.Benchmarked * *.*(..)) {
        var start = System.nanoTime();
        var result = proceed();
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

        System.out.println(thisJoinPoint.getSignature().toShortString() + " took " + fromMidnight.format(formatter));

        return result;
    }
}
