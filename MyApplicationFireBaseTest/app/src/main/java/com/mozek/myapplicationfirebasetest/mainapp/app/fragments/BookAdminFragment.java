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
import com.mozek.myapplicationfirebasetest.models.Book;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.ArrayList;

public class BookAdminFragment extends Fragment implements Fragmentable{

    private static final String TAG = "BookAdminFragment";

    private View view;

    private Button logOutButton, newBookButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    public void getDataFromDB(User user){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Book> userBooks = user.getBooks();

        ArrayList<String> mBookAuthors = new ArrayList<>();
        ArrayList<String> mBookTitles = new ArrayList<>();
        ArrayList<String> mBookProgress = new ArrayList<>();
        ArrayList<String> mBookCurrentPage = new ArrayList<>();
        ArrayList<String> mBookTargetPage = new ArrayList<>();

        Log.i(TAG, "getDataFromDB: user books: " + String.valueOf(user.getBooks().size()));

        for (Book b : userBooks){
            mBookTitles.add(b.getTitle());
            mBookAuthors.add(b.getAuthor());
            mBookProgress.add(String.valueOf(b.getProgress()) + " %");
            mBookCurrentPage.add(String.valueOf(b.getCurrentPage()));
            mBookTargetPage.add(String.valueOf(b.getTargetPage()));
        }

        initRecyclerView(mBookAuthors, mBookTitles, mBookProgress, mBookCurrentPage, mBookTargetPage);

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
        getDataFromDB(user);
    }

    private void initRecyclerView(ArrayList<String> mBookAuthors, ArrayList<String> mBookTitles, ArrayList<String> mBookProgress, ArrayList<String> mBookCurrentPage, ArrayList<String> mBookTargetPage){
        Log.i(TAG, "initRecyclerView: init recyclerView");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_bookAdmin);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mBookTitles, mBookAuthors, mBookProgress, mBookCurrentPage, mBookTargetPage);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
