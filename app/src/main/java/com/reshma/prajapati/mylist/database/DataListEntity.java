package com.reshma.prajapati.mylist.database;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*
    Class that represents single table
 */
@Entity(tableName = "list")
public class DataListEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "response")
    private  String columnResponse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnResponse() {
        return columnResponse;
    }

    public void setColumnResponse(String columnResponse) {
        this.columnResponse = columnResponse;
    }
}
