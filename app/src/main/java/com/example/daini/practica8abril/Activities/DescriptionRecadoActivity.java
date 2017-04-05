package com.example.daini.practica8abril.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.daini.practica8abril.ButtonListener;
import com.example.daini.practica8abril.R;

import org.w3c.dom.Text;

public class DescriptionRecadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_recado);



        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String direcion1 = intent.getStringExtra("dr1");
        String direccion2 = intent.getStringExtra("dr2");
        String description = intent.getStringExtra("des");

        TextView tv_nombre = (TextView)findViewById(R.id.nombre_tv);
        TextView tv_dir1 = (TextView)findViewById(R.id.dir1_tv);
        TextView tv_dir2 = (TextView)findViewById(R.id.dir2_tv);
        TextView tv_descr = (TextView)findViewById(R.id.description_tv);

        tv_nombre.setText(nombre);
        tv_dir1.setText(direcion1);
        tv_dir2.setText(direccion2);
        tv_descr.setText(description);



    }
}
