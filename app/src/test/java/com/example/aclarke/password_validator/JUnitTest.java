package com.example.aclarke.password_validator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class JUnitTest {
    @Test
    public static void main (String[] args) throws Exception {
        new JUnitTest().validate(new String());

    }
    public void validate(String pass) throws Exception {
        assertFalse(pass.equals("password"));
        assertFalse(pass.length()<8);
    }
}