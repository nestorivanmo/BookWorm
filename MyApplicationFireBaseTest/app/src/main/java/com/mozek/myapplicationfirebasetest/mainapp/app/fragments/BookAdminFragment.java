package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.mainapp.auth.LoginActivity;
import com.mozek.myapplicationfirebasetest.models.User;

public class BookAdminFragment extends Fragment implements Fragmentable{

    private static final String TAG = "BookAdminFragment";
    private Button logOutButton;
    private User user;
    private MainActivity mainActivity = new MainActivity();

    public BookAdminFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_book_admin, container, false);

        getGraphicElements(view);
        logOutUserIfClicked(inflater);

        this.user = mainActivity.getUser();
        Log.i(TAG, "user from Main activity -> " + user.getEmail());


        return view;
    }

    public void getGraphicElements(View view){
        logOutButton= view.findViewById(R.id.logOutButton_AdminBooks_Fragment);
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

}
