package com.example.primeraclase.activity.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primeraclase.Context;
import com.example.primeraclase.R;
import com.example.primeraclase.activity.Adapters.CategoriaAdapter;
import com.example.primeraclase.activity.LoginActivity;
import com.example.primeraclase.model.Categoria;
import com.example.primeraclase.model.Movie;
import com.example.primeraclase.utils.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentTransaction ;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentHome extends Fragment {

    private RecyclerView rvSubject;
    private CategoriaAdapter categoriaAdapter;
    private ArrayList<Categoria> categorias;

    BottomNavigationView bottomNavigation;


    private ArrayList<Categoria> peliculas;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contenedor = inflater.inflate(R.layout.fragment_home,container,false);

        bottomNavigation =contenedor.findViewById( R. id . bottom_navigation ) ;
        bottomNavigation. setOnNavigationItemSelectedListener ( navigationItemSelectedListener ) ;
        openFragment ( HomeICFragment. newInstance ( "" , "" )) ;
        Util util = new Util();
        boolean conection = util.isNetworkConnected(getContext());

        initComponents(contenedor);
        String prueba="28,37,27";

        // Chequea si hay conexión a Internet.
        if(conection) {
            new FragmentHome.DownloadInfoTask().execute(prueba);
        }

        return contenedor;
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void cerrarSesion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.cerrarSesionMensaje)
                .setPositiveButton(R.string.ACEPTAR, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.content.Context context = getContext().getApplicationContext();
                        if(!Context.getUsuarioBusiness().setCurrentUser(null))
                            return;
                        getActivity().finish();
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        startActivity(i);

                    }
                })
                .setNegativeButton(R.string.CANCELAR, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }
    private void initComponents(View contenedor) {
        rvSubject = contenedor.findViewById(R.id.rvSubject);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_menu:
                        cerrarSesion();
                            return true;
                        case R.id.navigation_lupa:
                            return true;
                        case R.id.navigation_notifications:
                            return true;
                    }
                    return false;
                }
            };

    private InputStream handleHttpConnection(String genero) throws IOException {
        URL url = null;
        try {
            url = new URL("https://api.themoviedb.org/3/discover/movie?api_key=07d1c10fb5c7fb2196dff5d4af1b7557&lenguage=es-US&sort_by=popularity&include_adult=false&with_genres="+genero);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("setResponseMovies", genero);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Log.i("setResponseMovies222", genero);
        return new BufferedInputStream(connection.getInputStream());
    }


    private ArrayList<Movie> convertStreamJson(InputStream in) throws IOException {
        Log.i("convertStreamJson", "entro");
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {

            return readJson(reader);
        } finally {
            reader.close();
        }
    }


    public ArrayList<Movie> readJson(JsonReader reader) throws IOException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie = new Movie();
        reader.beginObject();
        int id=1;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("results") && reader.peek() != JsonToken.NULL) {
                reader.beginArray();
                while (reader.hasNext()) {
                    movie=readTitleMovie(reader,id);
                    if(movie.image!=null){
                        movies.add (movie);
                        id++;
                    }

                }
                reader.endArray();
            }
            else {
                reader.skipValue();
            }
        }

        reader.endObject();
        return movies;
    }

    public Movie readTitleMovie(JsonReader reader, int id) throws IOException {
        Movie movie= new Movie();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("title")) {
                movie.id=id;
                movie.movieName= reader.nextString();
            } else if (name.equals("poster_path")&& reader.peek() != JsonToken.NULL) {
                movie.image = "https://image.tmdb.org/t/p/w500"+reader.nextString();
                id++;
            }else {
                movie.image = null;
                id++;
                reader.skipValue();
            }
        }
        reader.endObject();
        return movie;
    }
    //AsyncTask
    private class  DownloadInfoTask extends AsyncTask<String, Void, ArrayList<ArrayList<Movie>>> {
        @Override
        protected ArrayList<ArrayList<Movie>> doInBackground(String... urls) {
            ArrayList<ArrayList<Movie>> categorias = new ArrayList<ArrayList<Movie>>();

            try {
                categorias.add(convertStreamJson(handleHttpConnection("28")));
                categorias.add(convertStreamJson(handleHttpConnection("35")));
                categorias.add(convertStreamJson(handleHttpConnection("27")));
                return categorias;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<Movie>> s) {
            super.onPostExecute(s);

            ArrayList<Categoria> subjects = new ArrayList<Categoria>();
            Categoria accion = new Categoria();
            accion.id = 1;
            accion.categoriaName = "Accion";
            accion.movies = s.get(0);

            Categoria comedia= new Categoria();
            comedia.id = 1;
            comedia.categoriaName = "Comedia";
            comedia.movies = s.get(1);
            Categoria terror = new Categoria();
            terror.id = 1;
            terror.categoriaName = "Terror";
            terror.movies = s.get(2);


            subjects.add(accion);
            subjects.add(comedia);
            subjects.add(terror);

            categoriaAdapter = new CategoriaAdapter(subjects, getContext());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            rvSubject.setLayoutManager(manager);
            rvSubject.setAdapter(categoriaAdapter);
            peliculas=subjects;

        }



    }

}
