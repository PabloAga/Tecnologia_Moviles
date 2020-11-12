package com.example.primeraclase.persistance;

import com.example.primeraclase.model.Configuracion;

public interface IConfiguracionDAO {
    public Configuracion getConfiguracion(String username);
    public boolean save(Configuracion u, String username);
}
