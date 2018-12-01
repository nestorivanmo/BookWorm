package com.mozek.myapplicationfirebasetest.mainapp.config;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mozek.myapplicationfirebasetest.FirebaseManager.FirebaseManager;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.InitialConfigException;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.models.Book;
import com.mozek.myapplicationfirebasetest.models.User;
import com.mozek.myapplicationfirebasetest.verifiers.InitialConfigVerifier;


public class InitialConfigActivity extends AppCompatActivity {

    public static final String TAG = "InitialConfigActivity";

    private FloatingActionButton goToMainAppButton;
    private TextView titleTV;
    private Spinner bookSpinner, deadlineDateSpiner;
    private EditText hourET, minuteET;

    private User user;
    private Book book;
    private FirebaseManager fbManager;
    private InitialConfigVerifier verifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);
        fbManager = new FirebaseManager();
        getGraphicElements();
        activateSpinners();

        try {
            user = receiveUser();
            handleUser(user);

        }catch (UserMissingException e){

        }

    }

    private void getGraphicElements(){
        bookSpinner = findViewById(R.id.selectBook_Spinner_InitialConfig);
        deadlineDateSpiner = findViewById(R.id.selectDate_Spinner_InitialConfig);
        hourET = findViewById(R.id.hourPicker_ET_InitialConfig);
        minuteET = findViewById(R.id.minutePicker_ET_InitialConfig);
    }

    private void activateSpinners(){
        bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void handleUser(User user) {

        if (user.getFirstTime() == 1){

            user.setFirstTime(0);

            updateTitleTextView(user.getUsername(), true);

            try{

                verifier.verifyInfo();
                fbManager.addUserToDb(user, TAG);
                transitionToMainAppWindow();

            }catch (InitialConfigException e){



            }catch (RegisterToDBException e){

            }

        }else {

            updateTitleTextView("", false);
        }

    }

    public void updateTitleTextView(String input, boolean firstTime){
        titleTV = findViewById(R.id.initialConfigTitle_TV_InitialConfig);
        String currentText;

        if (firstTime) {
            currentText = getString(R.string.Bienvenido);
        }else{
            currentText = getString(R.string.agregaUnNuevoLibro);
        }

        currentText += ", " + input;
        titleTV.setText(currentText);
    }

    public User receiveUser() throws UserMissingException{

        Bundle data = getIntent().getExtras();
        User user;
        if (data == null){
            throw new UserMissingException();
        }else{
            user = data.getParcelable("user");
        }
        return user;
    }

    public void transitionToMainAppWindow() {

        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);

        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMainAppWindowIntent = new Intent(InitialConfigActivity.this, MainActivity.class);
                startActivity(goToMainAppWindowIntent);
            }
        });

    }

}
