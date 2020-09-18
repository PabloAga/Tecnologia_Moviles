package com.example.primeraclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class PrincipalActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;
    private Button btnIniciar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnIniciar = findViewById(R.id.init_button);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });
    }


    private void crearUsuario() {
        Intent RegistrarUsuario = new Intent(PrincipalActivity.this, RegistrarUsuarioActivity.class);
        startActivity(RegistrarUsuario);
    }
}