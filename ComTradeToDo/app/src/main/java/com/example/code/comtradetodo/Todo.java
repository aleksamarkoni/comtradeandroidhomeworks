package com.example.code.comtradetodo;


import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String title;
    private String about;
    private String hh;
    private String mm;
    private boolean isDone;
    //TODO domaci 2.2.2018 ovde dodati hour i min promenljive i odgovarajuce setere i geters 1 poen

    public Todo(String title, String about, String hh, String mm) {
        this.hh = hh;
        this.mm = mm;
        this.title = title;
        this.about = about;
    }

    public Todo(String title, String about, String hh, String mm, boolean isDone) {
        this.hh = hh;
        this.mm = mm;
        this.about = about;
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public String getAbout() {
        return about;
    }

    public String getHh() {
        return hh;
    }

    public String getMm() {
        return mm;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    protected Todo(Parcel in) {
        title = in.readString();
        about = in.readString();
        hh = in.readString();
        mm = in.readString();
        isDone = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hh);
        dest.writeString(mm);
        dest.writeString(title);
        dest.writeString(about);
        dest.writeByte((byte) (isDone ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}