package com.mozek.myapplicationfirebasetest.exceptions;

import com.mozek.myapplicationfirebasetest.fairules.Errors_Sign_UP;

public class AuthException extends Exception {

    public AuthException(){
        super(Errors_Sign_UP.ERROR_06_recordFailure);    
    }

}
