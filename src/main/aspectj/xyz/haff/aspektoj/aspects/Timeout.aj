package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Timed;
import xyz.haff.aspektoj.exceptions.RuntimeTimeoutException;

import java.time.Duration;
import java.util.concurrent.*;

public aspect Timeout {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public pointcut timed(Timed timed) : execution(@Timed * *.*(..)) && @annotation(timed);

    Object around(Timed timed): timed(timed) {
        Future<Object> result = executor.submit(() -> proceed(timed));
        try {
            return result.get(Duration.parse(timed.value()).toNanos(), TimeUnit.NANOSECONDS);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeTimeoutException();
        }
    }
}
