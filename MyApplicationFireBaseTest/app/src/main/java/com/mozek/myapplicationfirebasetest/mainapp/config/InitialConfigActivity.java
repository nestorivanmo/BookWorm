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

    private FirebaseFirestore db;
    // TODO: 11/29/18 update Welcome, user message in XML file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        try {
            user = receiveUser();
            handleUser(user);

        }catch (UserMissingException e){

        }

    }

    private void handleUser(User user) {

        if (user.getFirstTime() == 1){

            user.setFirstTime(0);

            updatetitleTextView(user.getUsername());
            

            transitionToMainAppWindow();

        }else {



        }

    }

    public void updatetitleTextView(String userName){
        titleTV = findViewById(R.id.initialConfigTitle_TV_InitialConfig);
        String currentText = getString(R.string.Bienvenido);
        currentText += " " + userName;
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

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                User currentUser = user;



                db.collection("users")
                        .add(currentUser)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

                //Intent goToMainAppWindowIntent = new Intent(InitialConfigActivity.this, MainActivity.class);
                //startActivity(goToMainAppWindowIntent);
            }
        });

    }

}
