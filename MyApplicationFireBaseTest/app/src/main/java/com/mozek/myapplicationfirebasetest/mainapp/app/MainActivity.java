package com.mozek.myapplicationfirebasetest.mainapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.mainapp.auth.LoginActivity;
import com.mozek.myapplicationfirebasetest.mainapp.auth.SignUpActivity;

public class MainActivity extends AppCompatActivity {
    private Button logOutButoon;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_admin:

                    return true;
                case R.id.navigation_reader:

                    return true;
                case R.id.navigation_library:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logOutButoon= findViewById(R.id.butoonLogi);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        logOutButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intentLogOut= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentLogOut);
            }
        });
    }

}
