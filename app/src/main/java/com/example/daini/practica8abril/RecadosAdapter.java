package com.example.daini.practica8abril;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.daini.practica8abril.Models.Recados;

import java.util.ArrayList;

/**
 * Created by Daini on 03/04/2017.
 */

public class RecadosAdapter extends ArrayAdapter<Recados> {

    public RecadosAdapter(Context context, ArrayList<Recados> recadosArrayList) {
        super(context, 0, recadosArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View elemento = convertView;
        if (elemento == null){
            elemento = LayoutInflater.from(getContext()).inflate(R.layout.pintar_recados, parent, false);
        }else {
            elemento = convertView;
        }
        Recados datoActual = getItem(position);
        TextView tv_nombre = (TextView)elemento.findViewById(R.id.pintar_nombre_tv);
        TextView tv_fecha = (TextView)elemento.findViewById(R.id.pintar_fecha_tv_tv);
        TextView tv_hora = (TextView)elemento.findViewById(R.id.pintar_hora_tv);


        tv_nombre.setText(datoActual.getNombre());
        tv_fecha.setText(datoActual.getFecha());
        tv_hora.setText(datoActual.getHora());


        return elemento;
    }
}
