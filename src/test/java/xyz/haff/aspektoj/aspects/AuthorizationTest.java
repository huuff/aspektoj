package xyz.haff.aspektoj.aspects;

import org.junit.jupiter.api.Test;
import xyz.haff.aspektoj.annotations.Secured;
import xyz.haff.aspektoj.exceptions.UnauthorizedException;
import xyz.haff.aspektoj.util.SecurityContext;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationTest {

    @Secured(username = "admin")
    public void onlyForAdmin() {

    }

    @Test
    void unauthorizedThrows() {
        assertThrows(UnauthorizedException.class, this::onlyForAdmin);
    }

    @Test
    void wrongUserThrows() {
        SecurityContext.INSTANCE.loggedUser.set("user");;

        assertThrows(UnauthorizedException.class, this::onlyForAdmin);
    }

    @Test
    void correctUserDoesNotThrow() {
        SecurityContext.INSTANCE.loggedUser.set("admin");

        assertDoesNotThrow(this::onlyForAdmin);
    }
}