package com.mozek.myapplicationfirebasetest.mainapp.config;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mozek.myapplicationfirebasetest.exceptions.IncorrectDataEntryException;
import com.mozek.myapplicationfirebasetest.mainapp.app.MainActivity;
import com.mozek.myapplicationfirebasetest.managers.DateManager;
import com.mozek.myapplicationfirebasetest.managers.FirebaseManager;
import com.mozek.myapplicationfirebasetest.R;
import com.mozek.myapplicationfirebasetest.exceptions.InitialConfigException;
import com.mozek.myapplicationfirebasetest.exceptions.RegisterToDBException;
import com.mozek.myapplicationfirebasetest.exceptions.UserMissingException;
import com.mozek.myapplicationfirebasetest.models.Book;
import com.mozek.myapplicationfirebasetest.models.PreferredUserSettings;
import com.mozek.myapplicationfirebasetest.models.User;
import com.mozek.myapplicationfirebasetest.verifiers.InitialConfigVerifier;

import java.util.ArrayList;

// TODO: 12/1/18 add user input verifications  
public class InitialConfigActivity extends AppCompatActivity {

    public static final String TAG = "InitialConfigActivity";
    private FloatingActionButton goToMainAppButton;
    private Spinner bookSpinner, weekSpinner;
    private EditText hourET, minuteET;
    private User user;
    private Book userBook = new Book();
    private PreferredUserSettings pfs = new PreferredUserSettings();
    private ArrayList<Book> listOfBooksFromDB = new ArrayList<>();
    private FirebaseManager fbManager;
    private DateManager dateManager;
    private InitialConfigVerifier verifier = new InitialConfigVerifier();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        fbManager = new FirebaseManager();
        dateManager = new DateManager();

        getGraphicElements();

        try {
            getUserFromExternalActivity();
            populateSpinners();
            activateSpinners();
            activateEditTexts();
            transitionToMainAppWindow();

        }catch (UserMissingException e){

        }

    }

    private void activateSpinners(){

        bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                userBook = listOfBooksFromDB.get(i);
                userBook.setRegisteredBookDate(dateManager.getCurrentTime());

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pfs.setFinishBefore(adapterView.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateSpinners() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<String> booksForSpinner = new ArrayList<>();
                        if (task.isSuccessful()) {
                            Book b;
                            for (DocumentSnapshot document : task.getResult()) {
                                b = document.toObject(Book.class);
                                listOfBooksFromDB.add(b);
                                booksForSpinner.add(b.getTitle() + ", " + b.getAuthor());
                            }
                            ArrayAdapter<String> bookAdapter = new ArrayAdapter<>(InitialConfigActivity.this, android.R.layout.simple_spinner_item, booksForSpinner);
                            bookAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            bookSpinner.setAdapter(bookAdapter);
                        } else {

                        }
                    }
                });

        String[] weeksForSpinner = new String[] {"1 week", "2 weeks", "3 weeks", "4 weeks"};
        ArrayAdapter<String> weekAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weeksForSpinner);
        weekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(weekAdapter);
    }

    private void getGraphicElements(){
        goToMainAppButton = findViewById(R.id.goToMainAppButton_InitalConfig);
        bookSpinner = findViewById(R.id.selectBook_Spinner_InitialConfig);
        weekSpinner = findViewById(R.id.selectDate_Spinner_InitialConfig);
        hourET = findViewById(R.id.hourPicker_ET_InitialConfig);
        minuteET = findViewById(R.id.minutePicker_ET_InitialConfig);
    }

    private void activateEditTexts(){

        pfs.setHour(Integer.parseInt(hourET.getHint().toString()));
        pfs.setMinutes(Integer.parseInt(minuteET.getHint().toString()));
        pfs.joinHoursAndMinutes();

        hourET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int userHour = Integer.parseInt(charSequence.toString());
                try {
                    if (verifier.verifyHour(userHour)){
                        pfs.setHour(userHour);
                        pfs.joinHoursAndMinutes();
                    }else{
                        throw new IncorrectDataEntryException();
                    }
                }catch (IncorrectDataEntryException e){
                    Log.i(TAG, "Incorrect hour :(");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        minuteET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int userMinutes = Integer.parseInt(charSequence.toString());
                try{
                    if (verifier.verifyMinutes(userMinutes)){
                        pfs.setMinutes(userMinutes);
                        pfs.joinHoursAndMinutes();
                    }else{
                        throw  new IncorrectDataEntryException();
                    }
                }catch (IncorrectDataEntryException e ){
                    Log.i(TAG, "Incorrect minutes :(");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


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
            }catch (RegisterToDBException e){
            }
        }else {
            updateTitleTextView("", false);
        }
    }

    public void updateTitleTextView(String input, boolean firstTime){
        TextView titleTV;
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

    public void getUserFromExternalActivity() throws UserMissingException{
        Bundle data = getIntent().getExtras();
        if (data == null){
            throw new UserMissingException();
        }else{
            user = data.getParcelable("user");
            updateUI();
        }
    }

    public void transitionToMainAppWindow() {

        goToMainAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userBook.setPreferredUserSettings(pfs);
                user.addBook(userBook);
                addUserToDb();

                Intent goToMainAppWindowIntent = new Intent(InitialConfigActivity.this, MainActivity.class);
                startActivity(goToMainAppWindowIntent);
            }
        });

    }

}
