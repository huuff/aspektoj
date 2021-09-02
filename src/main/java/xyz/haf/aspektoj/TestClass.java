package xyz.haf.aspektoj;

import xyz.haf.aspektoj.annotations.Traced;

public class TestClass {

    @Traced
    public void tracedMethod() {
        System.out.println("Hello world!");
    }
}
