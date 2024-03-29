package com.mozek.myapplicationfirebasetest.mainapp.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.mozek.myapplicationfirebasetest.exceptions.AuthException;
import com.mozek.myapplicationfirebasetest.fairules.Errors_Sign_UP;
import com.mozek.myapplicationfirebasetest.mainapp.config.InitialConfigActivity;

import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.models.User;
import com.mozek.myapplicationfirebasetest.verifiers.AuthVerifier;

public class SignUpActivity extends AppCompatActivity {

    private Button ButSignUp2, ButLogin2;
    private EditText TextEmail, TextPassword, TextUsername;
    private ProgressDialog progressRegister;
    private FirebaseAuth firebaseAuth;
    private AuthVerifier authVerifier = new AuthVerifier();
    public static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findGraphicElements();
        activateButtons();
    }

    private void findGraphicElements(){
        ButSignUp2=findViewById(R.id.signUpButton_SignUp);
        ButLogin2=findViewById(R.id.changeToLoginButton_SignUp);
        firebaseAuth= FirebaseAuth.getInstance();
        TextEmail= findViewById(R.id.emailET_SignUp);
        TextPassword=  findViewById(R.id.passwordET_SignUp) ;
        TextUsername = findViewById(R.id.usernameET_SignUp);
        progressRegister=new ProgressDialog(this);
    }

    private void activateButtons(){
        ButSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= TextEmail.getText().toString().trim();
                String password=TextPassword.getText().toString().trim();
                String username = TextUsername.getText().toString().trim();
                registerUser(username,email,password);

            }
        });
        ButLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLo2= new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intentLo2);
            }
        });
    }


    private void registerUser(final String username, final String email, final String password){

        try {
            if (authVerifier.verifyInfo(this, username, email, password) ) {

                progressRegister.setMessage(Errors_Sign_UP.ERROR_03_performingR);
                progressRegister.show();

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            authVerifier.displaySuccess(SignUpActivity.this, Errors_Sign_UP.ERROR_04_RecordS_);

                            Intent intentSign2 = new Intent(SignUpActivity.this, InitialConfigActivity.class);
                            intentSign2.putExtra("user", new User(username, email));
                            startActivity(intentSign2);

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                authVerifier.displayError(SignUpActivity.this, Errors_Sign_UP.ERROR_05__UserAE);

                            } else {
                                authVerifier.displayError(SignUpActivity.this, Errors_Sign_UP.ERROR_06_recordFailure);
                            }

                        }
                        progressRegister.dismiss();
                    }
                });
            }

        }catch (AuthException e){
            //authVerifier.displayError(this,Errors_Sign_UP.ERROR_06_recordFailure );
        }
    }
}
