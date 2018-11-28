package com.mozek.mozekapp.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mozek.mozekapp.config.InitialConfigActivity;

import com.mozek.mozekapp.R;

public class SignUpActivity extends AppCompatActivity {

    Button ButSignUp2, ButLogin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButSignUp2=(Button)findViewById(R.id.signUpButton_SignUp);
        ButSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSign2= new Intent(SignUpActivity.this,InitialConfigActivity.class);
                startActivity(intentSign2);
            }
        });
        ButLogin2=(Button)findViewById(R.id.changeToLoginButton_SignUp);
        ButLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLo2= new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intentLo2);
            }
        });
    }
}
