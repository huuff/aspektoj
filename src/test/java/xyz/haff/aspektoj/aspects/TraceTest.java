package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Traced;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TraceTest {
    private static final String LOGGER_NAME = "logger";

    public void innerTracedMethod() {
        System.out.println("TEST");
    }

    @Traced(logger = LOGGER_NAME)
    public void tracedMethod() {
        innerTracedMethod();
        innerTracedMethod();
    }

    // TODO: Test NDC?

    @Test
    void testTraced() {
        try (MockedStatic<LogManager> logManager = mockStatic(LogManager.class)) {
            var mockLogger = mock(Logger.class);
            logManager.when(() -> LogManager.getLogger(anyString())).thenReturn(mockLogger);
            tracedMethod();

            var inOrder = inOrder(mockLogger);

            inOrder.verify(mockLogger).trace(eq("Calling void xyz.haff.aspektoj.aspects.TraceTest.tracedMethod()..."));
            inOrder.verify(mockLogger).trace(eq("Calling void xyz.haff.aspektoj.aspects.TraceTest.innerTracedMethod()..."));
            inOrder.verify(mockLogger).trace(eq("Calling void java.io.PrintStream.println(String)..."));
            inOrder.verify(mockLogger).trace(eq("Calling void xyz.haff.aspektoj.aspects.TraceTest.innerTracedMethod()..."));
            inOrder.verify(mockLogger).trace(eq("Calling void java.io.PrintStream.println(String)..."));
        }
    }
}