package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.TestUtils;
import xyz.haff.aspektoj.annotations.Synchronized;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizationTest {

    @Synchronized
    void synchronizedMethod() {
        System.out.println("Started");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ended");
    }

    @Test
    void callsAreOrdered() {
        var stdout = TestUtils.redirectStdout();

        ForkJoinTask<?> task1 = ForkJoinPool.commonPool().submit(this::synchronizedMethod);
        ForkJoinTask<?> task2 = ForkJoinPool.commonPool().submit(this::synchronizedMethod);

        task1.join();
        task2.join();

        // If @Synchronized were not working
        assertEquals("Started\nEnded\nStarted\nEnded\n", stdout.toString());
    }
}