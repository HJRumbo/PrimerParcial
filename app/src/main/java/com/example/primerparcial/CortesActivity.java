package com.example.primerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class CortesActivity extends AppCompatActivity {

    String nombreMat = "";
    String codigoMat = "";
    AdaptadorCortes adaptadorCorte;
    ArrayList<Corte> listaCorte;
    DaoCorte daoCorte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortes);
        obtener();
        daoCorte = new DaoCorte(CortesActivity.this);
        listaCorte = daoCorte.verMateria(codigoMat);
        adaptadorCorte = new AdaptadorCortes(listaCorte, daoCorte, this);
        ListView list = (ListView)findViewById(R.id.listaCorte);
        if (listaCorte != null && listaCorte.size() > 0){
            list.setAdapter(adaptadorCorte);
        }
    }

    public void obtener() {
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            nombreMat = getIntent().getStringExtra("nombre");
            codigoMat = getIntent().getStringExtra("codigo");
        }
    }

    public void onClick(View view){
        Intent miIntent = new Intent(CortesActivity.this,NotaActivity.class);
        startActivity(miIntent);
    }


}