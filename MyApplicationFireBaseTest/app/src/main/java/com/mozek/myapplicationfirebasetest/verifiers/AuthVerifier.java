package com.mozek.myapplicationfirebasetest.verifiers;

import android.content.Context;
import android.text.TextUtils;

import com.mozek.myapplicationfirebasetest.exceptions.AuthException;
import com.mozek.myapplicationfirebasetest.fairules.Errors_Login;
import com.mozek.myapplicationfirebasetest.fairules.Errors_Sign_UP;

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

    public boolean verifyInfoLogin(Context activityContext,  String email, String pwd) throws AuthException{
        if(TextUtils.isEmpty((email))){
            super.displayError(activityContext, Errors_Login.ERROR_01_missing_Mail);
            throw new AuthException();
        }
        if(TextUtils.isEmpty((pwd))){
            super.displayError(activityContext, Errors_Login.ERROR_02_missing_Password);
            throw new AuthException();
        }
        return true;
    }

}
