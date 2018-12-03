package com.mozek.myapplicationfirebasetest.mainapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.administratorBooksFragment;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.bookShelfFragment;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.digitalReaderFragment;
import com.mozek.myapplicationfirebasetest.models.User;

public class MainActivity extends AppCompatActivity {

    private administratorBooksFragment adminBooksFrag;
    private bookShelfFragment bookShelfFrag;
    private digitalReaderFragment digitalReaderFrag;
    private User user;

    private FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation;
        adminBooksFrag= new administratorBooksFragment();
        bookShelfFrag= new bookShelfFragment();
        digitalReaderFrag= new digitalReaderFragment();
        manager.beginTransaction().replace(R.id.main_frame,adminBooksFrag).addToBackStack(null).commit();
        navigation = findViewById(R.id.navigation);

        getUserFromExternalActivity();

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_admin:
                        manager.beginTransaction().replace(R.id.main_frame, adminBooksFrag).addToBackStack(null).commit();
                        return true;
                    case R.id.navigation_reader:
                        manager.beginTransaction().replace(R.id.main_frame,digitalReaderFrag ).addToBackStack(null).commit();
                        return true;
                    case R.id.navigation_library:
                        manager.beginTransaction().replace(R.id.main_frame,bookShelfFrag).addToBackStack(null).commit();

                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void getUserFromExternalActivity() {
        Bundle data = getIntent().getExtras();
        if (data == null){

        }else{
            user = data.getParcelable("userFromInitConfig");
        }
    }

}
