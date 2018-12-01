package com.mozek.myapplicationfirebasetest.managers;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.mozek.myapplicationfirebasetest.models.Book;

import java.util.ArrayList;
import java.util.List;

public class DataManager implements Management {

    public DataManager() {

    }

    public ArrayList convertToReadableData(String TAG, List<DocumentSnapshot> documentsFromDB){
        ArrayList<Book> books= new ArrayList<>();

        Log.i(TAG,  "DOCUMENTS LENGTH -> " + documentsFromDB.size());

        for (DocumentSnapshot document : documentsFromDB) {
            Log.i(TAG,  document.getId() +" -> " + document.getData());
        }

        return  books;
    }


}
