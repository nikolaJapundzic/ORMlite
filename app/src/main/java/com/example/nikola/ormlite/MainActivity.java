package com.example.nikola.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nikola.ormlite.helper.DatabaseHelper;
import com.example.nikola.ormlite.model.Film;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText et_nazivFilma;
    TextView tv_idFilm;
    Button btn_unesiFilm;

    DatabaseHelper helper = new DatabaseHelper(this);

    Dao<Film, Integer>filmDao= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nazivFilma = (EditText) findViewById(R.id.et_nazivFilma);
        tv_idFilm = (TextView) findViewById(R.id.tv_idFilm);
        btn_unesiFilm = (Button) findViewById(R.id.btn_unesiFilm);


        try{
            filmDao = helper.getFilmDao();
        }catch (SQLException e){
            e.printStackTrace();
        }

        //upis();


    }


    private void upis(){
        Film film = new Film().setmName("Galaksija");
        try{
            filmDao.create(film);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
