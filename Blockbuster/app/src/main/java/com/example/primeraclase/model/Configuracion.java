package com.example.primeraclase.model;


public class Configuracion {

    private boolean notificaciones;
    private String hora;

    public Configuracion(){

        notificaciones = false;
    }

    public boolean isNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(boolean notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
