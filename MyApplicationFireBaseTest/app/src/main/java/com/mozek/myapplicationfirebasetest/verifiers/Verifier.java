package com.mozek.myapplicationfirebasetest.verifiers;

import android.content.Context;
import android.widget.Toast;

public class Verifier {

    public void displayError(Context activityContext, String errorMsg) {
        Toast.makeText(activityContext, errorMsg ,Toast.LENGTH_SHORT).show();
    }

    public void displaySuccess(Context activityContext, String errorMsg) {
        Toast.makeText(activityContext, errorMsg ,Toast.LENGTH_SHORT).show();
    }

}
