package xyz.haff.aspektoj.aspects;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

// TODO: Ditch guava and use my own implementation
// TODO: Add choosing duration of memoization

import java.time.Duration;

public aspect Memoize pertarget(memoized()) {
    private final Cache<String, Object> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(Duration.ofHours(1))
            .build();

    public pointcut memoized() : call(@xyz.haff.aspektoj.annotations.Memoized * *.*());

    Object around(): memoized() {
        var key = thisJoinPoint.getSignature().toShortString();

        if (cache.asMap().containsKey(key))
            return cache.asMap().get(key);
        else {
            var result = proceed();
            cache.put(key, result);
            return result;
        }
    }
}
