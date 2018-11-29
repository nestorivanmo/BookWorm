package com.mozek.mozekapp.verifiers;

import android.os.Build;

public class ApiVerifier {

    public boolean verifySystemApi(int apiNumber){

        if (Build.VERSION.SDK_INT >= apiNumber) {
            return true;
        } else {
            return false;
        }
    }
}
