package com.reshma.prajapati.mylist.fragment;

import com.reshma.prajapati.mylist.model.ListData;

public class FragmentPresenter implements FragmentInteractor.Presenter,
        FragmentInteractor.GetDataInteract.OnFinishedListener{

    private  FragmentInteractor.View frView;
    private FragmentInteractor.GetDataInteract getdataInteractor;

    public FragmentPresenter(FragmentInteractor.View presenterView, FragmentInteractor.GetDataInteract getdataInteractor) {
        this.frView = presenterView;
        this.getdataInteractor =getdataInteractor;
    }

    @Override
    public void requestDataFromServer() {
        getdataInteractor.getDataList(this );
    }

    @Override
    public void onFinished(ListData dataList) {
        if(frView != null){
            frView.setListData(dataList);
            frView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(frView != null){
            frView.onResponseFailure(t);
            frView.hideProgress();
        }
    }
}
