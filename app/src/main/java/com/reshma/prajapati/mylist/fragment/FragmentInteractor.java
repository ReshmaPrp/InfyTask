package com.reshma.prajapati.mylist.fragment;

import android.app.Activity;

import com.reshma.prajapati.mylist.model.ListData;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface FragmentInteractor {
    interface View{
        void setListData(ListData setListData);
    }

    /*interface Presenter{
        void getRxData(ListData listData);
    }*/

}
