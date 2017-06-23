package com.example.nikola.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nikola on 23.6.17..
 */
@DatabaseTable(tableName = "film")
public class Film {
    public static final String POLJE_ID = "id";
    public static final String POLJE_NAZIV = "naziv_filma";

    @DatabaseField(columnName = POLJE_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = POLJE_NAZIV)
    private String mName;

    public Film(){

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public Film setmName(String mName) {
        this.mName = mName;
        return null;
    }

    @Override
    public String toString() {
        return mName;
    }
}
