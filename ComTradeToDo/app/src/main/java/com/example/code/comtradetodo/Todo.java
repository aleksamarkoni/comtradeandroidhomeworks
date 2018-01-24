package com.example.code.comtradetodo;

public class Todo {
    private String title;
    private boolean isDone;

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
}