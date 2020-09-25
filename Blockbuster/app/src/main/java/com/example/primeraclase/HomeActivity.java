package com.example.primeraclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.primeraclase.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvSubject;
    private SubjectAdapter subjectAdapter;
    private ArrayList<Subject> subjects;

    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation = findViewById ( R. id . bottom_navigation ) ;

        openFragment ( HomeFragment. newInstance ( "" , "" )) ;
        initComponents();

        subjects = prepareData();

        subjectAdapter = new SubjectAdapter(subjects, HomeActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(HomeActivity.this);
        rvSubject.setLayoutManager(manager);
        rvSubject.setAdapter(subjectAdapter);
    }

    private void initComponents() {
        rvSubject = findViewById(R.id.rvSubject);
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_menu:
                            Intent pantallaHome = new Intent(HomeActivity.this, ConfigActivity.class);
                            startActivity(pantallaHome);
                            return true;
                        case R.id.navigation_lupa:
                         //   openFragment(BuscarFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_notifications:
                          //  openFragment(NotificationFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
    private ArrayList<Subject> prepareData() {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        Chapter chapterAvenger = new Chapter();
        chapterAvenger.id = 1;
        chapterAvenger.chapterName = "Avenger";
        chapterAvenger.image = R.drawable.p_avenger;

       Chapter chapterCobra = new Chapter();
        chapterCobra.id = 2;
        chapterCobra.chapterName = "Cobra Kai";
        chapterCobra.image = R.drawable.p_cobrakai;

        Chapter chapterDbz = new Chapter();
        chapterDbz.id = 3;
        chapterDbz.chapterName = "Dragon Ball Z";
        chapterDbz.image =R.drawable.p_dbz;

        Chapter chapterBadboys = new Chapter();
        chapterBadboys.id = 4;
        chapterBadboys.chapterName = "Bad Boys";
        chapterBadboys.image = R.drawable.p_badboys;

        Chapter chapterGot = new Chapter();
        chapterGot.id = 5;
        chapterGot.chapterName = "Game of Thrones";
        chapterGot.image = R.drawable.p_got;

        Chapter chapterMulan = new Chapter();
        chapterMulan.id = 6;
        chapterMulan.chapterName = "Mulan";
        chapterMulan.image =R.drawable.p_mulan;

        Chapter chapterDark = new Chapter();
        chapterDark.id = 7;
        chapterDark.chapterName = "Dark";
        chapterDark.image = R.drawable.p_dark;

        Chapter chapterUmbrella = new Chapter();
        chapterUmbrella.id = 8;
        chapterUmbrella.chapterName = "The Umbrella Academy";
        chapterUmbrella.image = R.drawable.p_umbrella;

        Chapter chapterBoys = new Chapter();
        chapterBoys.id = 9;
        chapterBoys.chapterName = "The Boys";
        chapterBoys.image = R.drawable.p_theboys;

        Chapter chapterGotham = new Chapter();
        chapterGotham.id = 10;
        chapterGotham.chapterName = "Gotham";
        chapterGotham.image = R.drawable.p_gotham;

        Subject accion = new Subject();
        accion.id = 1;
        accion.subjectName = "Accion";
        accion.chapters = new ArrayList<Chapter>();

        accion.chapters.add(chapterAvenger);
        accion.chapters.add(chapterCobra);
        accion.chapters.add(chapterDbz);
        accion.chapters.add(chapterBadboys);
        accion.chapters.add(chapterGot);

        Subject comedia = new Subject();
        comedia.id = 2;
        comedia.subjectName = "Comedia";
        comedia.chapters = new ArrayList<Chapter>();

        comedia.chapters.add(chapterMulan);
        comedia.chapters.add(chapterBadboys);
        comedia.chapters.add(chapterUmbrella);
        comedia.chapters.add(chapterDbz);
        comedia.chapters.add(chapterBoys);

        Subject series = new Subject();
        series.id = 3;
        series.subjectName = "Series";
        series.chapters = new ArrayList<Chapter>();

        series.chapters.add(chapterGotham);
        series.chapters.add(chapterBoys);
        series.chapters.add(chapterCobra);
        series.chapters.add(chapterDark);
        series.chapters.add(chapterUmbrella);

        Subject miLista = new Subject();
        miLista.id = 4;
        miLista.subjectName = "Mi Lista";
        miLista.chapters = new ArrayList<Chapter>();

        miLista.chapters.add(chapterAvenger);
        miLista.chapters.add(chapterGotham);
        miLista.chapters.add(chapterGot);
        miLista.chapters.add(chapterBadboys);
        miLista.chapters.add(chapterDark);

        subjects.add(accion);
        subjects.add(comedia);
        subjects.add(miLista);
        subjects.add(series);

        return subjects;
    }
}
