package xyz.haff.aspektoj.aspects;

public aspect Trace {

    pointcut annotated(): call(@xyz.haff.aspektoj.annotations.Traced * *.*(..));

    pointcut traced(): call(* *.*(..))
                        && (cflow(annotated()))
                        && !cflowbelow(adviceexecution() && within(Trace));

    before(): traced() {
        System.out.println("Calling " + thisJoinPoint.getSignature() + "...");
    }
}
