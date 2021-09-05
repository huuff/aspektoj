package xyz.haff.aspektoj;

// TODO: Lombok's `@UtilityClass`

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class TestUtils {

    public static OutputStream redirectStdout() {
        var testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        return testOut;
    }
}
