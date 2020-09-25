package com.example.primeraclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class RegistrarUsuarioActivity extends AppCompatActivity {
    static final int PICK_CONTACT_REQUEST = 1;
    private Button btnRegistrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        setTitle("Crear usuario");

        btnRegistrar = findViewById(R.id.btnAgregarUsuario);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }


    private void registrarUsuario() {
        Intent RegistrarUsuario = new Intent(RegistrarUsuarioActivity.this, LoginActivity.class);
        startActivity(RegistrarUsuario);
    }
}