package com.reshma.prajapati.mylist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.reshma.prajapati.mylist.activity.MainActivity;
import com.reshma.prajapati.mylist.fragment.ListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ActivityTest  {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Activity activity;

    @Before
    public void createActivity(){
         activity = activityTestRule.getActivity();
    }

    @Test
    public void testUI(){
        assertNotNull(activity.findViewById(R.id.fragment));
    }
    @UiThreadTest
    public void testActionBar(){
        assertNotNull(Objects.requireNonNull(activity.getActionBar()).getTitle());

        //checks wheather iserted fragment is listF
        FragmentManager sfm = activityTestRule.getActivity().getFragmentManager();
        Fragment currentFragment = sfm.findFragmentById(R.id.fragment);
        assertTrue(currentFragment instanceof ListFragment);

    }

}
