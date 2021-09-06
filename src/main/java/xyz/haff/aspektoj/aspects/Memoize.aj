package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Memoized;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO: Make a generic cache class for using this with a cache aspect

public aspect Memoize pertarget(memoized(*)) {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();
    private final Map<String, Long> lastUpdate = new ConcurrentHashMap<>();

    public pointcut memoized(Memoized memoized) : call(@Memoized * *.*()) && @annotation(memoized) ;

    Object around(Memoized memoized): memoized(memoized) {
        var key = thisJoinPoint.getSignature().toShortString();
        var cacheDuration = Duration.parse(memoized.value());

        if (cache.containsKey(key) && !isOutdated(key, cacheDuration))
            return cache.get(key);
        else {
            var result = proceed(memoized);
            cache.put(key, result);
            lastUpdate.put(key, System.nanoTime());
            return result;
        }
    }

    private boolean isOutdated(String key, Duration cacheDuration) {
        var lastUpdate = this.lastUpdate.get(key);
        var elapsed = System.nanoTime() - lastUpdate;

        return elapsed > cacheDuration.toNanos();
    }
}
