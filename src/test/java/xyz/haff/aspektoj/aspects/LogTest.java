package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import xyz.haff.aspektoj.annotations.Logged;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LogTest {

    @SuppressWarnings("UnusedReturnValue")
    @Logged
    public String stringInStringOut(String input) {
        return "output";
    }

    @Logged
    public int twoArgsAndNonString(String string, int nonString) {
        return 47;
    }

    @Test
    void testStringInStringOut() {
        try (MockedStatic<LogManager> logManager = mockStatic(LogManager.class)) {
            var mockLogger = mock(Logger.class);
            logManager.when(() -> LogManager.getLogger(anyString())).thenReturn(mockLogger);

            stringInStringOut("input");

            var inOrder = inOrder(mockLogger);

            inOrder.verify(mockLogger).info(eq("Calling String xyz.haff.aspektoj.aspects.LogTest.stringInStringOut(String)"));
            inOrder.verify(mockLogger).info(eq("Input: input"));
            inOrder.verify(mockLogger).info(eq("Output: output"));
        }
    }

    @Test
    void testTwoArgsAndNonString() {
        try (MockedStatic<LogManager> logManager = mockStatic(LogManager.class)) {
            var mockLogger = mock(Logger.class);
            logManager.when(() -> LogManager.getLogger(anyString())).thenReturn(mockLogger);

            twoArgsAndNonString("string", 38);

            var inOrder = inOrder(mockLogger);

            inOrder.verify(mockLogger).info(eq("Calling int xyz.haff.aspektoj.aspects.LogTest.twoArgsAndNonString(String, int)"));
            inOrder.verify(mockLogger).info(eq("Input: string"));
            inOrder.verify(mockLogger).info(eq("Input: 38"));
            inOrder.verify(mockLogger).info(eq("Output: 47"));
        }
    }
}