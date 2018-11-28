package com.santillan.mosek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
    Button ButSignUp2,ButLogin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButSignUp2=(Button)findViewById(R.id.SIGN2);
        ButSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSign2= new Intent(SignUpActivity.this,configuracionInicial.class);
                startActivity(intentSign2);
            }
        });

        ButLogin2=(Button)findViewById(R.id.LOGIN2);
        ButLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLo2= new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intentLo2);
            }
        });
    }
}
