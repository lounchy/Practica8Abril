package com.example.daini.practica8abril.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.daini.practica8abril.ButtonListener;
import com.example.daini.practica8abril.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButtonListener buttonListener = new ButtonListener(this);
        ImageView recadero = (ImageView)findViewById(R.id.recardero_main);
        ImageView usuario = (ImageView)findViewById(R.id.usuario_main);
        recadero.setOnClickListener(buttonListener);
        usuario.setOnClickListener(buttonListener);
    }
}
