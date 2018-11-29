package com.mozek.mozekapp.mainapp.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mozek.mozekapp.mainapp.config.InitialConfigActivity;

import com.mozek.mozekapp.R;
import com.mozek.mozekapp.verifiers.Errors;

public class LoginActivity extends AppCompatActivity {

    private Button signUpButton, loginButton; //lowerCamelCase -- atributos  UpperCamelCase -- clases
    private EditText emailEditText, passwordEditText;
    private FirebaseAuth fireAuth;
    public ProgressDialog verifiableUsually;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.emailET_Login);
        passwordEditText = findViewById(R.id.passwordET_Login);
        fireAuth=FirebaseAuth.getInstance();
        signUpButton= findViewById(R.id.changeToSignUpButton_Login);
        loginButton=findViewById(R.id.loginButton_Login);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSign= new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intentSign);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                login(email,password);
            }
        });
    }



    public  void login(String email,String password) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty((password))){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        verifiableUsually.setMessage(Errors.ERROR_01);
        verifiableUsually.show();

        fireAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent goToConfigIntent = new Intent(LoginActivity.this, InitialConfigActivity.class);
                    startActivity(goToConfigIntent);
                }else{
                    Toast.makeText(LoginActivity.this, "There was an error", Toast.LENGTH_LONG).show();
                }
                verifiableUsually.dismiss();
            }
        });

    }

}
