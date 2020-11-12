package com.example.primeraclase.persistance.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.primeraclase.model.CategoriaUsuario;

@Dao
public interface CategoriaUsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar( CategoriaUsuario CategoriaUsuario);

    @Query("SELECT * FROM CategoriaUsuario ")
    CategoriaUsuario getLastActual();


    @Query("DELETE FROM CategoriaUsuario WHERE id == :idCategoriaUsuario")
    void eliminar(Integer idCategoriaUsuario);



}
