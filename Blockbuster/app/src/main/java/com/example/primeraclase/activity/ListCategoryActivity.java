package com.example.primeraclase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.primeraclase.R;

import com.example.primeraclase.activity.Fragments.FragmentListaCategoria;


import java.util.List;

public class ListCategoryActivity extends AppCompatActivity implements FragmentListaCategoria.IFragmentListaCategoriaListener{

    private List<String> categoryList;
    private CheckBox seleccionCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        cargarFragmentoLista(false);
    }

    public void cargarFragmentoLista(boolean useAnimation){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        cargarLista();

        FragmentListaCategoria fragmentListaCategoria = new FragmentListaCategoria(this, categoryList);

        if(useAnimation)
            fragmentTransaction.setCustomAnimations(
                    R.anim.entrar_por_izquierda,
                    R.anim.salir_por_derecha,
                    R.anim.entrar_por_derecha,
                    R.anim.salir_por_izquierda);

        fragmentTransaction.replace(R.id.FragmentListaCategorias, fragmentListaCategoria);

        fragmentTransaction.commit();
    }


    public void cargarLista(){
        categoryList.add("Accion");
        categoryList.add("Comedia");
        categoryList.add("Terror");

    }


    @Override
    public void onClickItem(int position) {

    }

    @Override
    public String onClickCheckBox(final int position) {
        String categoria=null;
       boolean comprobacion= findViewById(R.id.checkCategoria).isClickable();
       if(comprobacion){
           categoria=findViewById(R.id.nombreCategoria).toString();
       }

       return categoria;
    }

    @Override
    public void onClickContinuar(){

        Intent i = new Intent(this, SplashActivity.class);
        startActivity(i);
        finish();
    }



}

