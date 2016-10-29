package com.codepath.mypizza;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Shyam Rokde on 8/9/16.
 */
public class MainActivityInstrumentationTest {
  @Rule
  public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  // Looks for an EditText with id = "R.id.etInput"
  // Types the text "Hello" into the EditText
  // Verifies the EditText has text "Hello"
  @Test
  public void validateEditText() {

    onView(withId(R.id.etInput)).perform(typeText("Hello")).check(matches(withText("Hello")));
  }
}
