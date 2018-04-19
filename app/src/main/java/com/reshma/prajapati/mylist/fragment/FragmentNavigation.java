package com.reshma.prajapati.mylist.fragment;

public interface FragmentNavigation {
    interface View {
        void attachPresenter(Presenter presenter);

    }

    interface Presenter {
        void addFragment(ListFragment fragment);
    }
}