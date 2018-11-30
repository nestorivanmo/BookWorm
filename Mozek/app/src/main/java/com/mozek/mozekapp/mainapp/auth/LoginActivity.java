package com.mozek.mozekapp.mainapp.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.mozek.mozekapp.exceptions.AuthException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mozek.mozekapp.fairules.Errors_Login;
import com.mozek.mozekapp.mainapp.app.MainActivity;
import com.mozek.mozekapp.mainapp.config.InitialConfigActivity;

import com.mozek.mozekapp.R;
import com.mozek.mozekapp.verifiers.AuthVerifier;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog proUser;
    private Button signUpButton, loginButton; //lowerCamelCase -- atributos  UpperCamelCase -- clases
    private EditText emailEditText, passwordEditText;
    private FirebaseAuth fireAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;
    private AuthVerifier authVerifier = new AuthVerifier();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findGraphicElements();

       /* authStateListener= new FirebaseAuth.AuthStateListener() {
=======

        authStateListener= new FirebaseAuth.AuthStateListener() {
>>>>>>> 929b041f217ad38c3c7b056d6b8703351c1b4f2d
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =firebaseAuth.getCurrentUser();

                if(user!=null) {
                    Intent goToConfigIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(goToConfigIntent);

                }




            }
        };*/
        activateButtons();
    }

    private void findGraphicElements(){
        emailEditText = findViewById(R.id.emailET_Login);
        passwordEditText = findViewById(R.id.passwordET_Login);
        fireAuth=FirebaseAuth.getInstance();
        proUser=new ProgressDialog(this);
        signUpButton= findViewById(R.id.changeToSignUpButton_Login);
        loginButton=findViewById(R.id.loginButton_Login);

    }

    private void activateButtons(){
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

        try {
           if (authVerifier.verifyInfoLogin(this, email, password) ) {
                proUser.setMessage(Errors_Login.ERROR_03_validatingU);
                proUser.show();
                fireAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            authVerifier.displaySuccess(LoginActivity.this, Errors_Login.ERROR_05_welcome);

                                Intent goToConfigIntent = new Intent(LoginActivity.this, MainActivity.class);

                                startActivity(goToConfigIntent);

                        } else {

                                authVerifier.displayError(LoginActivity.this,Errors_Login.ERROR_04_error );

                        }
                        proUser.dismiss();
                    }



                });
            }

        }catch (AuthException e){
            //authVerifier.displayError(this,Errors_Sign_UP.ERROR_06_recordFailure );
        }
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
       if(authStateListener!=null){
         FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }*/
}
