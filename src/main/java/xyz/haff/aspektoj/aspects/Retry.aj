package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Retryable;

import java.util.Arrays;

aspect Retry {

    public pointcut retryable(Retryable retryable) : call(@Retryable * *.*(..)) && @annotation(retryable);

    Object around(Retryable retryable) : retryable(retryable) {
        int timesRetried = 0;

        while (timesRetried < retryable.times()) {
            try {
                return proceed(retryable);
            } catch (Throwable throwable) {
                if ((Arrays.stream(retryable.exceptions()).anyMatch(t -> t.equals(throwable.getClass())))) {
                    timesRetried++;
                } else {
                    throw new RuntimeException(throwable);
                }
            }
        }
        return null; // XXX: Should never happen, just so ajc is happy
    }
}
