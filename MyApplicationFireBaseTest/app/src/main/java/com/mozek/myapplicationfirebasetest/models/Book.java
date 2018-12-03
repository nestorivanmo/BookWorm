package com.mozek.myapplicationfirebasetest.models;

public class Book {

    private String title, author, registeredBookDate;
    private int pages, progress,currentPage,targetPage;
    private boolean isFinished;
    private PreferredUserSettings preferredUserSettings;

    public Book(){

    }

    public void handleCurrentTargetPages(int numOfWeeks){
        this.progress = 0;
        this.currentPage = 0;
        int numOfDays = numOfWeeks * 7;
        this.targetPage = this.pages / numOfDays;
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(int targetPage) {
        this.targetPage = targetPage;
    }

    @Override
    public String toString() {
        return "Book{title: '"+this.title+"', author: '"+this.author+"', pages:'"+Integer.toString(this.pages)+"'}";
    }
}
