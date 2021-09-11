package xyz.haff.aspektoj.util;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CacheMap {
    private final Map<Object, Object> cache = new ConcurrentHashMap<>();
    private final Map<Object, Long> lastUpdate = new ConcurrentHashMap<>();

    public Object put(Object key, Object value) {
        cache.put(key, value);
        lastUpdate.put(key, System.nanoTime());
        return value;
    }

    public Optional<Object> get(Object key, String cacheDurationString) {
        var cacheDuration = Duration.parse(cacheDurationString);

        if (cache.containsKey(key) && !isOutdated(key, cacheDuration))
            return Optional.of(cache.get(key));
        else
            return Optional.empty();
    }

    private boolean isOutdated(Object key, Duration cacheDuration) {
        var lastUpdate = this.lastUpdate.get(key);
        var elapsed = System.nanoTime() - lastUpdate;

        return elapsed > cacheDuration.toNanos();
    }

}
