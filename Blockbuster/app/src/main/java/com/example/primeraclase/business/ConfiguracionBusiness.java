package com.example.primeraclase.business;

import com.example.primeraclase.Context;
import com.example.primeraclase.model.Configuracion;
import com.example.primeraclase.model.Usuario;
import com.example.primeraclase.persistance.ConfiguracionDAO;
import com.example.primeraclase.persistance.IConfiguracionDAO;

public class ConfiguracionBusiness {
    private IConfiguracionDAO configuracionDAO;
    private Usuario currentUser;
    private Configuracion currentConf;

    public ConfiguracionBusiness() {
        configuracionDAO = new ConfiguracionDAO();
    }

    public boolean save(Configuracion c) {
        if(currentUser == null)
            currentUser = Context.getUsuarioBusiness().getCurrentUser();

        if(!configuracionDAO.save(c,currentUser.getUsuario()))
            return false;

        currentConf = c;
        return true;
    }

    public boolean createConf(Configuracion conf, String username){

        if(!configuracionDAO.save(conf,username))
            return false;

        return true;
    }

    public Configuracion getConfiguracion() {
        if(currentConf == null){
            currentUser = Context.getUsuarioBusiness().getCurrentUser();
            currentConf = configuracionDAO.getConfiguracion(currentUser.getUsuario());
        }else{
            if(!currentUser.getUsuario().equals(
                    Context.getUsuarioBusiness().getCurrentUser()
                ))
                currentUser = Context.getUsuarioBusiness().getCurrentUser();
                currentConf = configuracionDAO.getConfiguracion(currentUser.getUsuario());
        }

        return currentConf;
    }
}
