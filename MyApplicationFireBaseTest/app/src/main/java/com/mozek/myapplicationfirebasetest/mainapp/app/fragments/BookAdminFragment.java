package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.mainapp.auth.LoginActivity;
import com.mozek.myapplicationfirebasetest.mainapp.auth.SignUpActivity;
import com.mozek.myapplicationfirebasetest.mainapp.config.InitialConfigActivity;
import com.mozek.myapplicationfirebasetest.models.User;

public class BookAdminFragment extends Fragment implements Fragmentable{

    private static final String TAG = "BookAdminFragment";

    private Button logOutButton, newBookButton;
    private TextView titleTV;
    private User user;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public BookAdminFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_book_admin, container, false);

        getGraphicElements(view);
        logOutUserIfClicked(inflater);
        FirebaseUser fbUser;
        fbUser = mAuth.getCurrentUser();
        String currentUserEmail = fbUser.getEmail();

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
        titleTV = view.findViewById(R.id.titleTV_AdmiBooks_Fragment);
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

    public void runMainThreadNow(final User user){

        titleTV.setText(user.getUsername());

        newBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "USER CURRENT # BOOKS: " + String.valueOf(user.getBooks().size()));
                Intent intentSign2 = new Intent(getActivity(), InitialConfigActivity.class);
                intentSign2.putExtra("user", user);
                startActivity(intentSign2);
            }
        });

    }
}
