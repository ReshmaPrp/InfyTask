package com.reshma.prajapati.mylist.fragment;

import com.reshma.prajapati.mylist.model.ListData;

public class FragmentPresenter implements FragmentInteractor.Presenter,
        FragmentInteractor.GetDataInteract.OnFinishedListener{

    private  FragmentInteractor.View mView;
    private FragmentInteractor.GetDataInteract mGetdataInteractor;

    public FragmentPresenter(FragmentInteractor.View presenterView, FragmentInteractor.GetDataInteract getdataInteractor) {
        this.mView = presenterView;
        this.mGetdataInteractor =getdataInteractor;
    }

    @Override
    public void requestDataFromServer() {
        mGetdataInteractor.getDataList(this );
    }

    @Override
    public void onFinished(ListData dataList) {
        if(mView != null){
            mView.setListData(dataList);
            mView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mView != null){
            mView.onResponseFailure(t);
            mView.hideProgress();
        }
    }
}
