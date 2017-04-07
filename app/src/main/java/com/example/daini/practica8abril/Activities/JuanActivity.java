package com.example.daini.practica8abril.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.daini.practica8abril.AyncTask.AsyncTaskRecados;
import com.example.daini.practica8abril.Models.Recados;
import com.example.daini.practica8abril.R;
import com.example.daini.practica8abril.RecadosAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class JuanActivity extends AppCompatActivity {
    private static ArrayList<Recados> recadosArrayList; //Array de app
    private static ArrayList<Recados> deServlet;        //Array de servidor
    private RecadosAdapter adapter;
    private boolean sort;

    //Constructor basio
    public JuanActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juan);



        recadosArrayList = deServlet; //seteo array con valores resibidos de servlet

        adapter = new RecadosAdapter(this, recadosArrayList);//encuentro adapter
        ListView listView = (ListView) findViewById(R.id.list_view_juan);//preparo ListView
        if (recadosArrayList != null) {// hay algo en array

            Log.d(getClass().getCanonicalName(), " recados array list size " + recadosArrayList.size());
            Collections.sort(recadosArrayList);//ordeno lista para que aparesca como priemero dato ultimo
            sort = true;//lista ha sido ordenada poniendo eltimo primero
            listView.setAdapter(adapter);//adapter seteado
        }

        //Seteo on long click para borrar dato
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                recadosArrayList.remove(position);//dato borrado con position elegida
                adapter.notifyDataSetChanged();//adapter avisado
                AsyncTaskRecados asyncTaskRecados = new AsyncTaskRecados(JuanActivity.this);//encuentro asynctask
                asyncTaskRecados.enviarAlServlet(recadosArrayList);//mando array actualizado
                Log.d(getClass().getCanonicalName(), " recados array list size despues de borrar " + recadosArrayList.size());
                return true;
            }
        });
        //on item click listener abrira actividad description
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //busco position en array
                Recados recados = recadosArrayList.get(position);
                //defino valores en position selectionada
                String nombre = recados.getNombre();
                String direcion1 = recados.getDirecion();
                String direccion2 = recados.getGetDirecion2();
                String description = recados.getDescripcionRecado();
                //preparo intent
                Intent intent = new Intent(JuanActivity.this, DescriptionRecadoActivity.class);
                //añado extras
                intent.putExtra("nombre", nombre);
                intent.putExtra("dr1", direcion1);
                intent.putExtra("dr2", direccion2);
                intent.putExtra("des", description);
                startActivity(intent);//lanzo intent
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflo menu.xml
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //preparo adapter
        adapter = new RecadosAdapter(this, recadosArrayList);
        //encuentro listview
        ListView listView = (ListView) findViewById(R.id.list_view_juan);


        switch (item.getItemId()) {
            case R.id.sort_menu://caso ordenar
                if (recadosArrayList != null) {
                    if (sort) {//ha sido ordenada ultimo al primero
                        sort = false;//
                        Collections.sort(recadosArrayList, Collections.<Recados>reverseOrder());//ordeno reverse

                        item.setIcon(R.drawable.sort_oldest);//cambio icono de ordenar
                    } else {//ha sido ordenada primero al ultimo

                        sort = true;
                        Collections.sort(recadosArrayList);//ordeno
                        item.setIcon(R.drawable.sort_newest);//camio icono

                    }
                }
                break;
            case R.id.refresh_menu://caso refresh activity
                this.finish();//termino actividad
                startActivity(new Intent(this, JuanActivity.class));//lanzo de nuevo

                break;
        }
        if (recadosArrayList != null) {//hay algo en array
            listView.setAdapter(adapter);//seteo listview con adapter
        }
        return true;
    }

    //sacar array list de servidor
    public void getRecadosArrayList(String s){
        Gson gson = new Gson();//defino gson
        //descodifico json
        deServlet = gson.fromJson (s, new TypeToken<ArrayList<Recados>>() {
        }.getType());
        Log.d(getClass().getCanonicalName(), "Ha terminado  json = " + s);
        Log.d(getClass().getCanonicalName(), " deServlet array list size juan activity " + deServlet.size());
    }
}

