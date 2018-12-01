package com.mozek.myapplicationfirebasetest.mainapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.administratorBooksFragment;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.bookShelfFragment;
import com.mozek.myapplicationfirebasetest.mainapp.app.fragments.digitalReaderFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView navigation;
    private administratorBooksFragment adminBooksFrag;
    private bookShelfFragment bookShelfFrag;
    private digitalReaderFragment digitalReaderFrag;

    private FragmentManager manager = getSupportFragmentManager();


   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getSupportFragmentManager().beginTransaction().add(R.id.main_frame,adminBooksFrag).commit();
        adminBooksFrag= new administratorBooksFragment();
        bookShelfFrag= new bookShelfFragment();
        digitalReaderFrag= new digitalReaderFragment();
        manager.beginTransaction().replace(R.id.main_frame,adminBooksFrag).addToBackStack(null).commit();
        navigation = findViewById(R.id.navigation);



        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_admin:
                       // adminBooksFrag= new administratorBooksFragment();
                        manager.beginTransaction().replace(R.id.main_frame, adminBooksFrag).addToBackStack(null).commit();
                        return true;
                    case R.id.navigation_reader:
                       // bookShelfFrag= new bookShelfFragment();
                        manager.beginTransaction().replace(R.id.main_frame,digitalReaderFrag ).addToBackStack(null).commit();
                        return true;
                    case R.id.navigation_library:
                        //digitalReaderFrag= new digitalReaderFragment();
                        manager.beginTransaction().replace(R.id.main_frame,bookShelfFrag).addToBackStack(null).commit();

                        return true;
                }
                return false;
            }

       /* private void setFragment(Fragment fragment) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame,fragment);
            fragmentTransaction.commit();
        }*/
        };

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

}
