package com.example.code.comtradetodo;


import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private int databaseId;
    private String title;
    private String about;
    private int hh;
    private int mm;
    private boolean isDone;
    //TODO domaci 2.2.2018 ovde dodati hour i min promenljive i odgovarajuce setere i geters 1 poen

    public Todo(String title, String about, int hh, int mm) {
        this.hh = hh;
        this.mm = mm;
        this.title = title;
        this.about = about;
    }

    public Todo(String title, String about, int hh, int mm, boolean isDone) {
        this.hh = hh;
        this.mm = mm;
        this.about = about;
        this.title = title;
        this.isDone = isDone;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public String getTitle() {
        return title;
    }

    public String getAbout() {
        return about;
    }

    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.databaseId);
        dest.writeString(this.title);
        dest.writeString(this.about);
        dest.writeInt(this.hh);
        dest.writeInt(this.mm);
        dest.writeByte(this.isDone ? (byte) 1 : (byte) 0);
    }

    protected Todo(Parcel in) {
        this.databaseId = in.readInt();
        this.title = in.readString();
        this.about = in.readString();
        this.hh = in.readInt();
        this.mm = in.readInt();
        this.isDone = in.readByte() != 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}