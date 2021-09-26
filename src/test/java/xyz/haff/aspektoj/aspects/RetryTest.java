package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Retryable;

import static org.junit.jupiter.api.Assertions.*;

class RetryTest {
    private static final int MAX_RETRIES = 3;
    private static int currentRetries = 0;

    private static class RetryException extends RuntimeException { }

    @Retryable(exceptions = {RetryException.class}, times = MAX_RETRIES)
    public void retryableMethod() {
        if (currentRetries < MAX_RETRIES) {
            System.out.println(currentRetries++);
            throw new RetryException();
        } else {
            System.out.println(currentRetries);
        }
    }

    @Test
    void retriesThreeTimes() {
        var testOut = TestUtils.redirectStdout();

        retryableMethod();

        assertEquals("0\n1\n2\n", testOut.toString());
    }
}