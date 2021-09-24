package xyz.haff.aspektoj.util;

import java.time.Duration;

// TODO: Inline this?
public class DurationUtil {

    private DurationUtil() { }

    public static long getNanos(String durationString) {
        var duration = Duration.parse(durationString);
        return duration.toNanos();
    }
}
