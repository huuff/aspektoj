package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.Between;

import static org.junit.jupiter.api.Assertions.*;

class BetweenCheckerTest {

    public void between0And10(@Between(min=0, max=10) int argument) {

    }

    @Test
    void rightUsage() {
        between0And10(5);
    }

    @Test
    void wrongUsage() {
        assertThrows(IllegalArgumentException.class, () -> between0And10(1000));
    }
}