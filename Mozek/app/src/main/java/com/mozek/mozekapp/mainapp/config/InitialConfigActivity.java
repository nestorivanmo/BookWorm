package com.mozek.mozekapp.mainapp.config;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mozek.mozekapp.R;
import com.mozek.mozekapp.mainapp.app.MainActivity;


public class InitialConfigActivity extends AppCompatActivity {

    FloatingActionButton goToMainAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        transitionToMainAppWindow();

    }

    public void transitionToMainAppWindow(){
        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);

        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainAppWindowIntent = new Intent(InitialConfigActivity.this, MainActivity.class);
                startActivity(goToMainAppWindowIntent);
            }
        });

    }

}
