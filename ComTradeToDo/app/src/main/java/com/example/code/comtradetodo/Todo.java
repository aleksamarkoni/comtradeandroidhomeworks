package com.example.code.comtradetodo;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private int databaseId;
    private String title;
    private String description;
    private boolean isDone;
    private int alarmHour;
    private int alarmMin;
    private String pictureFileUri;

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, boolean isDone, int databaseId) {
        this.title = title;
        this.isDone = isDone;
        this.databaseId = databaseId;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(int alarmHour) {
        this.alarmHour = alarmHour;
    }

    public int getAlarmMin() {
        return alarmMin;
    }

    public void setAlarmMin(int alarmMin) {
        this.alarmMin = alarmMin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pictureFileUri);
        dest.writeInt(this.databaseId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeByte(this.isDone ? (byte) 1 : (byte) 0);
        dest.writeInt(this.alarmHour);
        dest.writeInt(this.alarmMin);
    }

    public void setPictureFileUri(String pictureFileUri) {
        this.pictureFileUri = pictureFileUri;
    }

    public String getPictureFileUri() {
        return pictureFileUri;

    }

    protected Todo(Parcel in) {
        this.pictureFileUri = in.readString();
        this.databaseId = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.isDone = in.readByte() != 0;
        this.alarmHour = in.readInt();
        this.alarmMin = in.readInt();
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

    public boolean shouldStartAlarm() {
        return alarmHour != -1 && alarmMin != -1;
    }
}