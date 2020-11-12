package com.example.primeraclase.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CategoriaUsuario")
public class CategoriaUsuario {

        @PrimaryKey(autoGenerate = true)
        @NonNull
        private Integer id;


    @NonNull
    private Integer categoria1;

    @NonNull
    private Integer categoria2;

    @NonNull
    private Integer categoria3;

    public CategoriaUsuario() {

    }

    public CategoriaUsuario(@NonNull Integer id, @NonNull Integer categoria1, @NonNull Integer categoria2, @NonNull Integer categoria3) {
        this.id = id;
        this.categoria1 = categoria1;
        this.categoria2 = categoria2;
        this.categoria3 = categoria3;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(@NonNull Integer categoria1) {
        this.categoria1 = categoria1;
    }

    @NonNull
    public Integer getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(@NonNull Integer categoria2) {
        this.categoria2 = categoria2;
    }

    @NonNull
    public Integer getCategoria3() {
        return categoria3;
    }

    public void setCategoria3(@NonNull Integer categoria3) {
        this.categoria3 = categoria3;
    }
}
