package xyz.haf.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haf.aspektoj.annotations.Traced;

import static org.junit.jupiter.api.Assertions.*;

class TraceTest {

    @Traced
    public void tracedMethod() {
        System.out.println("Test");
    }

    @Test
    void testTraced() {
        tracedMethod();
    }
}