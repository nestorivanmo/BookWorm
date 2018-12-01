package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.mainapp.auth.LoginActivity;


public class administratorBooksFragment extends Fragment {
    private Button logOutButton;

    public administratorBooksFragment() {

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_administrator_books, container, false);

        logOutButton= view.findViewById(R.id.logOutButton_AdminBooks_Fragment);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intentLogOut= new Intent(inflater.getContext(),LoginActivity.class);
                startActivity(intentLogOut);
            }
        });
        return view;

    }

}
