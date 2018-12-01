package com.mozek.myapplicationfirebasetest.managers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager implements Management {

    public String getCurrentTime(){

        Date currentTime = Calendar.getInstance().getTime();
        String currentDate;

        DateFormat dateFormat = DateFormat.getDateInstance();

        currentDate = dateFormat.format(currentTime);

        return currentDate;
    }
}
