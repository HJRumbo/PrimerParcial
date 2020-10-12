package com.example.primerparcial;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorMateria extends BaseAdapter {
    ArrayList<Materia> lista;
    DaoMateria daoMateria;
    DaoCorte daoCorte;
    Materia materia;
    Activity activity;

    public AdaptadorMateria(ArrayList<Materia> lista, DaoMateria daoMateria, Activity activity) {
        this.lista = lista;
        this.daoMateria = daoMateria;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Materia getItem(int position) {
        materia = lista.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.item, null);
        }
        materia = lista.get(position);
        TextView nombre = (TextView)view.findViewById(R.id.txtNombre);
        TextView codigo = (TextView)view.findViewById(R.id.textCodigo);
        TextView numCreditos = (TextView)view.findViewById(R.id.txtNumeroCreditos);
        TextView nota = (TextView)view.findViewById(R.id.txtNota);
        Button editar = (Button)view.findViewById(R.id.btnEditar);
        Button eliminar = (Button)view.findViewById(R.id.btnEliminar);
        Button agregarNota = (Button)view.findViewById(R.id.btnEAddNota);

        nombre.setText(materia.getNombre());
        codigo.setText(materia.getCodigo());
        numCreditos.setText(""+materia.getNumCreditos());
        nota.setText(""+materia.getNota());
        editar(position, editar);
        eliminar(position, eliminar);
        agregarNota(position, agregarNota);

        return view;
    }

    public void editar(int position, Button editar){
        editar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo de editar
                int pos = Integer.parseInt(v.getTag().toString());
                final Dialog dialogo = new Dialog(activity);
                dialogo.setTitle("Editar registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();
                final EditText nombre = (EditText)dialogo.findViewById(R.id.nombre);
                final EditText codigo = (EditText)dialogo.findViewById(R.id.codigo);
                codigo.setEnabled(false);
                final EditText numCreditos = (EditText)dialogo.findViewById(R.id.numeroCreditos);
                Button btnAdd = (Button)dialogo.findViewById(R.id.btnAdd);
                btnAdd.setText("Guardar");
                Button btnCancel = (Button)dialogo.findViewById(R.id.btnCancel);
                materia = lista.get(pos);
                nombre.setText(materia.getNombre());
                codigo.setText(materia.getCodigo());
                numCreditos.setText(""+materia.getNumCreditos());
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            materia = new Materia(
                                    codigo.getText().toString(),
                                    nombre.getText().toString(),
                                    Integer.parseInt(numCreditos.getText().toString())
                            );
                            daoMateria.Editar(materia);
                            lista = daoMateria.VerTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){

                            Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show();
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

    public void eliminar(int position, Button eliminar){
        daoCorte = new DaoCorte(activity);
        eliminar.setTag(position);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                materia = lista.get(pos);
                AlertDialog.Builder del = new AlertDialog.Builder(activity);
                del.setMessage("¿Estas seguro de eliminar el contacto?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoMateria.Eliminar(materia.getCodigo());
                        daoCorte.EliminarXMateria(materia.getCodigo());
                        lista = daoMateria.VerTodos();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                del.show();
            }
        });
    }

    public void agregarNota(int position, Button agregarNota){
        agregarNota.setTag(position);
        agregarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                materia = lista.get(pos);

                Intent miIntent = new Intent(activity,NotaActivity.class);
                miIntent.putExtra("nombre", materia.getNombre());
                miIntent.putExtra("codigo", materia.getCodigo());
                activity.startActivity(miIntent);
            }
        });
    }
}
