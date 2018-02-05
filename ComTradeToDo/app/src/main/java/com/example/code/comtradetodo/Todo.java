package com.example.code.comtradetodo;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String title;
    private boolean isDone;
    //TODO domaci 2.2.2018 ovde dodati hour i min promenljive i odgovarajuce setere i geters 1 poen

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    protected Todo(Parcel in) {
        title = in.readString();
        isDone = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
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