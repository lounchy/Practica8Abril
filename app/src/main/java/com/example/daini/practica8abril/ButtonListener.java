package com.example.daini.practica8abril;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.daini.practica8abril.Activities.JuanActivity;
import com.example.daini.practica8abril.Activities.UsuarioActivity;
import com.example.daini.practica8abril.AyncTask.AsyncTaskRecados;
import com.example.daini.practica8abril.Models.Recados;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Daini on 03/04/2017.
 */

public class ButtonListener implements View.OnClickListener {
    private EditText et_nombre,et_direcion1,et_direcion2,et_descripcion;
    private String str_nombre,str_direcion1,str_direcion2,str_descripcion, str_fecha, str_hora;
    private int mes, dia, hora, minute, segundo;
    private Activity activity;
    private static ArrayList<Recados> preparoLista = new ArrayList<>();
    private static ArrayList<Recados> deServlet;

    public ButtonListener() {
    }

    public ButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.recardero_main:
                activity.startActivity(new Intent(activity, JuanActivity.class));
                break;
            case R.id.usuario_main:
                activity.startActivity(new Intent(activity, UsuarioActivity.class));
                break;
            case R.id.enviar_recado_b:
                enviarRecado();


        }


    }
    private void enviarRecado(){
        encontrarEditText();
        editTextToString();
        sacarFechaHora();
        AsyncTaskRecados asyncTaskRecados = new AsyncTaskRecados(activity);

           if (preparoLista.size()>1 &&deServlet!=null){
            if (preparoLista.size()>=deServlet.size()){
                preparoLista = deServlet;
                preparoLista.add(new Recados(str_nombre, str_fecha, str_hora, str_direcion1, str_direcion2, str_descripcion));
            }
        }else {
               preparoLista.add(new Recados(str_nombre, str_fecha, str_hora, str_direcion1, str_direcion2, str_descripcion));
           }

        Log.d(getClass().getCanonicalName(), " preparo lista size " + preparoLista.size());
        asyncTaskRecados.enviarAlServlet(preparoLista);




    }

    private void encontrarEditText(){
        et_nombre = (EditText)activity.findViewById(R.id.nombre_et);
        et_direcion1= (EditText)activity.findViewById(R.id.direcion_et);
        et_direcion2 = (EditText)activity.findViewById(R.id.direcion2_et);
        et_descripcion = (EditText)activity.findViewById(R.id.recado_descripcion_et);
    }
    private void editTextToString(){
        str_nombre = et_nombre.getText().toString();
        str_direcion1 = et_direcion1.getText().toString();
        str_direcion2 = et_direcion2.getText().toString();
        str_descripcion = et_descripcion.getText().toString();
    }
    private void sacarFechaHora(){
        Calendar c = Calendar.getInstance();
        mes = c.get(Calendar.DATE);
        dia = c.get(Calendar.DAY_OF_MONTH);
        hora = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        segundo = c.get(Calendar.SECOND);
        str_fecha = mes +"/" + dia;
        str_hora = hora + ":" + minute + ":" + segundo;
        Log.d(getClass().getCanonicalName(), "Current time => "+c.getTime() +" fecha = " + str_fecha + " hora " + str_hora);

    }
    public void getRecadosArrayList(String s){
        Gson gson = new Gson();
        deServlet = gson.fromJson (s, new TypeToken<ArrayList<Recados>>() {
        }.getType());
        Log.d(getClass().getCanonicalName(), "Ha terminado  json = " + s);
        Log.d(getClass().getCanonicalName(), " deServlet array list size ButtonListener " + deServlet.size());

    }
}
