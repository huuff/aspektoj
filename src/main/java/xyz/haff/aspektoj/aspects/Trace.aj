package xyz.haff.aspektoj.aspects;

// TODO: Maybe per cflow?

public aspect Trace {

    before(): call(@xyz.haff.aspektoj.annotations.Traced * *.*(..)) {
        System.out.println("Calling " + thisJoinPoint.getSignature() + "...");
    }
}
