package com.mozek.myapplicationfirebasetest.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.exceptions.ReadFromDBException;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.models.User;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FirebaseManager {

    private FirebaseFirestore db;
    private ArrayList<DocumentSnapshot> documents = new ArrayList<>();
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

    public ArrayList<DocumentSnapshot> readFromDataBase(Context context, String collectionName) throws ReadFromDBException{

        final Context ctx = context;
        ArrayList<DocumentSnapshot> docs = new ArrayList<>();

        db.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                addDocument(document);
                            }
                        } else {
                            setFailure(true);
                        }
                    }
                });

        if (isFailure()) {
            throw  new ReadFromDBException();
        }


        Toast.makeText(ctx, "Docs Fb -> "  + Integer.toString(docs.size()), Toast.LENGTH_LONG).show();
        return docs;

    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    public void addDocument(DocumentSnapshot doc){
        this.documents.add(doc);
    }
}
