package com.reshma.prajapati.mylist.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.reshma.prajapati.mylist.CommonUtil.ConnectionDetector;
import com.reshma.prajapati.mylist.CommonUtil.DatabaseHelper;
import com.reshma.prajapati.mylist.R;
import com.reshma.prajapati.mylist.adapters.MyListAdapter;
import com.reshma.prajapati.mylist.databinding.FragmentListBinding;
import com.reshma.prajapati.mylist.model.ListData;
import com.reshma.prajapati.mylist.model.Row;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements FragmentNavigation.View,FragmentInteractor.View {
    private DatabaseHelper dbHelper;
    private FragmentListBinding fragmentViewBinding;
    private FragmentPresenter frPresenter;

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
        frPresenter =new FragmentPresenter(this,new GetNoticeInteractorImpl());
        dbHelper = new DatabaseHelper(getActivity());
        loadRxData();
    }

    public void loadRxData() {
        //load data from local data
        String responseStr= dbHelper.getMyList();
        if(responseStr!=null) {
            if(!responseStr.equalsIgnoreCase("")) {
                try {
                    Gson gson = new Gson();
                    ListData response = gson.fromJson(responseStr, ListData.class);
                    setListData(response);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                frPresenter.requestDataFromServer();
            }
        }else{
            frPresenter.requestDataFromServer();
        }

        fragmentViewBinding.swipeFresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        frPresenter.requestDataFromServer();
                    }
                }
        );
    }



    /*method to display recycler view*/
    private void callRecyclerView(ArrayList<Row> row) {
        MyListAdapter mAdapter = new MyListAdapter(row);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        fragmentViewBinding.rv.setLayoutManager(mLayoutManager);
        fragmentViewBinding.rv.setItemAnimator(new DefaultItemAnimator());
        fragmentViewBinding.rv.setAdapter(mAdapter);
        fragmentViewBinding.rv.setHasFixedSize(true);
    }

    /*store data to local dbHelper*/
    private void storeListLocal(String rows) {
        dbHelper.insertList(rows);
    }

    @Override
    public void attachPresenter(FragmentNavigation.Presenter presenter) {
    }

    @Override
    public void setListData(ListData listData) {
        fragmentViewBinding.progressBar.setVisibility(View.GONE);
        fragmentViewBinding.swipeFresh.setRefreshing(false);
        List<Row> row=listData.getRows();
        getActivity().setTitle(listData.getTitle());
        callRecyclerView((ArrayList<Row>) row);
        //checks if internet is connected or not
        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {

            //converting ListData to GSon to store into local dbHelper
            Gson gson = new Gson();
            ListData jsonList = new ListData();
            jsonList.setTitle(listData.getTitle());
            jsonList.setRows(listData.getRows());
            String responseString = gson.toJson(jsonList);
            storeListLocal(responseString);
        }
    }


    @Override
    public void hideProgress() {
        fragmentViewBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
