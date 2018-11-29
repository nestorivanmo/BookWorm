package com.mozek.mozekapp.mainapp.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.mozek.mozekapp.mainapp.config.InitialConfigActivity;
import com.mozek.mozekapp.R;

public class LoginActivity extends AppCompatActivity {

    Button ButSignUp, ButLogin;
    EditText textEmail,textPassword;
    private FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButSignUp=(Button)findViewById(R.id.changeToSignUpButton_Login);
        ButSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSign= new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intentSign);
            }
        });

        ButLogin=(Button)findViewById(R.id.loginButton_Login);
        ButLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLo= new Intent(LoginActivity.this,InitialConfigActivity.class);
                startActivity(intentLo);
            }
        });
    }
}
