package com.reshma.prajapati.mylist;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.reshma.prajapati.mylist.R;
import com.reshma.prajapati.mylist.databinding.ActivityMainBinding;
import com.reshma.prajapati.mylist.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LIST_FRAGMENT = "tag_list_fragment";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //loads data based on net available

        FragmentManager fm = getFragmentManager();
        ListFragment mListFragment = (ListFragment) fm.findFragmentByTag(TAG_LIST_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mListFragment == null) {
            mListFragment = new ListFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment, mListFragment,TAG_LIST_FRAGMENT);
            ft.commit();
        }
    }
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of item position
//        outState.putInt(TAG_LIST_FRAGMENT, "");
    }
}
