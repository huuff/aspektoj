package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import xyz.haff.aspektoj.annotations.Benchmarked;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BenchmarkTest {

    @Benchmarked
    public void benchmarkedMethod() { }

    @Test
    void testBenchmarked() {
        try (MockedStatic<LogManager> logManager = mockStatic(LogManager.class)) {
            var mockLogger = mock(Logger.class);
            logManager.when(() -> LogManager.getLogger(anyString())).thenReturn(mockLogger);

            benchmarkedMethod();

            verify(mockLogger).info(eq("BenchmarkTest.benchmarkedMethod() took 000ms"));
        }
    }

}