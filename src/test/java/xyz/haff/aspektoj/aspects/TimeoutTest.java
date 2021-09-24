package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.Timed;
import xyz.haff.aspektoj.exceptions.RuntimeTimeoutException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeoutTest {

    @Timed("PT0.00001S")
    public void timesOutMethod() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Timed("PT1s")
    public void doesNotTimeOutMethod() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void timesOut() {
        assertThrows(RuntimeTimeoutException.class, this::timesOutMethod);
    }

    @Test
    void doesNotTimeOut() {
        assertDoesNotThrow(this::doesNotTimeOutMethod);
    }
}