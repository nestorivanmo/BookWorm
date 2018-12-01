package com.mozek.myapplicationfirebasetest.models;

public class Book {

    private String title, author;
    private int pages;
    private boolean isFinished;

    public Book(){

    }

    public Book(String title, String author, int pages){
       this.title = title;
       this.author = author;
       this.pages = pages;
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

}
