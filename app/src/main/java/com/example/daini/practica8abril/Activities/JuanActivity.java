package com.example.daini.practica8abril.Activities;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.daini.practica8abril.AyncTask.AsyncTaskRecados;
import com.example.daini.practica8abril.ButtonListener;
import com.example.daini.practica8abril.Models.Recados;
import com.example.daini.practica8abril.R;
import com.example.daini.practica8abril.RecadosAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class JuanActivity extends AppCompatActivity {
    private static ArrayList<Recados> recadosArrayList;
    private static ArrayList<Recados> deServlet;
    private RecadosAdapter adapter;
    private boolean sort;

    public JuanActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juan);



        ButtonListener buttonListener = new ButtonListener(this);



        recadosArrayList = deServlet;
        adapter = new RecadosAdapter(this, recadosArrayList);
        ListView listView = (ListView) findViewById(R.id.list_view_juan);
        if (recadosArrayList != null) {

            Log.d(getClass().getCanonicalName(), " recados array list size " + recadosArrayList.size());
            Collections.sort(recadosArrayList);
            sort = true;
            listView.setAdapter(adapter);
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                recadosArrayList.remove(position);
                adapter.notifyDataSetChanged();
                AsyncTaskRecados asyncTaskRecados = new AsyncTaskRecados(JuanActivity.this);
                asyncTaskRecados.enviarAlServlet(recadosArrayList);
                Log.d(getClass().getCanonicalName(), " recados array list size despues de borrar " + recadosArrayList.size());
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recados recados = recadosArrayList.get(position);
                String nombre = recados.getNombre();
                String direcion1 = recados.getDirecion();
                String direccion2 = recados.getGetDirecion2();
                String description = recados.getDescripcionRecado();

                Intent intent = new Intent(JuanActivity.this, DescriptionRecadoActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("dr1", direcion1);
                intent.putExtra("dr2", direccion2);
                intent.putExtra("des", description);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(Menu.NONE, 1, 1, "Ordenar - más reciente primero");
//        menu.add(Menu.NONE, 2, 2, "Ordenar - más reciente ultimo");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        adapter = new RecadosAdapter(this, recadosArrayList);
        ListView listView = (ListView) findViewById(R.id.list_view_juan);


        switch (item.getItemId()) {
            case R.id.sort_menu:
                if (recadosArrayList != null) {
                    if (!sort) {
                        sort = true;
                        Collections.sort(recadosArrayList);
                        item.setIcon(R.drawable.sort_newest);
                    } else {
                        sort = false;
                        Collections.sort(recadosArrayList, Collections.<Recados>reverseOrder());

                        item.setIcon(R.drawable.sort_oldest);
                    }
                }
                break;
            case R.id.refresh_menu:
                this.finish();
                startActivity(new Intent(this, JuanActivity.class));

                break;
        }
        if (recadosArrayList != null) {
            listView.setAdapter(adapter);
        }
        return true;
    }

    public void getRecadosArrayList(String s){
        Gson gson = new Gson();
        deServlet = gson.fromJson (s, new TypeToken<ArrayList<Recados>>() {
        }.getType());
        Log.d(getClass().getCanonicalName(), "Ha terminado  json = " + s);
        Log.d(getClass().getCanonicalName(), " deServlet array list size juan activity " + deServlet.size());
    }



}

