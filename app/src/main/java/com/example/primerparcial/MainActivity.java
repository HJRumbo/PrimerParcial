package com.example.primerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DaoMateria daoMateria;
    AdaptadorMateria adaptadorMateria;
    ArrayList<Materia> listaMateria;
    Materia materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoMateria = new DaoMateria(MainActivity.this);
        listaMateria = daoMateria.VerTodos();
        adaptadorMateria = new AdaptadorMateria(listaMateria, daoMateria, this);
        ListView list = (ListView)findViewById(R.id.listaMateria);
        Button btnAgregar = (Button)findViewById(R.id.btnAgregar);
        if (listaMateria != null && listaMateria.size() > 0){
            list.setAdapter(adaptadorMateria);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Dialogo para ver lista previa de registro
            }
        });
        Guardar(btnAgregar);
    }

    public void Guardar(Button btnAgregar){
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo de registrar
                final Dialog dialogo = new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();
                final EditText nombre = (EditText)dialogo.findViewById(R.id.nombre);
                final EditText codigo = (EditText)dialogo.findViewById(R.id.codigo);
                final EditText numCreditos = (EditText)dialogo.findViewById(R.id.numeroCreditos);
                Button btnAdd = (Button)dialogo.findViewById(R.id.btnAdd);
                btnAdd.setText("Agregar");
                Button btnCancel = (Button)dialogo.findViewById(R.id.btnCancel);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            materia = new Materia(
                                    codigo.getText().toString(),
                                    nombre.getText().toString(),
                                    Integer.parseInt(numCreditos.getText().toString())
                            );
                            daoMateria.insertar(materia);
                            listaMateria = daoMateria.VerTodos();
                            adaptadorMateria.notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){

                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo.dismiss();
                    }
                });
            }
        });
    }

}