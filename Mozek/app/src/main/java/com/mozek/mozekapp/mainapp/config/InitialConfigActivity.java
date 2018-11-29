package com.mozek.mozekapp.mainapp.config;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mozek.mozekapp.R;


public class InitialConfigActivity extends AppCompatActivity {

    FloatingActionButton goToMainAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        //transitionToMainAppWindow();

    }

    public void transitionToMainAppWindow(){
        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);

    }

}
