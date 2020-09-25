package com.example.primeraclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;
    private Button btnCrear;
    private Button btnIniciar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCrear = findViewById(R.id.btnCrearUsuario);
        btnIniciar= findViewById(R.id.iniciar_button);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantallaPricipal();
            }
        });
    }


    private void crearUsuario() {
        Intent RegistrarUsuario = new Intent(LoginActivity.this, RegistrarUsuarioActivity.class);
        startActivity(RegistrarUsuario);
    }
    private void pantallaPricipal() {
        Intent pantallaPricipal = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(pantallaPricipal);
    }
}