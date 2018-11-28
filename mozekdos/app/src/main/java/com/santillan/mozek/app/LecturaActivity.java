package com.santillan.mozek.app;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.santillan.mosek.R;
import com.santillan.mozek.app.adminLibros.AdminLibrosFragment;
import com.santillan.mozek.app.biblioteca.BibliotecaFragment;
import com.santillan.mozek.app.lectorDigital.LectorDigitalFragment;


public class LecturaActivity extends AppCompatActivity {
    BibliotecaFragment fragmentoBiblioteca;
    LectorDigitalFragment fragmentoLector;
    AdminLibrosFragment fragmentoAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura);
        fragmentoAdmin=new AdminLibrosFragment();
        fragmentoLector=new LectorDigitalFragment();
        fragmentoBiblioteca=new BibliotecaFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.ContenedorFragmentos,fragmentoAdmin).commit();

    }

    public void onClick(View view) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
              switch (view.getId()){
                  case R.id.botAdmin:
                        transaction.replace(R.id.ContenedorFragmentos,fragmentoAdmin);
                      break;
                  case R.id.botLector:
                      transaction.replace(R.id.ContenedorFragmentos,fragmentoLector);
                      break;
                  case R.id.botBiblioteca:
                      transaction.replace(R.id.ContenedorFragmentos,fragmentoBiblioteca);
                      break;
              }
        transaction.commit();
    }
}
