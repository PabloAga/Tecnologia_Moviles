package com.example.primeraclase.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.primeraclase.activity.Fragments.FragmentHome;
import com.google.android.material.navigation.NavigationView;
import com.example.primeraclase.Constants;
import com.example.primeraclase.Context;

import com.example.primeraclase.activity.Fragments.FragmentEditarUsuario;
import com.example.primeraclase.business.ConfiguracionBusiness;
import com.example.primeraclase.model.Configuracion;
import com.example.primeraclase.utils.Util;
import com.example.primeraclase.R;
import com.example.primeraclase.model.Usuario;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        FragmentEditarUsuario.IFragmentEditarUsuarioListener
        {


    private DrawerLayout drawer;
    private TextView nombreUsuarioMenu;
    private TextView emailUsuarioMenu;
    private CircleImageView avatar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setTitle("Blockbuster");

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nombreUsuarioMenu = headerView.findViewById(R.id.nombreUsuarioMenu);
        emailUsuarioMenu = headerView.findViewById(R.id.emailUsuarioMenu);
        avatar = headerView.findViewById(R.id.avatar);

        cargarUsuario();


        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                UIUtils.hideKeyboard(MenuActivity.this);
            }
        });
*/
        FragmentHome  fh = new FragmentHome();
        loadFragment(fh);


        //Intent i = new Intent(this, HomeActivity.class);
        //startActivity(i);

    //    toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment f = fragmentManager.findFragmentById(R.id.fragment_container);
            if(!(f instanceof FragmentHome)) {
                cargarHome();
                return;
            }
            if(!Context.getUsuarioBusiness().isMantenerSesion())
                Context.getUsuarioBusiness().setCurrentUser(null);
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        if(!Context.getUsuarioBusiness().isMantenerSesion())
            Context.getUsuarioBusiness().setCurrentUser(null);
        super.onStop();
    }

    private void cargarUsuario(){
        Usuario user = Context.getUsuarioBusiness().getCurrentUser();
        nombreUsuarioMenu.setText(user.getUsuario());
        emailUsuarioMenu.setText(user.getEmail());

        File img = new File(Context.getDataDir(),user.getUsuario()+"/"+ Constants.USER_AVATAR);
        Bitmap bmp = Util.getImage(img);
        avatar.setImageBitmap(bmp);
    }

    private void cerrarSesion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.cerrarSesionMensaje)
                .setPositiveButton(R.string.ACEPTAR, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.content.Context context = MenuActivity.this.getApplicationContext();
                        if(!Context.getUsuarioBusiness().setCurrentUser(null))
                            return;
                        Intent i = new Intent(MenuActivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton(R.string.CANCELAR, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    private void cargarHome(){
        FragmentHome  fhome = new FragmentHome();
        loadFragment(fhome);
    }

    private void cargarEditar(){
        FragmentEditarUsuario  fEdit = new FragmentEditarUsuario(this);
        loadFragment(fEdit);
    }



    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container,fragment);
        ft.commit();
    }

    private void loadFragmentWithAnim(Fragment fragment, FragmentTransaction fragmentTransaction){
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_logout:
                cerrarSesion();
                break;
            case R.id.nav_home:
                cargarHome();
                break;

            case R.id.nav_edit_profile:
                cargarEditar();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void cerrarFramgemntEditarUsuario() {
        cargarHome();
    }

    @Override
    public void guardarConfiguracionClick(Configuracion conf) {
        ConfiguracionBusiness cBO = Context.getConfiguracionBusiness();
        boolean valid = cBO.save(conf);
        cargarHome();
        String msg;
        if(valid)
            msg = getString(R.string.confGuardada);
        else
            msg = getString(R.string.confErroGuardado);

        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void actualizarUsuario() {
        cargarUsuario();
    }


}
