package com.example.primeraclase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primeraclase.R;
import com.example.primeraclase.business.UsuarioBusiness;
import com.example.primeraclase.model.Usuario;
import com.example.primeraclase.Context;
import com.example.primeraclase.exception.ExepcionUsuario;
import com.example.primeraclase.business.ConfiguracionBusiness;
import com.example.primeraclase.model.Configuracion;


public class RegistrarUsuarioActivity extends AppCompatActivity {

    private EditText nuevoUsuario;
    private EditText nuevaPassword;
    private EditText nuevoCorreo;
    private Button btnAgregar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        setTitle("Crear usuario");

        nuevoUsuario = findViewById(R.id.nuevoUsuario);
        nuevaPassword = findViewById(R.id.nuevaPassword);
        nuevoCorreo = findViewById(R.id.nuevoCorreo);
        btnAgregar = findViewById(R.id.btnAgregarUsuario);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario(v);
            }
        });
    }

    private void registrarUsuario(View v){

        Usuario u = new Usuario();
        u.setUsuario(nuevoUsuario.getText().toString());
        u.setPassword(nuevaPassword.getText().toString());
        u.setEmail(nuevoCorreo.getText().toString());
        UsuarioBusiness ususarioBO = Context.getUsuarioBusiness();
        try {
            ususarioBO.checkUser(getApplicationContext(),u);
        }catch (ExepcionUsuario e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }

        UsuarioBusiness userBO = Context.getUsuarioBusiness();
        if(userBO.getUsuario(nuevoUsuario.getText().toString()) != null){
            Toast.makeText(v.getContext(),"El usuario ya existe", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean valid = userBO.save(u);

        if(valid){
            ConfiguracionBusiness cBO = Context.getConfiguracionBusiness();
            Configuracion conf = new Configuracion();
            valid = cBO.createConf(conf,u.getUsuario());
        }


        if(valid)
            Toast.makeText(v.getContext(),"Se creo correctamente", Toast.LENGTH_SHORT).show();

        else {
            Toast.makeText(v.getContext(), "No se creo correctamente", Toast.LENGTH_SHORT).show();
            userBO.deleteUsuario(u.getUsuario());
        }

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}