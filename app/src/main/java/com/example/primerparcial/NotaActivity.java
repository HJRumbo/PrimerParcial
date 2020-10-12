package com.example.primerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NotaActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("0.00");
    ArrayList<Corte> listaPrimer, listaCorte, listaSegundo;
    DaoCorte daoCorte;
    DaoMateria daoMateria;
    Corte corte;
    String nombreMat = "";
    String codigoMat = "";
    int numerCorte, total = 100, porcentInt = 0;
    double notaCalcu, notaTotal = 0, porcent, notaa = 0, calporcent, nota1=0, nota2=0, nota3=0;
    Dialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        listaCorte = new ArrayList<>();
        daoCorte = new DaoCorte(NotaActivity.this);
        daoMateria = new DaoMateria(NotaActivity.this);
        dialogo = new Dialog(NotaActivity.this);
        obtener();
        notasPrimerCorte();
        notasSegundoCorte();
        notasTercerCorte();

    }

    public void obtener(){
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            nombreMat = getIntent().getStringExtra("nombre");
            codigoMat = getIntent().getStringExtra("codigo");
        }

        final TextView nombreText = (TextView)findViewById(R.id.textMateria);
        final TextView codigoText = (TextView)findViewById(R.id.textCodigo);
        nombreText.setText(nombreMat);
        codigoText.setText(codigoMat);
    }

    public void onClick(View view){
        Intent miIntent = new Intent(NotaActivity.this,MainActivity.class);
        startActivity(miIntent);
    }

    public ArrayList<Corte> buscarNotas(String codigoMate){
        return daoCorte.verMateria(codigoMate);
    }

    public void notasPrimerCorte(){
        char encontro = 'N';
        listaPrimer = new ArrayList<>();
        listaPrimer = buscarNotas(codigoMat);
        final Button botonAdd = (Button)findViewById(R.id.btnAddPrimer);
        final TextView primerCorte = (TextView)findViewById(R.id.txtNumPCorte);
        final TextView nota = (TextView)findViewById(R.id.txtNotaPCorte);
        final TextView codigoText = (TextView)findViewById(R.id.textCodigo);
        if(listaPrimer == null){
            primerCorte.setText("Primer corte");
            nota.setText("0.0");
            botonAdd.setEnabled(true);
        }else{
            for (Corte corte: listaPrimer
            ) {
                if(corte.getNumCorte()==1){
                    nota1 = nota1 + corte.getNotaCalculada();
                    encontro = 'S';
                }
            }
            if(encontro=='S' && codigoText.getText().toString() == codigoMat){
                nota.setText(""+nota1);
                botonAdd.setEnabled(false);
            }
        }

    }

    public void notasTercerCorte(){
        char encontro = 'N';
        listaPrimer = new ArrayList<>();
        listaPrimer = buscarNotas(codigoMat);
        final Button botonAdd = (Button)findViewById(R.id.btnAddTercer);
        final TextView tercerCorte = (TextView)findViewById(R.id.txtNumTCorte);
        final TextView nota = (TextView)findViewById(R.id.txtNotaTCorte);
        final TextView codigoText = (TextView)findViewById(R.id.textCodigo);
        if(listaPrimer == null){
            tercerCorte.setText("Tercer corte");
            nota.setText("0.0");
            botonAdd.setEnabled(true);
        }else{
            for (Corte corte: listaPrimer
            ) {
                if(corte.getNumCorte()==3){
                    nota3 = nota3 + corte.getNotaCalculada();
                    encontro = 'S';
                }
            }
            if(encontro=='S' && codigoText.getText().toString() == codigoMat){
                nota.setText(""+nota3);
                botonAdd.setEnabled(false);
            }
        }

    }

    public void notasSegundoCorte(){
        char encontro = 'N';
        listaPrimer = new ArrayList<>();
        listaPrimer = buscarNotas(codigoMat);
        final Button botonAdd = (Button)findViewById(R.id.btnAddSegundo);
        final TextView primerCorte = (TextView)findViewById(R.id.txtNumSCorte);
        final TextView nota = (TextView)findViewById(R.id.txtNotaSCorte);
        final TextView codigoText = (TextView)findViewById(R.id.textCodigo);
        if(listaPrimer == null){
            primerCorte.setText("Segundo corte");
            nota.setText("0.0");
            botonAdd.setEnabled(true);
        }else{
            for (Corte corte: listaPrimer
            ) {
                if(corte.getNumCorte()==2){
                    nota2 = nota2 + corte.getNotaCalculada();
                    encontro = 'S';
                }
            }
            if(encontro=='S' && codigoText.getText().toString() == codigoMat){
                nota.setText(""+nota2);
                botonAdd.setEnabled(false);
            }
        }

    }

    public void onClickAdd(View view) {
        notaTotal = 0;notaa = 0; total = 100; notaCalcu = 0;
        porcentInt = 0; nota1=0; nota2=0;nota3=0;
        dialogo.setTitle("Nuevo registro");
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_corte);
        dialogo.show();
        final TextView txtMensaje = (TextView)dialogo.findViewById(R.id.txtMensaje);
        final Button botonGuard = (Button)dialogo.findViewById(R.id.btnGuardAct);
        botonGuard.setEnabled(false);
        switch (view.getId()){
            case R.id.btnAddPrimer:
                txtMensaje.setText("Actividades del primer corte");
                numerCorte = 1;
                break;

            case R.id.btnAddSegundo:
                txtMensaje.setText("Actividades del segundo corte");
                numerCorte = 2;
                break;

            case R.id.btnAddTercer:
                txtMensaje.setText("Actividades del tercer corte");
                numerCorte = 3;
                break;
        }
    }

    EditText nota;
    EditText porcentaje;
    public void AddActividad(View view) {
        final EditText actividad = (EditText)dialogo.findViewById(R.id.txtActividad);
        porcentaje = (EditText)dialogo.findViewById(R.id.txtPorcent);
        nota = (EditText)dialogo.findViewById(R.id.txtNotaAct);
        final Button botonAdd = (Button)dialogo.findViewById(R.id.btnAddAct);
        final Button botonGuard = (Button)dialogo.findViewById(R.id.btnGuardAct);

        porcentInt = Integer.valueOf(porcentaje.getText().toString());
        porcent = Double.valueOf(porcentaje.getText().toString());
        notaa = Double.valueOf(nota.getText().toString());
        if(porcentInt>total){
            Toast.makeText(getApplication(), "El porcentaje que le sobra es "+ total +"%.", Toast.LENGTH_SHORT).show();
        }else {
            if (notaa > 5.0) {
                Toast.makeText(getApplication(), "La nota no puede ser mayor que 5.0", Toast.LENGTH_SHORT).show();
            } else {
                calporcent = (porcent / 100);
                notaCalcu = calporcent * notaa;
                notaTotal = notaTotal + notaCalcu;
                total = total - porcentInt;
                final TextView notaTo = (TextView) dialogo.findViewById(R.id.txtNotaTotal);
                final TextView restante = (TextView) dialogo.findViewById(R.id.txtRestante);
                final TextView codigo = (TextView)findViewById(R.id.textCodigo);
                notaTo.setText("" + df.format(notaTotal));
                restante.setText("Restante: " + total + " %");

                corte = new Corte(
                        numerCorte,
                        actividad.getText().toString(),
                        porcentInt,
                        notaa,
                        notaCalcu,
                        codigo.getText().toString()
                );

                listaCorte.add(corte);

                limpiar();
            }
        }
        if (total <= 0){
            botonAdd.setEnabled(false);
            botonGuard.setEnabled(true);
        }

    }

    public void limpiar(){
        final EditText actividad = (EditText)dialogo.findViewById(R.id.txtActividad);
        final EditText porcentaje = (EditText)dialogo.findViewById(R.id.txtPorcent);
        final EditText nota = (EditText)dialogo.findViewById(R.id.txtNotaAct);
        actividad.setText("");
        porcentaje.setText("");
        nota.setText("");
        actividad.requestFocus();
    }

    public void clickCancel(View view){
        dialogo.dismiss();
    }

    public void guardar(View view){
        try{
            daoCorte.insertar(listaCorte);
            notasPrimerCorte();
            notasSegundoCorte();
            notasTercerCorte();
            dialogo.dismiss();
            editarMateria();

        }catch (Exception e){
            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
    double primeraNota = 0, segundanota = 0, terceranota = 0, notaMateria;
    public void editarMateria(){

        listaCorte = daoCorte.verMateria(codigoMat);

        for (Corte corte: listaCorte
             ) {
                if(corte.getNumCorte()==1){
                    primeraNota = primeraNota + corte.getNotaCalculada();
                }
        }
        for (Corte corte: listaCorte
        ) {
                if(corte.getNumCorte()==2){
                    segundanota = segundanota + corte.getNotaCalculada();
                }
        }
        for (Corte corte: listaCorte
        ) {
                if(corte.getNumCorte()==3){
                    terceranota = terceranota + corte.getNotaCalculada();
                }
        }

        notaMateria = (primeraNota*0.3)+(segundanota*0.3)+(terceranota*0.4);
        daoMateria.EditarNota(codigoMat, notaMateria);
    }

    public void  conPrimer(View view){
        Intent miIntent = new Intent(NotaActivity.this,CortesActivity.class);
        miIntent.putExtra("nombre", nombreMat);
        miIntent.putExtra("codigo", codigoMat);
        miIntent.putExtra("numeroCorte", 1);
        startActivity(miIntent);
    }
}