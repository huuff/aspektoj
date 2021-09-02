package xyz.haf.aspektoj.aspects;

public aspect Trace {

    void around(): call(void xyz.haf.aspektoj.HelloWorld.sayHello()) {
        System.out.println("Log before...");
        proceed();
        System.out.println("After log...");
    }
}
