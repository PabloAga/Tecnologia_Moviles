package com.example.primeraclase.activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primeraclase.R;
import com.example.primeraclase.activity.Adapters.ListCategoryAdapter;


import java.util.List;

public class FragmentListaCategoria extends Fragment {

    private List<String> categoryList;
    private FragmentListaCategoria.IFragmentListaCategoriaListener onClick;

    public FragmentListaCategoria(FragmentListaCategoria.IFragmentListaCategoriaListener onClick, List<String> lista){
        this.onClick = onClick;
        this.categoryList = lista;
    }

    public interface IFragmentListaCategoriaListener {
        public void onClickItem(int position);

        String onClickCheckBox(int position);

        void onClickContinuar();
    }


    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contenedor = inflater.inflate(R.layout.fragment_lista_categorias, container, false);

        RecyclerView rv = contenedor.findViewById(R.id.ListaCategorias);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new ListCategoryAdapter(categoryList, onClick));

        return contenedor;
    }


}
