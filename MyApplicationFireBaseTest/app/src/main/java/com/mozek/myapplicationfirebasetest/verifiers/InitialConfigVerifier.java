package com.mozek.myapplicationfirebasetest.verifiers;

import com.mozek.myapplicationfirebasetest.exceptions.IncorrectDataEntryException;
import com.mozek.myapplicationfirebasetest.exceptions.InitialConfigException;

public class InitialConfigVerifier extends Verifier {

    public void verifyInfo() throws InitialConfigException {

    }

    public boolean verifyHour(int hour) throws IncorrectDataEntryException {
        if (hour >= 24 )
            return false;
        return true;
    }

    public boolean verifyMinutes(int minutes) throws IncorrectDataEntryException {
        if (minutes >= 60)
            return false;
        return true;
    }

}
