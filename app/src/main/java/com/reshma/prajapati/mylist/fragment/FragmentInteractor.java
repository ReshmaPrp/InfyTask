package com.reshma.prajapati.mylist.fragment;

import com.reshma.prajapati.mylist.model.ListData;

public interface FragmentInteractor {
    interface View{
        void setListData(ListData setListData);

        void hideProgress();
        void onResponseFailure(Throwable throwable);

    }

    interface Presenter{

        void requestDataFromServer();
    }
    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetDataInteract {

        interface OnFinishedListener {
            void onFinished(ListData dataList);
            void onFailure(Throwable t);
        }

        void getDataList(OnFinishedListener onFinishedListener);
    }
}
