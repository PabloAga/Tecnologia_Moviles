package com.example.primeraclase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

import com.example.primeraclase.R;


public class ConfigActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String[]paths = {"Accion", "Comedia", "Terror"};

    static final int PICK_CONTACT_REQUEST = 1;
    private Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ConfigActivity.this,
                android.R.layout.simple_spinner_item,paths);

        btnAceptar = findViewById(R.id.btnAgregarUsuario);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aceptar();
            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }
    private void Aceptar() {
        Intent aceptar = new Intent(ConfigActivity .this, SplashActivity.class);
        startActivity(aceptar);


    }
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;





    }
    }

}