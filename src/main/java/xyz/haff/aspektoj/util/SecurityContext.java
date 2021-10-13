package xyz.haff.aspektoj.util;

public class SecurityContext {
    public static final SecurityContext INSTANCE = new SecurityContext();
    public final ThreadLocal<String> loggedUser = new ThreadLocal<>();

    private SecurityContext() {}
}
