package com.mozek.mozekapp.auth;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.mozek.mozekapp.config.InitialConfigActivity;

import com.mozek.mozekapp.R;

public class SignUpActivity extends AppCompatActivity {

    Button ButSignUp2, ButLogin2;
    EditText TextEmail;
    EditText TextPassword;
    ProgressDialog progressRegister;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //INSTANCIAS
        ButSignUp2=(Button)findViewById(R.id.signUpButton_SignUp);
        ButLogin2=(Button)findViewById(R.id.changeToLoginButton_SignUp);

        //INCILIZANDO FIREBASE
            firebaseAuth= FirebaseAuth.getInstance();


            //Refrenciamos los views
        TextEmail=(EditText) findViewById(R.id.emailET_SignUp);
        TextPassword= (EditText) findViewById(R.id.passwordET_SignUp) ;
        progressRegister=new ProgressDialog(this);

        //LOGICA DE BOTON
        ButSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();

            }
        });
        //LOGICA DE BOTON
        ButLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLo2= new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intentLo2);
            }
        });
    }


    private void registrarUsuario(){
        String email= TextEmail.getText().toString().trim();
        String password=TextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty((password))){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        progressRegister.setMessage("Realizando Registro...");
        progressRegister.show();

        //Creando un nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                            Intent intentSign2= new Intent(SignUpActivity.this,InitialConfigActivity.class);
                            startActivity(intentSign2);
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SignUpActivity.this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUpActivity.this, "Fracaso de registro", Toast.LENGTH_SHORT).show();
                            }

                        }
                        progressRegister.dismiss();
                    }
                });
    }
}
