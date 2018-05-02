package com.reshma.prajapati.mylist.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.reshma.prajapati.mylist.R;
import com.reshma.prajapati.mylist.databinding.ActivityMainBinding;
import com.reshma.prajapati.mylist.fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements MainInteractor.MainView {

    private static final String TAG_LIST_FRAGMENT = "tag_list_fragment";
    private static final String TAG_RETAIN_FRAGMENT = "tag_retain";
    private boolean isTrue =false;
    ActivityMainBinding mainBinding;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //loads fragment
        mainPresenter =new MainPresenter(this);
        mainPresenter.loadFragment();
    }
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of item position
        outState.putBoolean(TAG_RETAIN_FRAGMENT, true);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.getBoolean(TAG_RETAIN_FRAGMENT)){
            isTrue = true;
        }
    }
    /**
     * sets a new fragment on fragment_container frame
     * layout and attaches to it the presenter
     * @param fragment
     */
    @Override
    public void setFragment(ListFragment fragment) {
        fragment.attachPresenter(mainPresenter);
        FragmentManager fm = getFragmentManager();
        ListFragment mListFragment = (ListFragment) fm.findFragmentByTag(TAG_LIST_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mListFragment == null) {
            mListFragment = new ListFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment, mListFragment, TAG_LIST_FRAGMENT);
            ft.commit();
        }
    }
}
