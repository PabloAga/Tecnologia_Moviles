package com.example.primeraclase.activity.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primeraclase.R;
import com.example.primeraclase.activity.Fragments.FragmentListaCategoria;

import java.util.List;


public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ListCategoryViewHolder> {


    private List<String> lista;
    private FragmentListaCategoria.IFragmentListaCategoriaListener onClick;

    public ListCategoryAdapter(List<String> categoryList,
                           @NonNull FragmentListaCategoria.IFragmentListaCategoriaListener onClick) {
        this.lista = categoryList;
        this.onClick = onClick;
    }

    @Override
    public ListCategoryAdapter.ListCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_categoria, parent, false);
        return new ListCategoryAdapter.ListCategoryViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ListCategoryAdapter.ListCategoryViewHolder holder, int position) {
        String categoria = lista.get(position);

        try{

            if(position == 0)
                holder.getCheckCategoria().setVisibility(View.GONE);

            holder.getNombreCategory().setText(categoria);

            String username = lista.get(position);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ListCategoryViewHolder extends RecyclerView.ViewHolder {



        private TextView nombreCategory;
        private CheckBox checkCategoria;


        public ListCategoryViewHolder(View itemView) {
            super(itemView);

            nombreCategory = itemView.findViewById(R.id.nombreUsuario);
            checkCategoria = itemView.findViewById(R.id.checkCategoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onClickItem(getAdapterPosition());
                }
            });

            checkCategoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onClickItem(getAdapterPosition());
                }
            });

        }

        public TextView getNombreCategory() {
            return nombreCategory;
        }

        public void setNombreCategory(TextView nombreCategory) {
            this.nombreCategory = nombreCategory;
        }

        public CheckBox getCheckCategoria() {
            return checkCategoria;
        }

        public void setCheckCategoria(CheckBox checkCategoria) {
            this.checkCategoria = checkCategoria;
        }


    }
}
