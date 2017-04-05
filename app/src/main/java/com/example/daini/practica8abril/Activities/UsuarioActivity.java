package com.example.daini.practica8abril.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.daini.practica8abril.ButtonListener;
import com.example.daini.practica8abril.Models.Recados;
import com.example.daini.practica8abril.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class UsuarioActivity extends AppCompatActivity {
    private static ArrayList<Recados>recadosArrayList;

    public UsuarioActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        ButtonListener buttonListener = new ButtonListener(this);
        Button enviarRecado = (Button)findViewById(R.id.enviar_recado_b);
        enviarRecado.setOnClickListener(buttonListener);


    }

}
