package com.example.primeraclase.persistance;

import com.example.primeraclase.Constants;
import com.example.primeraclase.Context;
import com.example.primeraclase.model.Usuario;
import com.example.primeraclase.utils.SharedPreferencesUtils;
import com.example.primeraclase.utils.Util;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    public boolean save(Usuario user) {
        String json = getJSON(user);
        File file = new File(Context.getDataDir(),
                user.getUsuario()+"/"+Constants.USER_DATA_FILE);

        return Util.writeFile(file,json);
    }

    public Usuario getUsuario(String username) {
        File userData = new File(Context.getDataDir(),
                username + "/" + Constants.USER_DATA_FILE);
        if(!userData.exists())
            return null;

        String json = Util.readFile(userData);

        return getFromJSON(json);
    }

    public List<Usuario> getListaUsuarios() {
        List<Usuario> out = new ArrayList<Usuario>();
        File file = new File(Context.getDataDir(), Constants.USER_LIST_FILE);
        String[] list = Util.readFile(file).split("\n");
        if(list[0].equals("")){
            return out;
        }

        for(int i = 0; i < list.length; i++){
            Usuario u = getUsuario(list[i]);
            out.add(u);
        }
        return out;
    }

    public boolean setListaUsuarios(List<Usuario> list){
        File file = new File(Context.getDataDir(), Constants.USER_LIST_FILE);
        String listTxt = "";
        for(int i = 0; i < list.size(); i++){
            listTxt += list.get(i).getUsuario() + "\n";
        }

        return Util.writeFile(file,listTxt);
    }

    public Usuario getCurrentUser(){

        /* Metodo viejo para obtener el usuario actual desde un archivo

        File loged = new File(Context.getDataDir(), Constants.CURRENT_USER_FILE);*/
        String username = SharedPreferencesUtils.getKeyValueString(Constants.CURRENT_USER_FILE);

        if(username != null)
            return getUsuario(username);

        return null;
    }

    public boolean setCurrentUser(String username){

        /*Metodo viejo para guardar el usuario actual en un archivo

        if(username == null){
            return Util.deleteFileOnPath(Context.getDataDir(),Constants.CURRENT_USER_FILE);
        }

        File loged = new File(Context.getDataDir(), Constants.CURRENT_USER_FILE);
        return Util.writeFile(loged,username);*/

        boolean valid = SharedPreferencesUtils.setKeyValueString(
                Constants.CURRENT_USER_FILE,
                username);
        return valid;
    }

    @Override
    public boolean deleteUser(String username) {
        return Util.deleteFileOnPath(Context.getDataDir(),username);
    }

    public Usuario getFromJSON(String jsonUser){
        Usuario user;
        try {
            JSONObject json = new JSONObject(jsonUser);
            user = new Usuario();
            user.setUsuario(json.getString("usuario"));
            user.setPassword(json.getString("password"));
            user.setEmail(json.getString("email"));
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getJSON(Usuario user){
        try{
            JSONObject json = new JSONObject();
            json.put("usuario",user.getUsuario());
            json.put("password",user.getPassword());
            json.put("email",user.getEmail());
            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }
}
