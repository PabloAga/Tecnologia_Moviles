package com.example.primeraclase.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.primeraclase.R;
import com.example.primeraclase.Context;
import com.example.primeraclase.model.Usuario;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Context.setContext(getApplicationContext());

        Usuario aux = Context.getUsuarioBusiness().getCurrentUser();
        if(aux != null){
            Context.getUsuarioBusiness().setMantenerSesion(true);

            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            finish();
            return;
        }else{
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
