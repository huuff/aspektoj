package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Traced;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TraceTest {

    public void innerTracedMethod() { }

    @Traced
    public void tracedMethod() {
        innerTracedMethod();
        System.out.println("Test");
    }

    @Test
    void testTraced() {
        var testOut = TestUtils.redirectStdout();

        tracedMethod();

        assertEquals(
                "Calling void xyz.haff.aspektoj.aspects.TraceTest.tracedMethod()...\n" +
                        "Calling void xyz.haff.aspektoj.aspects.TraceTest.innerTracedMethod()...\n" +
                        "Calling void java.io.PrintStream.println(String)...\n" +
                        "Test\n",
                testOut.toString());
    }
}