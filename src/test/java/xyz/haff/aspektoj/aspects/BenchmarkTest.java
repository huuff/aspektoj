package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Benchmarked;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkTest {

    @Benchmarked
    public void benchmarkedMethod() { }

    @Test
    void testBenchmarked() {
        var testOut = TestUtils.redirectStdout();

        benchmarkedMethod();

        assertEquals(
                "BenchmarkTest.benchmarkedMethod() took 000ms\n",
                testOut.toString()
        );
    }

}