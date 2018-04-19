package com.reshma.prajapati.mylist.fragment;

import com.reshma.prajapati.mylist.CommonUtil.ApiClient;
import com.reshma.prajapati.mylist.CommonUtil.ApiInterface;
import com.reshma.prajapati.mylist.model.ListData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetNoticeInteractorImpl implements FragmentInteractor.GetDataInteract {
    @Override
    public void getDataList(final OnFinishedListener onFinishedListener) {
        //Give a retrofit call with rxjava
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Observable<ListData> call = apiService.getRxJsonData();

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Observer<ListData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListData listData) {
                        onFinishedListener.onFinished(listData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFinishedListener.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
