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

    public int getNumberOfWeeks(String weeks){

        switch (weeks){
            case "1 week":
                return 1;
            case "2 weeks":
                return 2;
            case "3 weeks":
                return 3;
            case "4 weeks":
                return 4;
                default:
                    return 1;
        }
    }
}
