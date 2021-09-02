package xyz.haf.aspektoj.aspects;

import xyz.haf.aspektoj.annotations.Memoized;

public aspect Memoize {

    public pointcut memoized(Object arg, Memoized memoized): execution(@xyz.haf.aspektoj.annotations.Memoized * * (*))
            && args(arg) && @annotation(memoized);

    Object around(Object arg, Memoized memoized): memoized(arg, memoized) {
        return null;
    }
}
