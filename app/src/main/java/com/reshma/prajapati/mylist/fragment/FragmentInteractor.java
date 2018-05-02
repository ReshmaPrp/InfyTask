package com.reshma.prajapati.mylist.fragment;

import com.reshma.prajapati.mylist.model.ListData;

public interface FragmentInteractor {

    interface View{
        //method will refresh view on updated data
        void setListData(ListData setListData);

        void hideProgress();
        void onResponseFailure(Throwable throwable);

    }
    /*
        Interface that will Listener that will fetch data from server and reflect the view
     */
    interface Presenter{
        void requestDataFromServer();
    }
    /**
     * Interface is  built for fetching data from your database, web services, or any other data source.
     **/
    interface GetDataInteract {

        interface OnFinishedListener {
            void onFinished(ListData dataList);
            void onFailure(Throwable t);
        }
        void getDataList(OnFinishedListener onFinishedListener);
    }
}
