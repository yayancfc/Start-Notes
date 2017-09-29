package com.yayanheryanto.startnotes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yayan Heryanto on 9/12/2017.
 */

public class TextNotes implements Parcelable {

    private int id;
    private String title;
    private String desccription;
    private String location;
    private String date;
    private String time;
    private int color;

    public TextNotes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.desccription);
        dest.writeString(this.location);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeInt(this.color);
    }

    protected TextNotes(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.desccription = in.readString();
        this.location = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.color = in.readInt();
    }

    public static final Creator<TextNotes> CREATOR = new Creator<TextNotes>() {
        @Override
        public TextNotes createFromParcel(Parcel source) {
            return new TextNotes(source);
        }

        @Override
        public TextNotes[] newArray(int size) {
            return new TextNotes[size];
        }
    };
}
