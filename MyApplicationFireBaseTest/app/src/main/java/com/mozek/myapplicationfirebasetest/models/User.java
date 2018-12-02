package com.mozek.myapplicationfirebasetest.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static com.mozek.myapplicationfirebasetest.mainapp.config.InitialConfigActivity.TAG;


public class User implements Parcelable{

    private String username;
    private String email;
    private int firstTime;
    private ArrayList<Book> books = new ArrayList<>();

    public User(){

    }

    public User(Parcel in) {
        this.username = in.readString();
        this.email = in.readString();
        this.firstTime = in.readInt();
    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.firstTime = 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeInt(firstTime);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>(){
        public User createFromParcel(Parcel in){
            return new User(in);
        }
        public User[] newArray(int size){
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{username: '"+this.username+"', email: '"+this.email+"', firstTime?:'"+this.firstTime+"'}";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }

    public void addBook(Book b){
        this.books.add(b);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

}
