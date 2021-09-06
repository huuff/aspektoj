package xyz.haff.aspektoj.aspects;

// TODO: Add choosing duration of memoization

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public aspect Memoize pertarget(memoized()) {
    private static final Duration CACHE_TIME = Duration.ofHours(1);

    private final Map<String, Object> cache = new ConcurrentHashMap<>();
    private final Map<String, Long> lastUpdate = new ConcurrentHashMap<>();

    public pointcut memoized() : call(@xyz.haff.aspektoj.annotations.Memoized * *.*());

    Object around(): memoized() {
        var key = thisJoinPoint.getSignature().toShortString();

        if (cache.containsKey(key) && !isOutdated(key))
            return cache.get(key);
        else {
            var result = proceed();
            cache.put(key, result);
            lastUpdate.put(key, System.nanoTime());
            return result;
        }
    }

    private boolean isOutdated(String key) {
        var lastUpdate = this.lastUpdate.get(key);
        var elapsed = System.nanoTime() - lastUpdate;

        return elapsed > CACHE_TIME.toNanos();
    }
}
