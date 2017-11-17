package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewPasswordTokenTest {
    private static final Logger log = LoggerFactory.getLogger(NewPasswordTokenTest.class);


    @Test
    public void testIllegalNewNewPasswordToken() {
        String example1 = "OTE4MTk5NjQ6MTUwODk4NTYyOTQ1NjpQemR3ZWpzRVhGYzVVaWRWWnpKYkJ4SlFjRFlsRkZNcE9YMEJVV1V4VEZnbkNWbzJPQVIyVmpsc0VWTi9ESEVHUGdsc05nPT0=?newpassword=nemlighemmlig%00<iframe+src%3d%2f%2fwww.openiam.com+scrolling%3dno+frameborder%3d0+width%3d800+height%3d300>&show-password=on";
        String example2 = "OTE4MTk5NjQ6MTU8aWZyYW1lIHNyYz0vL3d3dy5vcGVuaWFtLmNvbSBzY3JvbGxpbmc9bm8gZnJhbWVib3JkZXI9MCB3aWR0aD04MDAgaGVpZ2h0PTMwMD4wNjI5NjYxOlB6ZHdlanNFWEZjNVVpZFZaekpiQnhKUWNEWWxGRk1wT1gwQlVXVXhURmduQ1ZvMk9BUjJWamxzRVZOL0RIRUdQZ2xzTmc9PQ==";
        assertFalse(NewPasswordToken.isValid(example1));  // typical attack data
        assertFalse(NewPasswordToken.isValid(example2));  // typical attack data

        assertFalse(NewPasswordToken.isValid("<javascript:"));
        assertFalse(NewPasswordToken.isValid("<html>"));

        assertFalse(NewPasswordToken.isValid("abc"));  // to short
        assertFalse(NewPasswordToken.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(NewPasswordToken.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(NewPasswordToken.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(NewPasswordToken.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(NewPasswordToken.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(NewPasswordToken.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(NewPasswordToken.isValid("superhemmeligpassord"));
        assertFalse(NewPasswordToken.isValid("sometimes it is fine to forget"));


    }

    @Test
    public void testOKNewNewPasswordTokens() {
        assertTrue(NewPasswordToken.isValid("RTYRYRYT7898798JKHKJH"));
        assertTrue(NewPasswordToken.isValid(UUID.randomUUID().toString()));
    }

}