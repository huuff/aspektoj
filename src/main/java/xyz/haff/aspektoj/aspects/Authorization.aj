package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Secured;
import xyz.haff.aspektoj.exceptions.UnauthorizedException;
import xyz.haff.aspektoj.util.SecurityContext;

public aspect Authorization {
    public pointcut annotated(Secured secured): execution(@xyz.haff.aspektoj.annotations.Secured * *.*(..)) && @annotation(secured);

    before(Secured secured): annotated(secured) {
        var loggedUser = SecurityContext.INSTANCE.loggedUser.get();

        if (loggedUser == null) {
            throw new UnauthorizedException("Not authenticated");
        }

        if (!loggedUser.equals(secured.username())) {
            throw new UnauthorizedException("User " + loggedUser + " is not authorized to use method " + thisJoinPoint.getSignature().toShortString());
        }
    }
}
