package com.mozek.mozekapp.mainapp.config;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);


    }

    public void transitionToMainAppWindow(){
        boolean deviceReadyToAnimate = apiVerifier.verifySystemApi(Build.VERSION_CODES.LOLLIPOP);
        if (deviceReadyToAnimate){
            transitionWithAnimation();
        }else {
            transitionWithoutAnimation();
        }
    }

    public void transitionWithAnimation(){

    }

    public void transitionWithoutAnimation(){

    }

}
