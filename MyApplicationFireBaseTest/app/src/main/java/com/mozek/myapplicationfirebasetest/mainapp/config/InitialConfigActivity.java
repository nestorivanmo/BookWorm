package com.mozek.myapplicationfirebasetest.mainapp.config;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.HashMap;
import java.util.Map;


public class InitialConfigActivity extends AppCompatActivity {

    public static final String TAG = "InitialConfigActivity";

    FloatingActionButton goToMainAppButton;
    User user;

    private FirebaseFirestore db;
    // TODO: 11/29/18 update Welcome, user message in XML file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        transitionToMainAppWindow();
    }


    public User receivedUser(){
        Bundle data = getIntent().getExtras();
        User user = (User) data.getParcelable("user");
        return user;
    }

    public void transitionToMainAppWindow(){
        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);

        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(InitialConfigActivity.this, "changing now...", Toast.LENGTH_LONG).show();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                User currentUser = receivedUser();

                db.collection("cientificos")
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
