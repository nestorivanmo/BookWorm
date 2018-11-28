package com.santillan.mosek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Spinner;



public class configuracionInicial extends AppCompatActivity {
    Spinner spinnerbook;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_inicial);


        spinnerbook=(Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_dias,android.R.layout.simple_spinner_item);
        spinnerbook.setAdapter(adapter);
    }
}
