package com.morganoliveira.agrohelp.Controller;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.widget.Toast;


import com.morganoliveira.agrohelp.Model.Database;
import com.morganoliveira.agrohelp.Model.TermoTecnico;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Morgan Oliveira on 26/12/2017.
 */

public class BibliotecaController {

    private Database agroDB;
    private ArrayList<TermoTecnico> TT = new ArrayList<TermoTecnico>();
    private Context contexto;
    private TermoTecnico TE;

    public BibliotecaController(Context contexto) {
        this.contexto = contexto;


    }

    public ArrayList<TermoTecnico> ListaTermos() {
        agroDB = new Database(contexto);

        TT = agroDB.allTermos();
        return TT;
    }

    public TermoTecnico TermoEspecifico(int id){
        agroDB = new Database(contexto);
        TE = agroDB.TermoDetalhado(id);
        return TE;
    }

    /*private void iniciaDB() {
        agroDB = new Database(this);
        File database = getApplicationContext().getDatabasePath(Database.DATABASE_NAME);
        if(database.exists() == false){
            agroDB.getReadableDatabase();
            if(copiaBanco(this)){
                alert("Banco Copiado");
            }else{
                alert("Erro ao Copiar");
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context contexto) {
        try{
            InputStream ip = contexto.getAssets().open(Database.DATABASE_NAME);
            String outFile = Database.LOCALDB + Database.DATABASE_NAME;
            OutputStream op = new FileOutputStream(outFile);
            byte [] buff = new byte[1024];
            int lenght = 0;
            while((lenght = ip.read(buff)) > 0){
                op.write(buff, 0, lenght);
            }
            op.flush();
            op.close();
            return true;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }*/

}
