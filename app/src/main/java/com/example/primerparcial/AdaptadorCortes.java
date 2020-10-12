package com.example.primerparcial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCortes extends BaseAdapter {
    ArrayList<Corte> lista;
    DaoCorte daoCorte;
    Corte corte;
    Activity activity;

    public AdaptadorCortes(ArrayList<Corte> lista, DaoCorte daoCorte, Activity activity) {
        this.lista = lista;
        this.daoCorte = daoCorte;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
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
            view = li.inflate(R.layout.item_corte, null);
        }

        corte = lista.get(position);

            TextView actividad = (TextView)view.findViewById(R.id.textActividadVer);
            TextView porcentaje = (TextView)view.findViewById(R.id.txtPorcentajeVer);
            TextView nota = (TextView)view.findViewById(R.id.txtNotaVer);
            TextView notaCal = (TextView)view.findViewById(R.id.txtNotaCalVer);

            actividad.setText(corte.getActividad());
            porcentaje.setText(""+corte.getPorcentaje());
            nota.setText(""+corte.getNota());
            notaCal.setText(""+corte.getNotaCalculada());

        return view;
    }

}
