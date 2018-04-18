package com.reshma.prajapati.mylist.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.reshma.prajapati.mylist.CommonUtil.ApiClient;
import com.reshma.prajapati.mylist.CommonUtil.ApiInterface;
import com.reshma.prajapati.mylist.CommonUtil.ConnectionDetector;
import com.reshma.prajapati.mylist.CommonUtil.DatabaseHelper;
import com.reshma.prajapati.mylist.R;
import com.reshma.prajapati.mylist.adapters.MyListAdapter;
import com.reshma.prajapati.mylist.databinding.FragmentListBinding;
import com.reshma.prajapati.mylist.model.ListData;
import com.reshma.prajapati.mylist.model.Row;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private FragmentListBinding fragmentViewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        fragmentViewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,
                container,false);
        return  fragmentViewBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        fragmentViewBinding.swipeFresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        loadData();
                    }
                }
        );
    }

    public void loadData() {
        //Give a retrofit call
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ListData> call = apiService.getJsonData();
        dbHelper = new DatabaseHelper(getActivity());

        //checks if internet is connected or not
        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            call.enqueue(new Callback<ListData>() {
                @Override
                public void onResponse(@NonNull Call<ListData> call, @NonNull Response<ListData> response) {
                    fragmentViewBinding.swipeFresh.setRefreshing(false);
                    getActivity().setTitle(response.body().getTitle());
                    List<Row> row = response.body().getRows();
                    //store into local dbHelper
                    ListData jsonList = new ListData();
                    jsonList.setTitle(response.body().getTitle());
                    assert response.body() != null;
                    jsonList.setRows(response.body().getRows());

                    callRecyclerView((ArrayList<Row>) row);
                    //converting ListData to GSon to store into local dbHelper
                    Gson gson = new Gson();
                    String responseString = gson.toJson(jsonList);
                    storeListLocal(responseString);

                }
                @Override
                public void onFailure(@NonNull Call<ListData> call, @NonNull Throwable t) {
                }
            });
        }
        else{
            fragmentViewBinding.swipeFresh.setRefreshing(false);
            //load data from local data
            String responseStr= dbHelper.getMyList();
            if(responseStr!=null) {
                if(!responseStr.equalsIgnoreCase("")) {
                    try {
                        Gson gson = new Gson();
                        ListData response = gson.fromJson(responseStr, ListData.class);
                        Log.v("Main", ">> resp row :" + response.getRows());
                        List<Row> row=response.getRows();
                        getActivity().setTitle(response.getTitle());
                        callRecyclerView((ArrayList<Row>) row);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /*method to display recycler view*/
    private void callRecyclerView(ArrayList<Row> row) {
        MyListAdapter mAdapter = new MyListAdapter(row);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        fragmentViewBinding.rv.setLayoutManager(mLayoutManager);
        fragmentViewBinding.rv.setItemAnimator(new DefaultItemAnimator());
        fragmentViewBinding.rv.setAdapter(mAdapter);
    }

    /*store data to local dbHelper*/
    private void storeListLocal(String rows) {
        dbHelper.insertList(rows);
    }
}
