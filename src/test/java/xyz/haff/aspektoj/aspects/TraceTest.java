package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Traced;

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
        var testOut = TestUtils.redirectStdout();

        tracedMethod();

        assertEquals(
                "Calling void xyz.haff.aspektoj.aspects.TraceTest.tracedMethod()...\nTest\n",
                testOut.toString());
    }
}