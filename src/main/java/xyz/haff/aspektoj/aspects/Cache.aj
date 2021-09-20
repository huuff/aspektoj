package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Cached;
import xyz.haff.aspektoj.annotations.CacheKey;
import xyz.haff.aspektoj.util.CacheMap;

// TODO: Support last accessed instead of only last inserted

public aspect Cache {
    private final CacheMap cacheMap = new CacheMap();

    public pointcut cached(Cached cached): call(@Cached * *.*(@CacheKey (*), ..)) && @annotation(cached);

    Object around(Cached cached): cached(cached) {
        var key = thisJoinPoint.getArgs()[0];

        return cacheMap.get(key, cached.value()).orElseGet(() -> cacheMap.put(key, proceed(cached)));
    }


}
