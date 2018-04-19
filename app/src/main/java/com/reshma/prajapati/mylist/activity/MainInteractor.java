package com.reshma.prajapati.mylist.activity;

import com.reshma.prajapati.mylist.fragment.ListFragment;

public interface MainInteractor {
     interface MainView{
        void setFragment(ListFragment fragment);
    }
    interface MainPresenter {
         void loadFragment();
    }
}
