package xyz.haff.aspektoj.aspects;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public aspect Synchronization {
    private final Map<String, ReentrantLock> methodToLock = new ConcurrentHashMap<>();

    public pointcut annotated(): execution(@xyz.haff.aspektoj.annotations.Synchronized * *.*(..));

    Object around(): annotated() {
        var method = thisJoinPoint.getSignature().toShortString();

        var lock = methodToLock.computeIfAbsent(method, (x) -> new ReentrantLock());
        try {
            lock.lock();
            return proceed();
        } finally {
            lock.unlock();
        }
    }
}
