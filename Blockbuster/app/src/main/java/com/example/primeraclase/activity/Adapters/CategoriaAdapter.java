package com.example.primeraclase.activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primeraclase.R;
import com.example.primeraclase.model.Categoria;

import java.util.ArrayList;

//Este adaptador es responsable de mostrar la lista vertical.
public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    public ArrayList<Categoria> categorias;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoriaAdapter(ArrayList<Categoria> categorias, Context context) {
        this.categorias = categorias;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    // el método onCreateViewHolder () devuelve una vista personalizada de su diseño
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_subject, parent, false);
        return new ViewHolder(view);
    }

    // el metodo onBindViewHolder el que itera al scrollear
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recyclerView.setAdapter(new MoviesAdapter(context, categorias.get(position).movies));//se obtiene la lista de peliculas por categoria
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setHasFixedSize(true);
        holder.tvHeading.setText(categorias.get(position).categoriaName);
    }

    // el método getItemCount () devuelve el tamaño de la lista
    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView tvHeading;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rvChapters);
            tvHeading = (TextView) itemView.findViewById(R.id.tvSubjectName);
        }
    }
}