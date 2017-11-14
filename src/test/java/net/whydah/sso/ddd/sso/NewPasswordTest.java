package net.whydah.sso.ddd.sso;

import net.whydah.sso.ddd.model.Password;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class NewPasswordTest {


    @Test
    public void testOKNewPasswords() {
    }

    @Test
    public void testIllegalNewPasswords() {
        String example1 = "OTE4MTk5NjQ6MTUwODk4NTYyOTQ1NjpQemR3ZWpzRVhGYzVVaWRWWnpKYkJ4SlFjRFlsRkZNcE9YMEJVV1V4VEZnbkNWbzJPQVIyVmpsc0VWTi9ESEVHUGdsc05nPT0=?newpassword=nemlighemmlig%00<iframe+src%3d%2f%2fwww.openiam.com+scrolling%3dno+frameborder%3d0+width%3d800+height%3d300>&show-password=on";
        String example2 = "OTE4MTk5NjQ6MTU8aWZyYW1lIHNyYz0vL3d3dy5vcGVuaWFtLmNvbSBzY3JvbGxpbmc9bm8gZnJhbWVib3JkZXI9MCB3aWR0aD04MDAgaGVpZ2h0PTMwMD4wNjI5NjYxOlB6ZHdlanNFWEZjNVVpZFZaekpiQnhKUWNEWWxGRk1wT1gwQlVXVXhURmduQ1ZvMk9BUjJWamxzRVZOL0RIRUdQZ2xzTmc9PQ==";
        assertFalse(Password.isValid(example1));
        assertFalse(Password.isValid(example2));

    }
}