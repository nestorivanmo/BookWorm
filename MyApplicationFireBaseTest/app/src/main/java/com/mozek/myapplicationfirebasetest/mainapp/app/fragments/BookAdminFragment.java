package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.ViewAdapters.RecyclerViewAdapter;
import com.mozek.myapplicationfirebasetest.mainapp.auth.LoginActivity;
import com.mozek.myapplicationfirebasetest.mainapp.config.InitialConfigActivity;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.ArrayList;

public class BookAdminFragment extends Fragment implements Fragmentable{

    private static final String TAG = "BookAdminFragment";

    private View view;

    private Button logOutButton, newBookButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayList<String> mBookTitles = new ArrayList<>();
    private ArrayList<String> mBookAuthors = new ArrayList<>();
    private ArrayList<String> mBookProgress = new ArrayList<>();
    private ArrayList<String> mBookCurrentPage = new ArrayList<>();
    private ArrayList<String> mBookTargetPage = new ArrayList<>();


    public BookAdminFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_book_admin, container, false);

        getGraphicElements(view);
        logOutUserIfClicked(inflater);

        String currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        Query userQuery = db.collection("users").whereEqualTo("email", currentUserEmail);
        userQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                User user = new User();
                for (DocumentSnapshot doc : task.getResult()) {
                    user = doc.toObject(User.class);
                }
                runMainThreadNow(user);
            }
        });

        return view;
    }

    public void getGraphicElements(View view){
        logOutButton= view.findViewById(R.id.logOutButton_AdminBooks_Fragment);
        newBookButton = view.findViewById(R.id.addBookButton_AdminBooks_Fragment);
    }

    public void logOutUserIfClicked(LayoutInflater inflater){
        final LayoutInflater inflaterInside = inflater;
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intentLogOut= new Intent(inflaterInside.getContext(),LoginActivity.class);
                startActivity(intentLogOut);
            }
        });
    }

    public void getDataFromDB(){
        mBookAuthors.add("Swift Jonathan");
        mBookTitles.add("Gulliver's Adventures");
        mBookProgress.add("67%");
        mBookCurrentPage.add("340");
        mBookTargetPage.add("360");

        mBookAuthors.add("Nathaniel Hawthorne");
        mBookTitles.add("The Red and the Black");
        mBookProgress.add("10%");
        mBookCurrentPage.add("10");
        mBookTargetPage.add("30");

    }

    public void runMainThreadNow(final User user){
        newBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign2 = new Intent(getActivity(), InitialConfigActivity.class);
                intentSign2.putExtra("user", user);
                startActivity(intentSign2);
            }
        });
        getDataFromDB();
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.i(TAG, "initRecyclerView: init recyclerView");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_bookAdmin);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mBookTitles, mBookAuthors, mBookProgress, mBookCurrentPage, mBookTargetPage);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
