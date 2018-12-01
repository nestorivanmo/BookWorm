package com.mozek.myapplicationfirebasetest.mainapp.config;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mozek.myapplicationfirebasetest.FirebaseManager.FirebaseManager;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.models.User;


public class InitialConfigActivity extends AppCompatActivity {

    public static final String TAG = "InitialConfigActivity";

    private FloatingActionButton goToMainAppButton;
    private TextView titleTV;
    private User user;
    private FirebaseManager fbManager;

    // TODO: 11/29/18 update Welcome, user message in XML file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);
        fbManager = new FirebaseManager();

        try {
            user = receiveUser();
            handleUser(user);

        }catch (UserMissingException e){

        }

    }

    private void handleUser(User user) {

        if (user.getFirstTime() == 1){

            user.setFirstTime(0);

            updateTitleTextView(user.getUsername(), true);

            try {
                fbManager.addUserToDb(user, TAG);
                transitionToMainAppWindow();

            }catch (RegisterToDBException e){

            }

        }else {
            updateTitleTextView("", false);
        }

    }

    public void updateTitleTextView(String input, boolean firstTime){
        titleTV = findViewById(R.id.initialConfigTitle_TV_InitialConfig);
        String currentText;

        if (firstTime) {
            currentText = getString(R.string.Bienvenido);
        }else{
            currentText = getString(R.string.agregaUnNuevoLibro);
        }

        currentText += ", " + input;
        titleTV.setText(currentText);
    }

    public User receiveUser() throws UserMissingException{

        Bundle data = getIntent().getExtras();
        User user;
        if (data == null){
            throw new UserMissingException();
        }else{
            user = data.getParcelable("user");
        }
        return user;
    }

    public void transitionToMainAppWindow() {

        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);

        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent goToMainAppWindowIntent = new Intent(InitialConfigActivity.this, MainActivity.class);
                //startActivity(goToMainAppWindowIntent);
            }
        });

    }

}
