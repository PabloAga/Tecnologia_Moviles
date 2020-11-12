package com.example.primeraclase.persistance;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.primeraclase.model.CategoriaUsuario;
import com.example.primeraclase.persistance.dao.CategoriaUsuarioDAO;


@androidx.room.Database(entities = {CategoriaUsuario.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private final String databaseName = "AppDatabase";

    public abstract CategoriaUsuarioDAO climaDAO();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,Database.class, "databaseName").build();
                }
            }
        }
        return INSTANCE;
    }
}
