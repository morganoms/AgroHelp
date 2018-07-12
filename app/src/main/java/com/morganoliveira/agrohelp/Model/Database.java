package com.morganoliveira.agrohelp.Model;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.util.ArrayList;

public class Database extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "agro.db";
    private Context contexto;
    private SQLiteDatabase myDB;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        contexto = context;

    }







    public void openDB(){
        File dbFile = contexto.getDatabasePath(DATABASE_NAME); //getApplicationInfo().dataDir + "/databases/"+ DATABASE_NAME;
        if(myDB != null && myDB.isOpen()){
            return;
        }

       //myDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);

        myDB = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);

    }

    public void closeDB(){
        if(myDB != null){
            myDB.close();
        }

    }

    public ArrayList<TermoTecnico> allTermos(){
        openDB();
        //myDB = this.getWritableDatabase();
        ArrayList<TermoTecnico> termo = new ArrayList<TermoTecnico>();
        String sql = "SELECT * FROM TermosTecnicos ";
        if(myDB != null) {

            Cursor cursor = myDB.rawQuery(sql, null);
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

    public TermoTecnico TermoDetalhado(int id){
        openDB();
        TermoTecnico TT = new TermoTecnico();
        myDB = this.getWritableDatabase();
        ArrayList<TermoTecnico> termo = new ArrayList<TermoTecnico>();
        String sql = "select* from TermosTecnicos where TermosTecnicos.ID = " + String.valueOf(id);
        Cursor cursor = myDB.rawQuery(sql, null);
        if(cursor.getCount() == 1) {
            if (cursor.moveToFirst()) {

                TT = new TermoTecnico();
                TT.setID(cursor.getInt(0));
                TT.setDescricao(cursor.getString(1));
                TT.setNome(cursor.getString(2));
                TT.setFonte(cursor.getString(3));
                TT.setImage(cursor.getBlob(4));
                TT.setDecricaoRDZ(cursor.getString(5));
                termo.add(TT);
            }
        }
        cursor.close();
        myDB.close();
        return TT;
    }

}
