package com.example.primerparcial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoMateria {
    SQLiteDatabase conexion;
    ArrayList<Materia> lista = new ArrayList<Materia>();
    Materia materia;
    Context context;
    String nombreDB = "DBNotas";
    String tabla = "Create table if not exists materia(codigo text primary key, nombre text, numCreditos int, nota double default 0.0)";

    public DaoMateria(Context _context){
        this.context = _context;
        conexion = _context.openOrCreateDatabase(nombreDB, Context.MODE_PRIVATE, null);
        conexion.execSQL(tabla);
    }

    public boolean insertar(Materia _materia){
        double nota = 0.0;
        ContentValues contenedor = new ContentValues();
        contenedor.put("codigo", _materia.getCodigo());
        contenedor.put("nombre", _materia.getNombre());
        contenedor.put("numCreditos", _materia.getNumCreditos());
        return (conexion.insert("materia", null, contenedor))>0;
    }

    public boolean Eliminar(String codigo){
        String cod = "'"+codigo+"'";
        return conexion.delete("materia", "codigo = " + cod,null)>0;
    }

    public boolean Editar(Materia _materia){
        ContentValues contenedor = new ContentValues();
        contenedor.put("numCreditos", _materia.getNumCreditos());
        contenedor.put("nombre", _materia.getNombre());
        String codigo = "'"+_materia.getCodigo()+"'";
        return (conexion.update("materia", contenedor, "codigo = " + codigo, null))>0;
    }

    public boolean EditarNota(String codig, double nota){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nota", nota);
        String codigo = "'"+codig+"'";
        return (conexion.update("materia", contenedor, "codigo = " + codigo, null))>0;
    }

    public ArrayList<Materia> VerTodos (){
        lista.clear();
        Cursor cursor = conexion.rawQuery("select * from materia", null);
        if(cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                lista.add(new Materia(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getDouble(3)
                        )
                );

            } while (cursor.moveToNext());
        }
        return lista;
    }

    public Materia VerUno(String codigo){
        Cursor cursor = conexion.rawQuery("select * from materia where codigo = "+ codigo,null);
        materia = new Materia(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getDouble(3)
        );
        return materia;
    }
}
