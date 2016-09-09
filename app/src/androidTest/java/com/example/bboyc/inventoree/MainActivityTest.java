package com.example.bboyc.inventoree;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
    new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testFab() throws Exception{
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fab_books)).perform(click());
        onView(withId(R.id.dialog_editTextName)).perform(clearText(), typeText("Harry Potter"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextDetail)).perform(clearText(), typeText("J.K. Rowling"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextYear)).perform(clearText(), typeText("2001"), closeSoftKeyboard());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.fab_wardrobe)).perform(click());
        onView(withId(R.id.dialog_editTextName)).perform(clearText(), typeText("Jacket"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextDetail)).perform(clearText(), typeText("Blue & Black"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextYear)).perform(clearText(), typeText("2015"), closeSoftKeyboard());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.fab_media)).perform(click());
        onView(withId(R.id.dialog_editTextName)).perform(clearText(), typeText("Final Fantasy X"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextDetail)).perform(clearText(), typeText("PS4 Video Game"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextYear)).perform(clearText(), typeText("2015"), closeSoftKeyboard());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.fab_electronics)).perform(click());
        onView(withId(R.id.dialog_editTextName)).perform(clearText(), typeText("Monitor"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextDetail)).perform(clearText(), typeText("Sony 3D 24"), closeSoftKeyboard());
        onView(withId(R.id.dialog_editTextYear)).perform(clearText(), typeText("2012"), closeSoftKeyboard());
        onView(withText("OK")).perform(click());


    }

}
