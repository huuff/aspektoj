package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import xyz.haff.aspektoj.annotations.ContextVar;
import xyz.haff.aspektoj.annotations.LogContext;

import static org.junit.jupiter.api.Assertions.*;

class AppendLogContextTest {
    private static final String CONTEXT_KEY = "key";
    private static final String CONTEXT_VALUE = "value";

    @LogContext
    public void methodWithContext(@ContextVar(CONTEXT_KEY) String contextVar, String unusedParam) {
        assertTrue(MDC.getCopyOfContextMap().containsKey(CONTEXT_KEY));
        assertEquals(CONTEXT_VALUE, MDC.getCopyOfContextMap().get(CONTEXT_KEY));
    }

    @Test
    void logContextGetsAppendedWithContextVar() {
        methodWithContext(CONTEXT_VALUE, "");
        assertNull(MDC.getCopyOfContextMap());
    }

}