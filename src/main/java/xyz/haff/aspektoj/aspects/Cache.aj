package xyz.haff.aspektoj.aspects;

import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.reflect.MethodSignature;
import xyz.haff.aspektoj.annotations.Cached;
import xyz.haff.aspektoj.annotations.CacheKey;
import xyz.haff.aspektoj.util.AnnotatedArgument;

import java.lang.annotation.Annotation;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// TODO: Support last accessed instead of only last inserted
// Do not press C-M O or you'll expend hours debugging why it doesn't work because idea removed the CacheKey import

public aspect Cache {
    private final Map<String, com.google.common.cache.Cache<Object, Object>> CACHES = new HashMap<>();

    public pointcut cached(Cached cached): call(@Cached * *.*(.., @CacheKey (*), ..)) && @annotation(cached);

    Object around(Cached cached): cached(cached) {
        var method = thisJoinPoint.getSignature().toShortString();
        var key = new AnnotatedArgument<>(thisJoinPoint, CacheKey.class).getArgument();

        try {
            return CACHES.computeIfAbsent(method, (x) -> newCache(cached.value())).get(key, () -> proceed(cached));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private com.google.common.cache.Cache<Object, Object> newCache(String duration) {
        return CacheBuilder.newBuilder().expireAfterWrite(Duration.parse(duration)).softValues().build();
    }
}
