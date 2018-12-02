package com.mozek.myapplicationfirebasetest.models;

public class Book {

    private String title, author, registeredBookDate;
    private int pages;
    private boolean isFinished;
    private PreferredUserSettings preferredUserSettings;

    public Book(){

    }

    public Book(String title, String author, int pages, String registeredBookDate, PreferredUserSettings pfs){
       this.title = title;
       this.author = author;
       this.registeredBookDate = registeredBookDate;
       this.pages = pages;
       this.preferredUserSettings = pfs;
       this.isFinished = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setRegisteredBookDate(String date){

        this.registeredBookDate = date;
    }

    public String getRegisteredBookDate(){
        return this.registeredBookDate;
    }

    public PreferredUserSettings getPreferredUserSettings() {
        return preferredUserSettings;
    }

    public void setPreferredUserSettings(PreferredUserSettings preferredUserSettings) {
        this.preferredUserSettings = preferredUserSettings;
    }

    @Override
    public String toString() {
        return "Book{title: '"+this.title+"', author: '"+this.author+"', pages:'"+Integer.toString(this.pages)+"'}";
    }
}
