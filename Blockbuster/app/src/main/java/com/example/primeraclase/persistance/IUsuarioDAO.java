package com.example.primeraclase.persistance;

import com.example.primeraclase.model.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    public Usuario getUsuario(String username);
    public boolean save(Usuario u);
    public List<Usuario> getListaUsuarios();
    public boolean setListaUsuarios(List<Usuario> list);
    public Usuario getCurrentUser();
    public boolean setCurrentUser(String username);
    public boolean deleteUser(String username);
}
