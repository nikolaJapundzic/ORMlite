package com.example.nikola.ormlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.nikola.ormlite.model.Film;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by nikola on 23.6.17..
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormlite.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<Film, Integer> mFilmDao = null;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Film.class);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource, Film.class, true);
            onCreate(db, connectionSource);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Dao<Film, Integer> getFilmDao() throws SQLException {
        if(mFilmDao == null){
            mFilmDao = getDao(Film.class);
        }
        return mFilmDao;
    }

    @Override
    public  void close(){
        mFilmDao = null;
        super.close();
    }

}
