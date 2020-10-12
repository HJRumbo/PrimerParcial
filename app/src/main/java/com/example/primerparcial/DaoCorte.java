package com.example.primerparcial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoCorte {
    SQLiteDatabase conexion;
    ArrayList<Corte> lista = new ArrayList<>();
    Corte corte;
    Context context;
    String nombreDB = "DBNotas";
    String tabla = "create table if not exists cortes(id integer primary key autoincrement, numCorte int, actividad text, porcentaje int, nota double, notaCalculada double, codigoMat text)";

    public DaoCorte(Context _context) {
        this.context = _context;
        conexion = _context.openOrCreateDatabase(nombreDB, Context.MODE_PRIVATE, null);
        conexion.execSQL(tabla);
    }

    public void insertar(ArrayList<Corte> listaCorte){
        ContentValues contenedor = new ContentValues();
        for (Corte _corte:listaCorte) {
            contenedor.put("numCorte", _corte.getNumCorte());
            contenedor.put("actividad", _corte.getActividad());
            contenedor.put("porcentaje", _corte.getPorcentaje());
            contenedor.put("nota", _corte.getNota());
            contenedor.put("notaCalculada", _corte.getNotaCalculada());
            contenedor.put("codigoMat", _corte.getCodigoMateria());
            conexion.insert("cortes", null, contenedor);
        }

    }

    public boolean Eliminar(int id){

        return conexion.delete("cortes", "id = " + id,null)>0;
    }

    public boolean EliminarXMateria(String materia){
        String codigo = "'"+materia+"'";
        return conexion.delete("cortes", "codigoMat = " + codigo,null)>0;
    }

    public boolean Editar(Corte _corte){
        ContentValues contenedor = new ContentValues();
        contenedor.put("actividad", _corte.getActividad());
        contenedor.put("porcentaje", _corte.getPorcentaje());
        contenedor.put("nota", _corte.getNota());
        contenedor.put("notaCalculada", _corte.getNotaCalculada());
        contenedor.put("codigoMat", _corte.getCodigoMateria());
        return (conexion.update("cortes", contenedor, "id = " + _corte.getId(), null))>0;
    }

    public ArrayList<Corte> VerTodos (){
        lista.clear();
        Cursor cursor = conexion.rawQuery("select * from cortes", null);
        if(cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                lista.add(new Corte(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getDouble(4),
                                cursor.getDouble(5),
                                cursor.getString(6)
                        )
                );

            } while (cursor.moveToNext());
        }
        return lista;
    }

    public Corte VerUno(int posicion){
        Cursor cursor = conexion.rawQuery("select * from cortes", null);
        cursor.moveToPosition(posicion);
        corte = new Corte(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getDouble(4),
                cursor.getDouble(5),
                cursor.getString(6)
        );
        return corte;
    }

    public ArrayList<Corte> VerPrimer(int numcorte){
        lista.clear();
        Cursor cursor = conexion.rawQuery("select * from cortes where numCorte = "+ numcorte, null);
        if(cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                lista.add(new Corte(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getDouble(4),
                                cursor.getDouble(5),
                                cursor.getString(6)
                        )
                );

            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<Corte> verMateria(String codigo){
        lista.clear();
        String codigoMat = "'"+codigo+"'";
        Cursor cursor = conexion.rawQuery("select * from cortes where codigoMat = "+ codigoMat, null);
        if(cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                lista.add(new Corte(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getDouble(4),
                                cursor.getDouble(5),
                                cursor.getString(6)
                        )
                );

            } while (cursor.moveToNext());
        }
        return lista;
    }
}
