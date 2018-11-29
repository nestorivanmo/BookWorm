package com.mozek.mozekapp.mainapp.config;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;


import com.mozek.mozekapp.R;
import com.mozek.mozekapp.verifiers.ApiVerifier;

public class InitialConfigActivity extends AppCompatActivity {

    Button goToMainAppButton;
    ApiVerifier apiVerifier = new ApiVerifier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        //transitionToMainAppWindow();

    }

    public void transitionToMainAppWindow(){

        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);
        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentMainAppActivity();
            }
        });

    }

    public void presentMainAppActivity(){

    }




}
