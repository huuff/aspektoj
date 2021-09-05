package xyz.haff.aspektoj.aspects;

public aspect Trace {

    before(): call(@xyz.haff.aspektoj.annotations.Traced * *.*(..)) {
        System.out.println("Calling " + thisJoinPoint.getSignature() + "...");
    }
}
