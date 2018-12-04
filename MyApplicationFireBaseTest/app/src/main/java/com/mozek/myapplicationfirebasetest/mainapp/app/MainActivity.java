package com.mozek.myapplicationfirebasetest.mainapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

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
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.BookAdminFragment;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.LibraryFragment;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.DigitalReaderFragment;
import com.mozek.myapplicationfirebasetest.managers.FirebaseManager;
import com.mozek.myapplicationfirebasetest.managers.ObjectSenderManager;
import com.mozek.myapplicationfirebasetest.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String SENDER_TITLE = "userFromMainActivity";

    private BottomNavigationView navigation;
    private FragmentManager manager = getSupportFragmentManager();
    private BookAdminFragment bookAdminFragment;
    private LibraryFragment libraryFragment;
    private DigitalReaderFragment digitalReaderFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragments();

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_admin:
                        manager.beginTransaction().replace(R.id.main_frame, bookAdminFragment).addToBackStack(null).commit();
                        return true;
                    case R.id.navigation_reader:
                        manager.beginTransaction().replace(R.id.main_frame, digitalReaderFragment).addToBackStack(null).commit();
                        return true;
                    case R.id.navigation_library:
                        manager.beginTransaction().replace(R.id.main_frame, libraryFragment).addToBackStack(null).commit();

                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void getFragments(){
        bookAdminFragment = new BookAdminFragment();
        libraryFragment = new LibraryFragment();
        digitalReaderFragment = new DigitalReaderFragment();
        manager.beginTransaction().replace(R.id.main_frame, bookAdminFragment).addToBackStack(null).commit();
        navigation = findViewById(R.id.navigation);
    }

}
