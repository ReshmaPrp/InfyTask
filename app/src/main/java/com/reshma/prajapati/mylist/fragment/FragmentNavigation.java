package com.reshma.prajapati.mylist.fragment;

public interface FragmentNavigation {
    /*
        View works for MVP with help of which main component can reflect view on base of presenter command
     */
    interface View {
        void attachPresenter(Presenter presenter);

    }

    /*interface for adding new fragment*/
    interface Presenter {
        void addFragment(ListFragment fragment);
    }
}