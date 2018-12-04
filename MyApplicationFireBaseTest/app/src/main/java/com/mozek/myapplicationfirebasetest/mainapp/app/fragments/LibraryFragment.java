package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.models.Book;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {
    private User user;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseUser fbUser;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private ListView lisbooks;

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            fbUser=mAuth.getCurrentUser();
             View view =  inflater.inflate(R.layout.fragment_library, container, false);
            lisbooks= view.findViewById(R.id.listViewBooks);

            String currentUserEmail = fbUser.getEmail();
            Query query =db.collection("users").whereEqualTo("email",currentUserEmail);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    ArrayList<Book> userBooks = new ArrayList<>();
                    ArrayList<String> booksForListView = new ArrayList<>();
                    for (DocumentSnapshot docment:task.getResult()){
                        user= docment.toObject(User.class);
                        userBooks = user.getBooks();
                        //ArrayAdapter adapter=new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,userBooks);
                        //lisbooks.setAdapter(adapter);
                    }
                    for(int i=0;i<userBooks.size();i++){
                        booksForListView.add(userBooks.get(i).getTitle() + ", " +userBooks.get(i).getAuthor());
                    }
                    ArrayAdapter<String> bookAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, booksForListView);
                   // bookAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                    lisbooks.setAdapter(bookAdapter);
                    Log.i("LibraryFragment","number of books _----------------------____: " + String.valueOf(userBooks.size()));
                }
            });
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

}
