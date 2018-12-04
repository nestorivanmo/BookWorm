package com.mozek.myapplicationfirebasetest.managers;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.ArrayList;

public class FirebaseManager {

    private FirebaseFirestore db;
    private ArrayList<DocumentSnapshot> documents = new ArrayList<>();
    private ArrayList<User> usersFromDb = new ArrayList<>();
    private boolean failure;

    public FirebaseManager(){
        db = FirebaseFirestore.getInstance();
    }

    public void addUserToDb(User currentUser, String tag) throws RegisterToDBException{
        final String TAG = tag;
        db.collection("users")
                .document(currentUser.getEmail())
                .set(currentUser)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                        setFailure(true);
                    }
                });

        if (isFailure()){
            throw new RegisterToDBException();
        }
    }

    private boolean isFailure() {
        return failure;
    }
    private void setFailure(boolean failure) {
        this.failure = failure;
    }

}
