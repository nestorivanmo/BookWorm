package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.ViewAdapters.LibraryRecyclerViewAdapter;
import com.mozek.myapplicationfirebasetest.models.Book;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    private final static String TAG = "Library Fragment";
    
    private User user;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseUser fbUser;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private View view;

    public LibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view =  inflater.inflate(R.layout.fragment_library, container, false);

            fbUser = mAuth.getCurrentUser();
            String currentUserEmail = fbUser.getEmail();
            
            Query query =db.collection("users").whereEqualTo("email",currentUserEmail);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        ArrayList<Book> userBooks = new ArrayList<>();
                        for (DocumentSnapshot docment:task.getResult()){
                            user = docment.toObject(User.class);
                        }
                        userBooks = user.getBooks();
                        updateRecyclerView(userBooks);
                    }else{
                        
                    }
                   
                }
            });

            return view;
    }

    public void updateRecyclerView(final ArrayList<Book> userBooks){
        ArrayList<String> mBookAuthors = new ArrayList<>();
        ArrayList<String> mBookTitles = new ArrayList<>();
        ArrayList<String> mBookProgress = new ArrayList<>();
        for (Book b : userBooks){
            mBookAuthors.add(b.getAuthor());
            mBookTitles.add(b.getTitle());
            mBookProgress.add(String.valueOf(b.getProgress()) + " %");
        }
        initRecyclerView(mBookAuthors, mBookTitles, mBookProgress);
    }

    private void initRecyclerView(ArrayList<String> mBookAuthors, ArrayList<String> mBookTitles, ArrayList<String> mBookProgress){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_library);
        recyclerView.setLayoutManager(layoutManager);
        LibraryRecyclerViewAdapter libraryRecyclerViewAdapter = new LibraryRecyclerViewAdapter(getActivity(), mBookTitles, mBookAuthors, mBookProgress);
        recyclerView.setAdapter(libraryRecyclerViewAdapter);
    }

}
