package com.mozek.mozekapp.mainapp.config;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mozek.mozekapp.R;
import com.mozek.mozekapp.mainapp.app.MainActivity;
import com.mozek.mozekapp.models.User;


public class InitialConfigActivity extends AppCompatActivity {

    public static final String TAG = "InitialConfigActivity";

    FloatingActionButton goToMainAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        Bundle data = getIntent().getExtras();
        User user = (User) data.getParcelable("user");
        Log.d(TAG, "onCreate: " + user.toString());

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
