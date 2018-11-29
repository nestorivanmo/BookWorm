package com.mozek.mozekapp.models;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable{

    private String username;
    private String email;
    private String password;

    public User(Parcel in) {
        this.username = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addBook(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(password);
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
        return "User{'username': "+this.username+", 'email': "+this.email+"}";
    }

}
