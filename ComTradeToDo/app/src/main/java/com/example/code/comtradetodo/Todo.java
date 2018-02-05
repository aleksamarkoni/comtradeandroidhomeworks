package com.example.code.comtradetodo;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String title;
    private String opis;
    private String vreme;
    private boolean isDone;

    public Todo(String title, String vreme, String opis, boolean isDone) {
        this.title = title;
        this.vreme = vreme;
        this.opis = opis;
        this.isDone = isDone;
    }

    public Todo(String title, String opis, boolean isDone) {
        this.title = title;
        this.vreme = vreme;
        this.opis = opis;
        this.isDone = isDone;
    }

    public Todo(String title, String opis, String vreme) {
        this.title = title;
        this.vreme = vreme;
        this.opis = opis;
        this.isDone = isDone;
    }

    public Todo(String title, String opis) {
        this.title = title;
        this.opis = opis;
    }


    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getVreme() {
        return vreme;
    }


    public String getTitle() {
        return title;
    }

    public String getOpis() {
        return opis;
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
        opis = in.readString();
        vreme = in.readString();
        isDone = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(opis);
        dest.writeString(vreme);
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