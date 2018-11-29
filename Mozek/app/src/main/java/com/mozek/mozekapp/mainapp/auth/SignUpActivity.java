package com.mozek.mozekapp.mainapp.auth;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.mozek.mozekapp.fairules.Errors_Sign_UP;
import com.mozek.mozekapp.mainapp.config.InitialConfigActivity;

import com.mozek.mozekapp.R;

public class SignUpActivity extends AppCompatActivity {

    private Button ButSignUp2, ButLogin2;
    private EditText TextEmail;
    private EditText TextPassword;
    private ProgressDialog progressRegister;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButSignUp2=findViewById(R.id.signUpButton_SignUp);
        ButLogin2=findViewById(R.id.changeToLoginButton_SignUp);
        firebaseAuth= FirebaseAuth.getInstance();
        TextEmail= findViewById(R.id.emailET_SignUp);
        TextPassword=  findViewById(R.id.passwordET_SignUp) ;
        progressRegister=new ProgressDialog(this);

        ButSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= TextEmail.getText().toString().trim();
                String password=TextPassword.getText().toString().trim();
                registrarUsuario(email,password);

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



    private void registrarUsuario(String email,String password){
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,Errors_Sign_UP.ERROR_01_missing_mail,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty((password))){
            Toast.makeText(this,Errors_Sign_UP.ERROR_02_missing_password,Toast.LENGTH_LONG).show();
            return;
        }

        progressRegister.setMessage(Errors_Sign_UP.ERROR_03_performingR);
        progressRegister.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,Errors_Sign_UP.ERROR_04_RecordS_,Toast.LENGTH_LONG).show();
                            Intent intentSign2= new Intent(SignUpActivity.this,InitialConfigActivity.class);//InitialConfigActivity
                            startActivity(intentSign2);
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SignUpActivity.this, Errors_Sign_UP.ERROR_05__UserAE, Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUpActivity.this, Errors_Sign_UP.ERROR_06_recordFailure, Toast.LENGTH_SHORT).show();
                            }

                        }
                        progressRegister.dismiss();
                    }
                });
    }
}
