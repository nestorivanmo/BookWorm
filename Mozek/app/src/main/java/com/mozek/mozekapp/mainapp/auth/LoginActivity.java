package com.mozek.mozekapp.mainapp.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private Button signUpButton, loginButton; //lowerCamelCase -- atributos  UpperCamelCase -- clases
    private EditText emailEditText, passwordEditText;
    private FirebaseAuth fireAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.emailET_Login);
        passwordEditText = findViewById(R.id.passwordET_Login);

        fireAuth=FirebaseAuth.getInstance();
        /*authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(LoginActivity.this,"El usuario ya inicio secion", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(LoginActivity.this,"El usuario salio de la  secion", Toast.LENGTH_SHORT).show();
                }



            }
        };

        */
        signUpButton= findViewById(R.id.changeToSignUpButton_Login);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSign= new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intentSign);
            }
        });

        loginButton=findViewById(R.id.loginButton_Login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }



    public  void login() {
        String correo = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        fireAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent goToConfigIntent = new Intent(LoginActivity.this, InitialConfigActivity.class);
                    startActivity(goToConfigIntent);
                }else{
                    Toast.makeText(LoginActivity.this, "Error de inicio de secion", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
