package com.mozek.myapplicationfirebasetest.managers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mozek.myapplicationfirebasetest.models.User;

public class ObjectSenderManager implements Management {

    public ObjectSenderManager(){

    }

    public void sendUserToFragment(String senderTitle, User U, Fragment F){
        Bundle args = new Bundle();
        args.putParcelable(senderTitle, U);
        F.setArguments(args);
    }
}
