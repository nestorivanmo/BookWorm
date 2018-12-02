package com.mozek.myapplicationfirebasetest.models;


public class PreferredUserSettings {

    private String finishBefore, preferredReadingHour;
    private int hour, minutes;

    public PreferredUserSettings() {
    }

    public void joinHoursAndMinutes(){
        String hourP = String.valueOf(hour) + ":" + String.valueOf(minutes);
        setPreferredReadingHour(hourP);
    }

    public String getFinishBefore() {
        return finishBefore;
    }

    public void setFinishBefore(String finishBefore) {
        this.finishBefore = finishBefore;
    }

    public String getPreferredReadingHour() {
        return preferredReadingHour;
    }

    public void setPreferredReadingHour(String preferredReadingHour) {
        this.preferredReadingHour = preferredReadingHour;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "PrefferedUserSettings{finishBefore: '"+getFinishBefore()+"', preferredReadingHour: '"+getPreferredReadingHour()+"'}";
    }
}
