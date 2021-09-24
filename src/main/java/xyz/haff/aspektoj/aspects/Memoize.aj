package xyz.haff.aspektoj.aspects;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import xyz.haff.aspektoj.annotations.Memoized;
import xyz.haff.aspektoj.util.DurationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public aspect Memoize {
    private final Map<String, Supplier<Object>> MEMOIZATIONS = new HashMap<>();

    public pointcut memoized(Memoized memoized) : call(@Memoized * *.*()) && @annotation(memoized);

    Object around(Memoized memoized): memoized(memoized) {
        var key = thisJoinPoint.getSignature().toShortString();

        if (!MEMOIZATIONS.containsKey(key)) {
            MEMOIZATIONS.put(key, Suppliers.memoizeWithExpiration(() -> proceed(memoized), DurationUtil.getNanos(memoized.value()), TimeUnit.NANOSECONDS));
        }

        return MEMOIZATIONS.get(key).get();
    }
}
