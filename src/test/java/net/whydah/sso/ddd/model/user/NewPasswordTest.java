package net.whydah.sso.ddd.model.user;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewPasswordTest {

    private static final Logger log = LoggerFactory.getLogger(NewPasswordTest.class);



    @Test
    public void testIllegalPasswords() {
        String example1 = "OTE4MTk5NjQ6MTUwODk4NTYyOTQ1NjpQemR3ZWpzRVhGYzVVaWRWWnpKYkJ4SlFjRFlsRkZNcE9YMEJVV1V4VEZnbkNWbzJPQVIyVmpsc0VWTi9ESEVHUGdsc05nPT0=?newpassword=nemlighemmlig%00<iframe+src%3d%2f%2fwww.openiam.com+scrolling%3dno+frameborder%3d0+width%3d800+height%3d300>&show-password=on";
        String example2 = "OTE4MTk5NjQ6MTU8aWZyYW1lIHNyYz0vL3d3dy5vcGVuaWFtLmNvbSBzY3JvbGxpbmc9bm8gZnJhbWVib3JkZXI9MCB3aWR0aD04MDAgaGVpZ2h0PTMwMD4wNjI5NjYxOlB6ZHdlanNFWEZjNVVpZFZaekpiQnhKUWNEWWxGRk1wT1gwQlVXVXhURmduQ1ZvMk9BUjJWamxzRVZOL0RIRUdQZ2xzTmc9PQ==";
        assertFalse(Password.isValid(example1));  // typical attack data
        assertFalse(Password.isValid(example2));  // typical attack data

        assertFalse(Password.isValid("<javascript:"));
        assertFalse(Password.isValid("<html>"));

        assertFalse(Password.isValid("abc"));  // to short
        assertFalse(Password.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(Password.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(Password.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Password.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Password.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Password.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));


        assertFalse(Password.isValid("http://victim/cgi/%252E%252E%252F%252E%252E%252Fwinnt/system32/cmd.exe?/c+dir+c:\\"));

    }

    @Test
    public void testOKNewPasswords() {
        assertTrue(Password.isValid("superhemmeligpassord"));
        assertTrue(Password.isValid("sometimes it is fine to forget"));
        assertTrue(Password.isValid("RTYRYRYT7898798JKHKJH"));
        assertTrue(Password.isValid("P@ss#word12~!$ %+=.,?-_"));
        assertTrue(Password.isValid("&AgCM%s&Cec4f2gSPdkPqt9Q"));
        assertTrue(Password.isValid("Password12!@#$%^&*_-+=~!$ %+=.,?-_"));
        assertTrue(Password.isValid(UUID.randomUUID().toString()));
        assertTrue(Password.getInValidCharacters("P@ss#word12~!$ <%+=.,?-_>").get(0).equals("<"));
        assertTrue(Password.getInValidCharacters("P@ss#word12~!$ <%+=.,?-_>").get(1).equals(">"));
    }
    
   

}