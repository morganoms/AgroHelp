package com.morganoliveira.agrohelp.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.morganoliveira.agrohelp.Model.TermoTecnico;
import com.morganoliveira.agrohelp.R;

import java.util.ArrayList;

/**
 * Created by Morgan Oliveira on 24/12/2017.
 */

public class TermoAdapter extends ArrayAdapter<TermoTecnico> {

    private final Context context;
    private final ArrayList<TermoTecnico> elementos;



    public TermoAdapter(Context context, ArrayList<TermoTecnico> elementos) {
        super(context, R.layout.bibliotecav2, elementos);
        this.context = context;
        this.elementos = elementos;



    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.bibliotecav2, parent, false);



        final TextView nomeTermo= (TextView) convertView.findViewById(R.id.nome);
        TextView RDZ= (TextView) convertView.findViewById(R.id.endereco);
        ImageView imagem = (ImageView) convertView.findViewById(R.id.imagem);

        String nome = elementos.get(position).getNome();
        final int id = elementos.get(position).getID();

        nomeTermo.setText(nome);
        RDZ.setText(elementos.get(position).getDecricaoRDZ());
        byte [] image = elementos.get(position).getImage();
        Bitmap bm = BitmapFactory.decodeByteArray(image,0, image.length);
        imagem.setImageBitmap(bm);

       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemTermo(id);
            }
        });

        return convertView;

    }

    public void openItemTermo(int id){
        Intent i = new Intent(context, ItemTermo.class);

        i.putExtra("ID", id);

        context.startActivity(i);
    }


}
