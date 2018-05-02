package com.reshma.prajapati.mylist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/*
    Interface that performs query to db
 */
@Dao
public interface ListDao {

    @Insert
    void insertList(DataListEntity list);

    @Query("DELETE FROM list")
    void deleteAll();

    @Query("SELECT * FROM list")
    DataListEntity loadAllData();

}
