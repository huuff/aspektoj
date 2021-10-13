package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import xyz.haff.aspektoj.annotations.Traced;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void testTraced() {
        try (MockedStatic<LogManager> logManager = mockStatic(LogManager.class)) {
            try (MockedStatic<ThreadContext> threadContext = mockStatic(ThreadContext.class)) {
                var mockLogger = mock(Logger.class);
                logManager.when(() -> LogManager.getLogger(anyString())).thenReturn(mockLogger);
                tracedMethod();

                var inOrder = inOrder(mockLogger);

                inOrder.verify(mockLogger).trace(eq("Calling void xyz.haff.aspektoj.aspects.TraceTest.tracedMethod()..."));
                inOrder.verify(mockLogger).trace(eq("Calling void xyz.haff.aspektoj.aspects.TraceTest.innerTracedMethod()..."));
                inOrder.verify(mockLogger).trace(eq("Calling void java.io.PrintStream.println(String)..."));
                inOrder.verify(mockLogger).trace(eq("Calling void xyz.haff.aspektoj.aspects.TraceTest.innerTracedMethod()..."));
                inOrder.verify(mockLogger).trace(eq("Calling void java.io.PrintStream.println(String)..."));

                threadContext.verify(() -> ThreadContext.push(anyString()), times(5));
                threadContext.verify(ThreadContext::pop, times(5));
            }
        }
    }
}