package xyz.haff.aspektoj.aspects;

import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.ContextVar;
import xyz.haff.aspektoj.annotations.LogContext;

import static org.junit.jupiter.api.Assertions.*;

class AppendLogContextTest {
    private static final String CONTEXT_KEY = "key";
    private static final String CONTEXT_VALUE = "value";

    @LogContext
    public void methodWithContext(@ContextVar(CONTEXT_KEY) String contextVar, String unusedParam) {
        assertTrue(ThreadContext.getContext().containsKey(CONTEXT_KEY));
        assertEquals(CONTEXT_VALUE, ThreadContext.getContext().get(CONTEXT_KEY));
    }

    @Test
    void logContextGetsAppendedWithContextVar() {
        methodWithContext(CONTEXT_VALUE, "");
        assertFalse(ThreadContext.containsKey(CONTEXT_KEY));
    }

}