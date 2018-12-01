package com.mozek.myapplicationfirebasetest.models;

import java.util.Calendar;
import java.util.Date;

public class PreferredUserSettings {

    private String finishBefore, preferredReadingHour;

    public PreferredUserSettings() {
    }

    public PreferredUserSettings(String finishBefore, String preferredReadingHour) {
        this.finishBefore = finishBefore;
        this.preferredReadingHour = preferredReadingHour;
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

    @Override
    public String toString() {
        return "PrefferedUserSettings{finishBefore: '"+this.finishBefore+"', preferredReadingHour: '"+this.preferredReadingHour+"'}";
    }
}
