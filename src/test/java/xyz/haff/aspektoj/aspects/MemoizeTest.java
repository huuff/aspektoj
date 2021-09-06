package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.Memoized;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MemoizeTest {

    @Memoized("PT1H30M")
    public int memoizedMethod() {
        return new Random().nextInt();
    }

    @Memoized("PT0.00001S")
    public int expiredMemoization() {
        return new Random().nextInt();
    }

    @Test
    void correctlyMemoized() {
        var firstResult = memoizedMethod();
        var secondResult = memoizedMethod();

        assertEquals(firstResult, secondResult);
    }

    @Test
    void memoizationExpires() {
        var firstResult = expiredMemoization();
        var secondResult = expiredMemoization();

        assertNotEquals(firstResult, secondResult);
    }

}