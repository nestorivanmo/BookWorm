package com.santillan.mosek;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button ButSignUp,ButLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

          ButSignUp=(Button)findViewById(R.id.SIGN1);
          ButSignUp.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intentSign= new Intent(LoginActivity.this,SignUpActivity.class);
                    startActivity(intentSign);
              }
          });
          ButLogin=(Button)findViewById(R.id.LOGIN1);
          ButLogin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Intent intentLo= new Intent(LoginActivity.this,configuracionInicial.class);
                  startActivity(intentLo);
              }
          });
    }
}
