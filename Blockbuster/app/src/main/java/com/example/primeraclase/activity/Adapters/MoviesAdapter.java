package com.example.primeraclase.activity.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.primeraclase.R;
import com.example.primeraclase.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Movie> chapters;
    private LayoutInflater inflater;

    public MoviesAdapter(Context context, ArrayList<Movie> chapters) {
        this.context = context;
        this.chapters = chapters;
        this.inflater = LayoutInflater.from(context);
    }

    //el método onCreateViewHolder () devuelve una vista personalizada de su diseño
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.single_chapter, parent, false);
        return new CustomViewHolder(view);
    }

    //el metodo onBindViewHolder() este itera cada pelicula.
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Movie movie;
        movie = chapters.get(position);

        holder.tvChapterName.setText(movie.movieName);
        Log.i("url portada", movie.image);
        Picasso.get().load(movie.image).into(holder.ivChapter);

    }

    //el método getItemCount () devuelve el tamaño de la lista
    @Override
    public int getItemCount() {
        return chapters.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView getIvChapter() {
            return ivChapter;
        }

        public TextView getTvChapterName() {
            return tvChapterName;
        }

        public ImageView ivChapter;
        public TextView tvChapterName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvChapterName = (TextView) itemView.findViewById(R.id.tvChapterName);
            ivChapter = (ImageView) itemView.findViewById(R.id.ivChapter);
        }
    }
}
