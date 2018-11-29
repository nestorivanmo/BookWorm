package com.mozek.mozekapp.exceptions;

import com.mozek.mozekapp.fairules.Errors_Sign_UP;

public class AuthException extends Exception {

    public AuthException(){
        super(Errors_Sign_UP.ERROR_06_recordFailure);    
    }

}
