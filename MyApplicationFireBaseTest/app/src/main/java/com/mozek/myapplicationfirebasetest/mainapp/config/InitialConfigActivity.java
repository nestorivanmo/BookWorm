package com.mozek.myapplicationfirebasetest.mainapp.config;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mozek.myapplicationfirebasetest.FirebaseManager.FirebaseManager;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.InitialConfigException;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.models.Book;
import com.mozek.myapplicationfirebasetest.models.PreferredUserSettings;
import com.mozek.myapplicationfirebasetest.models.User;
import com.mozek.myapplicationfirebasetest.verifiers.InitialConfigVerifier;


public class InitialConfigActivity extends AppCompatActivity {

    public static final String TAG = "InitialConfigActivity";

    private FloatingActionButton goToMainAppButton;
    private TextView titleTV;
    private Spinner bookSpinner, weekSpinner;
    private EditText hourET, minuteET;

    private User user;
    private Book book;
    private FirebaseManager fbManager;
    private InitialConfigVerifier verifier = new InitialConfigVerifier();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);
        fbManager = new FirebaseManager();
        getGraphicElements();
        activateSpinners();

        try {

            user = receiveUser();
            updateUI();
            transitionToMainAppWindow();

        }catch (UserMissingException e){


        }

    }

    private void activateSpinners(){

        String[] booksForSpinner = new String[] {"Book 1", "Book 2", "Book 3"};
        String[] weeksFroSpinner = new String[] {"1 week", "2 weeks", "3 weeks", "4 weeks"};

        ArrayAdapter<String> bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, booksForSpinner);
        bookAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookSpinner.setAdapter(bookAdapter);

        ArrayAdapter<String> weekAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weeksFroSpinner);
        weekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(weekAdapter);

        bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(InitialConfigActivity.this, "Selected book: "+ adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(InitialConfigActivity.this, "Selected week: "+ adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getGraphicElements(){
        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);
        bookSpinner = findViewById(R.id.selectBook_Spinner_InitialConfig);
        weekSpinner = findViewById(R.id.selectDate_Spinner_InitialConfig);
        hourET = findViewById(R.id.hourPicker_ET_InitialConfig);
        minuteET = findViewById(R.id.minutePicker_ET_InitialConfig);
    }

    private void updateUI(){
        if (user.getFirstTime() == 1){

            updateTitleTextView(user.getUsername(), true);

        }else {

            updateTitleTextView("", false);
        }
    }

    private void addUserToDb() {

        if (user.getFirstTime() == 1){

            user.setFirstTime(0);

            try{
                fbManager.addUserToDb(user, TAG);
                transitionToMainAppWindow();

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

        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    verifier.verifyInfo();
                    PreferredUserSettings pfs = new PreferredUserSettings("12-12-18", "08:00");
                    book = new Book("The Code Book", "Simon Singh", 234, "30-11-18", pfs);
                    user.addExtraInfo(InitialConfigActivity.this, book);
                    //addUserToDb();

                }catch (InitialConfigException e){

                }

                //Intent goToMainAppWindowIntent = new Intent(InitialConfigActivity.this, MainActivity.class);
                //startActivity(goToMainAppWindowIntent);
            }
        });

    }

}
