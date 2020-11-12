package com.example.primeraclase;


import com.example.primeraclase.business.ConfiguracionBusiness;
import com.example.primeraclase.business.UsuarioBusiness;

import java.io.File;

public class Context {
    private static UsuarioBusiness usuarioBusiness;
    private static ConfiguracionBusiness configuracionBusiness;
    private static File dataDir;
    private static android.content.Context context;


    /**
     * Este metodo debe ser ejecutado si o si para el correcto funcionamiento de la aplicacion.
     * */
    public static void setContext(android.content.Context c){
        context = c;
        dataDir = c.getFilesDir();
    }
    public static android.content.Context getContext(){return context;}


    public static File getDataDir(){
        return dataDir;
    }

    public static UsuarioBusiness getUsuarioBusiness(){
        if (usuarioBusiness == null)
            usuarioBusiness = new UsuarioBusiness();

        return usuarioBusiness;
    }

    public static ConfiguracionBusiness getConfiguracionBusiness(){
        if (configuracionBusiness == null)
            configuracionBusiness = new ConfiguracionBusiness();

        return configuracionBusiness;
    }




    public static android.content.Context getStringContext(){
        return context;
    }

}
