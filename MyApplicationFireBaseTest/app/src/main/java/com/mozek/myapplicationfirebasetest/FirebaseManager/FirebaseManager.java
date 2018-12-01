package com.mozek.myapplicationfirebasetest.FirebaseManager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.models.User;

public class FirebaseManager {

    private FirebaseFirestore db;
    private boolean failure;

    public FirebaseManager(){
        db = FirebaseFirestore.getInstance();
    }

    public void addUserToDb(User currentUser, String tag) throws RegisterToDBException{

        final String TAG = tag;

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
                        setFailure(true);
                    }
                });

        if (isFailure()){
            throw new RegisterToDBException();
        }

    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }
}
