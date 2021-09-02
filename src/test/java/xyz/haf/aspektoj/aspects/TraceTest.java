package xyz.haf.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haf.aspektoj.annotations.Traced;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TraceTest {

    @Traced
    public void tracedMethod() {
        System.out.println("Test");
    }

    @Test
    void testTraced() throws InterruptedException {
        var testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        tracedMethod();

        assertEquals("Calling void xyz.haf.aspektoj.aspects.TraceTest.tracedMethod()...\n" +
                "Test\n", testOut.toString());
    }
}