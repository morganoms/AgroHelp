package com.morganoliveira.agrohelp.View;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.morganoliveira.agrohelp.Controller.BibliotecaController;
import com.morganoliveira.agrohelp.Model.Database;
import com.morganoliveira.agrohelp.Model.TermoTecnico;
import com.morganoliveira.agrohelp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ItemTermo extends AppCompatActivity {

    private TextView descricao;
    private TextView fonte;
    private ImageView IM;
    private Database agroDB;
    private TermoTecnico TT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_termo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        //iniciaDB();
        Intent i = this.getIntent();
        int id = i.getExtras().getInt("ID");
        BibliotecaController bc = new BibliotecaController(this);
        TT = bc.TermoEspecifico(id);
        //TT = agroDB.TermoDetalhado(id);

        getSupportActionBar().setTitle(TT.getNome());
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

       // descricao = (TextView)findViewById(R.id.descricao);
        fonte = (TextView)findViewById(R.id.fonte);
        IM = (ImageView)findViewById(R.id.imagemGrande);

       // descricao.setText(TT.getDescricao());
        fonte.setText(TT.getFonte());
        byte [] image = TT.getImage();
        Bitmap bm = BitmapFactory.decodeByteArray(image,0, image.length);
        IM.setImageBitmap(bm);

        WebView wb = (WebView)findViewById(R.id.web);
        String text = "<html><body>"
                + "<p align=\"justify\">"
                + TT.getDescricao()
                + "</p> "
                + "</body></html>";

        wb.loadData(text,"text/html;charset=UTF-8",null);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, act_biblioteca.class));
                //this.finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

    /*private void iniciaDB() {
        agroDB = new Database(this);
        File database = getApplicationContext().getDatabasePath(Database.DATABASE_NAME);
        if (database.exists() == false) {
            agroDB.getReadableDatabase();
            if (copiaBanco(this)) {
                alert("Banco Copiado");
            } else {
                alert("Erro ao Copiar");
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context contexto) {
        try {
            InputStream ip = contexto.getAssets().open(Database.DATABASE_NAME);
            String outFile = Database.LOCALDB + Database.DATABASE_NAME;
            OutputStream op = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int lenght = 0;
            while ((lenght = ip.read(buff)) > 0) {
                op.write(buff, 0, lenght);
            }
            op.flush();
            op.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }*/


}
