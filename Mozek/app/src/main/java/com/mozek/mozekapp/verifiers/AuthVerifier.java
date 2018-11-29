package com.mozek.mozekapp.verifiers;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.mozek.mozekapp.exceptions.AuthException;
import com.mozek.mozekapp.fairules.Errors_Sign_UP;

public class AuthVerifier extends Verifier {

    public boolean verifyInfo(Context activityContext, String username, String email, String pwd) throws AuthException{
        if (TextUtils.isEmpty(username)){
            super.displayError(activityContext, Errors_Sign_UP.ERROR_005_missing_username);
            throw new AuthException();

        }
        if(TextUtils.isEmpty((email))){
            super.displayError(activityContext, Errors_Sign_UP.ERROR_01_missing_mail);
            throw new AuthException();
        }
        if(TextUtils.isEmpty((pwd))){
            super.displayError(activityContext, Errors_Sign_UP.ERROR_02_missing_password);
            throw new AuthException();
        }
        return true;
    }

}
