package xyz.haf.aspektoj.aspects;

public aspect Trace {

    before(): call(@xyz.haf.aspektoj.annotations.Traced * *.*(..)) {
        System.out.println("Calling " + thisJoinPoint.getSignature() + "...");
    }
}
