package com.example.primeraclase.activity.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.primeraclase.model.Configuracion;
import com.example.primeraclase.Constants;
import com.example.primeraclase.Context;
import com.example.primeraclase.R;
import com.example.primeraclase.business.UsuarioBusiness;
import com.example.primeraclase.exception.ExepcionUsuario;
import com.example.primeraclase.model.Usuario;
import com.example.primeraclase.utils.Util;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentEditarUsuario extends Fragment {


    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2 ;
    private CircleImageView imgPerfil;
    private EditText nombre;
    private EditText correo;
    private EditText password;
    private Bitmap bmp = null;
    private IFragmentEditarUsuarioListener onclick;

    public interface IFragmentEditarUsuarioListener {
        public void cerrarFramgemntEditarUsuario();

        void guardarConfiguracionClick(Configuracion conf);

        public void actualizarUsuario();
    }

    public FragmentEditarUsuario(IFragmentEditarUsuarioListener onclick){
        this.onclick = onclick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contenedor = inflater.inflate(R.layout.fragment_editar_usuario, container, false);
        imgPerfil = contenedor.findViewById(R.id.profile_image);



        Button btnGuardar = contenedor.findViewById(R.id.btnModificarUsuario);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        nombre = contenedor.findViewById(R.id.nombrePerfil);
        correo = contenedor.findViewById(R.id.correoPerfil);
        password = contenedor.findViewById(R.id.contrasenaPerfil);

        Usuario u = Context.getUsuarioBusiness().getCurrentUser();
        nombre.setText(u.getUsuario());
        correo.setText(u.getEmail());
        password.setText(u.getPassword());

        File fl = new File(Context.getDataDir(),
                u.getUsuario()+"/"+ Constants.USER_AVATAR);
        if(fl.exists()){
            Bitmap bmp = Util.getImage(fl);
            imgPerfil.setImageBitmap(bmp);
        }

        return contenedor;
    }

    private void guardar(){
        UsuarioBusiness usuarioBO = Context.getUsuarioBusiness();
        Usuario currentUser = usuarioBO.getCurrentUser();

        Usuario user = new Usuario();
        user.setUsuario(nombre.getText().toString());
        user.setPassword(password.getText().toString());
        user.setEmail(correo.getText().toString());
        try {
            usuarioBO.checkUser(getActivity().getApplicationContext(),user);
        }catch (ExepcionUsuario e){
            Toast.makeText(
                    getActivity().getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        if(!user.getUsuario().equals(currentUser.getUsuario())) {

            if(usuarioBO.getUsuario(user.getUsuario()) != null){
                Toast.makeText(getActivity().getApplicationContext(),"El usuario ya existe", Toast.LENGTH_SHORT).show();
                return;
            }

            usuarioBO.changeUserNameList(currentUser.getUsuario(),user.getUsuario());
            Util.renameFile(new File(Context.getDataDir(), currentUser.getUsuario()), user.getUsuario());

        }

        usuarioBO.setCurrentUser(user);

        usuarioBO.update(user);
        if(bmp != null) {
            File fl = new File(Context.getDataDir(),
                    currentUser.getUsuario() + "/" + Constants.USER_AVATAR);

            Util.saveImage(fl, bmp);
        }

        Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Guardado", Toast.LENGTH_SHORT);
        toast.show();
        onclick.actualizarUsuario();
        onclick.cerrarFramgemntEditarUsuario();

    }


    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                bmp = (Bitmap) bundle.get("data");

                imgPerfil.setImageBitmap(bmp);

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();

                bmp = Util.getImageFromGallery(
                        getActivity().getContentResolver(),selectedImageUri);

                imgPerfil.setImageBitmap(bmp);
            }

        }
    }
}
