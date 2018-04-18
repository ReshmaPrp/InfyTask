package com.reshma.prajapati.mylist.CommonUtil;

import com.reshma.prajapati.mylist.model.ListData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("facts.json")
    Call<ListData> getJsonData();
}