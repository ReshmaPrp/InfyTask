package com.reshma.prajapati.mylist.CommonUtil;

import com.reshma.prajapati.mylist.model.ListData;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //rxAndroid call with retrofit
    @GET("facts.json")
    Observable<ListData> getRxJsonData();
}