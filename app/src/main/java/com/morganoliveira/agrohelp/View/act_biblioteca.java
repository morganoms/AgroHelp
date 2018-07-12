package com.morganoliveira.agrohelp.View;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.morganoliveira.agrohelp.Controller.BibliotecaController;
import com.morganoliveira.agrohelp.Model.Database;
import com.morganoliveira.agrohelp.Model.DatabasesAcess;
import com.morganoliveira.agrohelp.Model.TermoTecnico;
import com.morganoliveira.agrohelp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class act_biblioteca extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private ListView LV;
    private ArrayList<TermoTecnico> TT = new ArrayList<TermoTecnico>();
    private ArrayAdapter<TermoTecnico> ArrayAdapter;
    private SearchView sv;
    private SQLiteDatabase myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_biblioteca);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inicializaComponetes();
        addListV();




    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, AH_main.class));
                //this.finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in = getMenuInflater();
        in.inflate(R.menu.menu_serch, menu);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        sv = (SearchView) MenuItemCompat.getActionView(item);
        sv.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<TermoTecnico> TTsearch = new ArrayList<TermoTecnico>();
        for(TermoTecnico termo : TT){

            String name = termo.getNome().toLowerCase();
            if(name.contains(newText)){
                TTsearch.add(termo);
            }
        }

        ArrayAdapter = new TermoAdapter(this, TTsearch);
        LV.setAdapter(ArrayAdapter);

        return true;
    }

    private void inicializaComponetes() {
        LV = (ListView) findViewById(R.id.ListTermo);

    }

    private void addListV() {




        //Database agroDB = new Database(this);
        DatabasesAcess db = DatabasesAcess.getInstance(getApplicationContext());
        db.openDB();
        TT = db.allTermos();

        ArrayAdapter = new TermoAdapter(this, TT);
        LV.setAdapter(ArrayAdapter);

    }






}
