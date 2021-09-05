package xyz.haff.aspektoj.aspects;

import xyz.haff.aspektoj.annotations.Memoized;

public aspect Memoize {

    public pointcut memoized(Object arg, Memoized memoized): execution(@xyz.haff.aspektoj.annotations.Memoized * * (*))
            && args(arg) && @annotation(memoized);

    Object around(Object arg, Memoized memoized): memoized(arg, memoized) {
        return null;
    }
}
