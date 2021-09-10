package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.CacheKey;
import xyz.haff.aspektoj.annotations.Cached;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Cached("PT10s")
    public int cachedMethod(@CacheKey String key) {
        return new Random().nextInt();
    }

    @Test
    void correctlyCached() {
        var firstResult = cachedMethod("Key");
        var secondResult = cachedMethod("Key");

        assertEquals(firstResult, secondResult);
    }

    @Test
    void differentKeysCached() {
        var firstKey = "key1";
        var secondKey = "key2";

        var firstKeyFirstResult = cachedMethod(firstKey);
        var firstKeySecondResult = cachedMethod(firstKey);

        var secondKeyFirstResult = cachedMethod(secondKey);
        var secondKeySecondResult = cachedMethod(secondKey);

        assertEquals(firstKeyFirstResult, firstKeySecondResult);
        assertEquals(secondKeyFirstResult, secondKeySecondResult);

        assertNotEquals(firstKeyFirstResult, secondKeyFirstResult);
        assertNotEquals(firstKeySecondResult, secondKeySecondResult);
    }
}