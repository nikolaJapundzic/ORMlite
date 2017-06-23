package com.example.nikola.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.nikola.ormlite.helper.DatabaseHelper;
import com.example.nikola.ormlite.model.Film;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_nazivFilma;
    Button btn_unesiFilm;
    Button but_sve;

    DatabaseHelper helper;
    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nazivFilma = (EditText) findViewById(R.id.et_nazivFilma);
        btn_unesiFilm = (Button) findViewById(R.id.btn_unesiFilm);
        but_sve = (Button) findViewById(R.id.but_sve);



        helper = new DatabaseHelper(this);


        btn_unesiFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upis();

            }
        });
        but_sve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                celaLista();
            }
        });





    }


    private void upis(){
        film = new Film();
        film.setmName(et_nazivFilma.getText().toString());

        try {
            getDatabaseHelper().getFilmDao().create(film);
            celaLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void celaLista(){
        List<Film> list;
        try {

            list = getDatabaseHelper().getFilmDao().queryForAll();
            ListAdapter adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.lista, list);
            final ListView listView = (ListView) findViewById(R.id.lv_lista);
            listView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public DatabaseHelper getDatabaseHelper() {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return helper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }
}
