package com.example.daini.practica8abril.AyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.daini.practica8abril.Activities.JuanActivity;
import com.example.daini.practica8abril.Activities.UsuarioActivity;
import com.example.daini.practica8abril.ButtonListener;
import com.example.daini.practica8abril.Models.Recados;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Daini on 04/04/2017.
 */

public class AsyncTaskRecados extends AsyncTask<String, Void,String> {
    private Context context;
    public AsyncTaskRecados(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String devovler = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        String strPost = "http://192.168.1.38:8080/Practica8Abril/ServletRecados";
        String mensaje_json = params[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;

        try {
            url = new URL(strPost);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            OutputStream outputStream =  httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(mensaje_json);
            outputStreamWriter.close();

            int res_code = httpURLConnection.getResponseCode();
            if (res_code == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                devovler = bufferedReader.readLine();
            }

        }catch (Throwable throwable){
            Log.d(getClass().getCanonicalName(), "Algo fue mal!");

        }finally {
            httpURLConnection.disconnect();
        }

        return devovler;
    }

    @Override
    protected void onPostExecute(String s) {
        JuanActivity juanActivity = new JuanActivity();
        juanActivity.getRecadosArrayList(s);
        ButtonListener buttonListener = new ButtonListener();
        buttonListener.getRecadosArrayList(s);
    }

    public void enviarAlServlet(ArrayList<Recados> arrayList){
        Gson gson = new Gson();
        String listaGson = gson.toJson(arrayList);
        AsyncTaskRecados asyncTaskRecados = new AsyncTaskRecados(context);
        asyncTaskRecados.execute(listaGson);
    }
}
