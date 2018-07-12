package com.morganoliveira.agrohelp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Morgan Oliveira on 10/06/2018.
 */

public class DatabasesAcess {
    private Context contexto;
    private SQLiteOpenHelper openH;
    private SQLiteDatabase myDB;
    private static DatabasesAcess instance;

    private DatabasesAcess(Context contexto){
        this.openH = new Database(contexto);

    }

    public static DatabasesAcess getInstance(Context contexto){
        if(instance == null){
            instance = new DatabasesAcess(contexto);
        }
        return instance;
    }

    public void openDB(){
        this.myDB = openH.getWritableDatabase();
    }


    public ArrayList<TermoTecnico> allTermos(){

        ArrayList<TermoTecnico> termo = new ArrayList<TermoTecnico>();
        String sql = "select * from TermosTecnicos";
        if(myDB != null) {

            Cursor cursor = myDB.rawQuery(sql, new String[]{});
            if (cursor.getCount() > 1) {
                if (cursor.moveToFirst()) {
                    do {

                        TermoTecnico TT = new TermoTecnico();
                        TT.setID(cursor.getInt(0));
                        TT.setDescricao(cursor.getString(1));
                        TT.setNome(cursor.getString(2));
                        TT.setFonte(cursor.getString(3));
                        TT.setImage(cursor.getBlob(4));
                        TT.setDecricaoRDZ(cursor.getString(5));
                        termo.add(TT);

                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        }


        myDB.close();
        return termo;
    }

}
