package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Memoized;
import xyz.haff.aspektoj.util.CacheMap;

public aspect Memoize pertarget(memoized(*)) {
    private final CacheMap cacheMap = new CacheMap();

    public pointcut memoized(Memoized memoized) : call(@Memoized * *.*()) && @annotation(memoized) ;

    Object around(Memoized memoized): memoized(memoized) {
        var key = thisJoinPoint.getSignature().toShortString();

        return cacheMap.get(key, memoized.value()).orElseGet(() -> cacheMap.put(key, proceed(memoized)));
    }
}
