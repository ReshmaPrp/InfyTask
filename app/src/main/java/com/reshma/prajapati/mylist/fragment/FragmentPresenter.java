package com.reshma.prajapati.mylist.fragment;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.google.gson.Gson;
import com.reshma.prajapati.mylist.CommonUtil.ApiClient;
import com.reshma.prajapati.mylist.CommonUtil.ApiInterface;
import com.reshma.prajapati.mylist.CommonUtil.ConnectionDetector;
import com.reshma.prajapati.mylist.CommonUtil.DatabaseHelper;
import com.reshma.prajapati.mylist.model.ListData;
import com.reshma.prajapati.mylist.model.Row;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentPresenter {

    private final FragmentInteractor.View view;

    public FragmentPresenter(FragmentInteractor.View presenterView) {
        this.view = presenterView;
    }

    public void loadRxData() {
        //Give a retrofit call with rxjava
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Observable<ListData> call = apiService.getRxJsonData();

            call.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());

    }

    private Observer<ListData> getObserver() {
        return new Observer<ListData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onNext(ListData listData) {
                view.setListData(listData);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }



}
