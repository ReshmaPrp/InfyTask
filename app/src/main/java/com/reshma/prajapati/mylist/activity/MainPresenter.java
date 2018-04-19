package com.reshma.prajapati.mylist.activity;

import android.app.Fragment;

import com.reshma.prajapati.mylist.fragment.FragmentNavigation;
import com.reshma.prajapati.mylist.fragment.ListFragment;

public class MainPresenter implements MainInteractor.MainPresenter ,FragmentNavigation.Presenter{
    private MainInteractor.MainView view;

    public  MainPresenter(MainInteractor.MainView view){
        this.view =view;
    }
    @Override
    public void loadFragment() {
        view.setFragment(new ListFragment());
    }
    /**
     * this method from navigation presenter tells the view to show
     * the param fragment
     * @param fragment
     */
    @Override
    public void addFragment(ListFragment fragment) {
        view.setFragment(fragment);
    }
}
