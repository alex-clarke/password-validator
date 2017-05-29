package com.example.aclarke.password_validator;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JUnitTest{
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test
    public void main () throws Exception {
        onView(withId(R.id.edittext)).perform(typeText("Passcode1@")).perform(closeSoftKeyboard());
        Thread.sleep(250);
        onView(withId(R.id.button)).perform(click());
        new JUnitTest().validate();

    }

    public void validate() throws Exception {
        String pass = MainActivity.getText();
        try {
            assertFalse(pass.equals("password"));
        }catch (AssertionError e){
            onView(withId(R.id.resultText)).perform(typeText("Password is \'password\'"));
            return;
        }
        try{
            assertFalse(pass.length()<8);
        }catch (AssertionError e){
            onView(withId(R.id.resultText)).perform(typeText("Password is too short"));
            return;
        }
        try{
            assertTrue(pass.contains("0")||pass.contains("1")||pass.contains("2")||pass.contains("3")||pass.contains("4")
                ||pass.contains("5")||pass.contains("6")||pass.contains("7")||pass.contains("8")||pass.contains("9"));
        }catch (AssertionError e){
            onView(withId(R.id.resultText)).perform(typeText("Password does not contain a number"));
            return;
        }
        try{
            assertTrue(uppercase(pass));
        }catch (AssertionError e){
            onView(withId(R.id.resultText)).perform(typeText("Password does not contain an uppercase letter"));
            return;
        }
        try{
            assertTrue(pass.contains("!")||pass.contains("@")||pass.contains("#")||pass.contains("$")
                ||pass.contains("%")||pass.contains("^")||pass.contains("&")||pass.contains("*"));
        }catch (AssertionError e){
            onView(withId(R.id.resultText)).perform(typeText("Password does not contain a symbol"));
            return;
        }
        onView(withId(R.id.resultText)).perform(typeText("Password entered"));
        onView(withId(R.id.resultText)).perform(closeSoftKeyboard());
        Thread.sleep(250);
    }
    public boolean uppercase(String s){
        for (int i=0;i<s.length();i++){
            if(Character.isUpperCase(s.charAt(i))){
                return true;
            }
        }
        return false;
    }
}