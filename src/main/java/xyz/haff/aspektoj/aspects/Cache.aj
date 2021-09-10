package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Cached;
import xyz.haff.aspektoj.annotations.CacheKey;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public aspect Cache {
    private final Map<Object, Object> cache = new ConcurrentHashMap<>();
    private final Map<Object, Long> lastUpdate = new ConcurrentHashMap<>();

    public pointcut cached(Cached cached): call(@Cached * *.*(@CacheKey (*), ..)) && @annotation(cached);

    Object around(Cached cached): cached(cached) {
        var key = thisJoinPoint.getArgs()[0];

        var cacheDuration = Duration.parse(cached.value());

        if (cache.containsKey(key) && !isOutdated(key, cacheDuration))
            return cache.get(key);
        else {
            var result = proceed(cached);
            cache.put(key, result);
            lastUpdate.put(key, System.nanoTime());
            return result;
        }
    }

    private boolean isOutdated(Object key, Duration cacheDuration) {
        var lastUpdate = this.lastUpdate.get(key);
        var elapsed = System.nanoTime() - lastUpdate;

        return elapsed > cacheDuration.toNanos();
    }
}
