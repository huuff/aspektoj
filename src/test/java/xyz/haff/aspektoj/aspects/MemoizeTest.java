package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.Memoized;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MemoizeTest {

    @Memoized
    public int memoizedMethod() {
        return new Random().nextInt();
    }

    @Test
    void testMemoized() {
        var firstResult = memoizedMethod();
        var secondResult = memoizedMethod();

        assertEquals(firstResult, secondResult);
    }

}