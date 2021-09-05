package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Benchmarked;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkTest {

    // TODO: Lombok's `@SneakyThrows`
    @Benchmarked
    public void benchmarkedMethod() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // HACK: Fragile, maybe use some epsilon?
    @Test
    void testBenchmarked() {
        var testOut = TestUtils.redirectStdout();

        benchmarkedMethod();

        assertEquals(
                "BenchmarkTest.benchmarkedMethod() took 500ms\n",
                testOut.toString()
        );
    }

}