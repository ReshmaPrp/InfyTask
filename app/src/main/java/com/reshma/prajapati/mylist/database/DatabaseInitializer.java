package com.reshma.prajapati.mylist.database;


import android.os.AsyncTask;
import android.support.annotation.NonNull;

/*
    Class is used to perform operations on database the data base.
 */
public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    //class that executes insertion task.
    public static void InsertAsync(@NonNull final AppDatabase db, DataListEntity data) {
        InsertAsync task = new InsertAsync(db,data);
        task.execute();
    }

    /*
        Method that insert data into db using DAO
     */
    private static void insertDataToDB(final AppDatabase db, DataListEntity data) {
//        will clears all data and inserts new data
        db.listDao().deleteAll();
        db.listDao().insertList(data);
    }

    /*
        Async task which does insertion of data into database table.
     */
    private static class InsertAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDatabase;
        private final DataListEntity mData;

        InsertAsync(AppDatabase db, DataListEntity data) {
            mDatabase = db;
            mData = data;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            insertDataToDB(mDatabase,mData);
            return null;
        }

    }
}
